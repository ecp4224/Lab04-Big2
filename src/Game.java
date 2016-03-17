import java.util.Arrays;

public class Game {

    private Player[] players = new Player[4];
    private Deck theDeck;
    private Player whoseTurn = players[0];
    private List<Card> currentMiddle = new List<>();


    public Game() {

        theDeck = new Deck();
        theDeck.shuffle();

        //set position of each player
        for(int p = 0; p < 4; p++){
            players[p].setPosition(p);
        }

        //deal to all players
        for(int p = 0; p < 4; p++){
            if (p == 0)
                players[p] = new HumanPlayer();
            else
                players[p] = new ComputerPlayer();

            for(int i = 0; i < 13; i++){
                players[p].getHand().addCardToHand(theDeck.dealCard());
            }
        }

        //print hands of players
        for(int i = 0; i < 4; i++) {
            Player p = players[i];
            System.out.println("Player " + (i+1) + " card's: ");
            p.getHand().printHand();
        }
        System.out.println();
    }

    public void start() {
        int turn = 0;
        while (true) { //Continue until someone wins
            Player currentPlayer = players[turn];

            System.out.println("==============Player " + (turn + 1) + "==============");
            do {
                List<Card> cards = currentPlayer.makeMove(this);

                if (cards == null) {
                    System.out.println("Passed");
                    break;
                }

                if (requestMove(cards)) {
                    currentPlayer.getHand().removeCards(cards);
                    break;
                } else {
                    System.out.println("Invalid move!");
                }
            } while (true);

            if (currentPlayer.getHand().getNumOfCards() == 0) {
                System.out.println("Player " + (turn + 1) + " wins!");
                break;
            }

            turn++;
            turn = turn % 4;
            System.out.println("============================");
        }
    }

    public boolean requestMove(List<Card> toPlay) {
        toPlay.sort();

        if (!isValidMove(toPlay))
            return false;

        currentMiddle = toPlay;
        return true;
    }

    public boolean isValidMove(List<Card> toPlay) {
        if (!isAllSame(toPlay) && !isStream(toPlay))
            return false;

        if (currentMiddle.getLength() == 0) {
            return toPlay.getCurrentSize() == 1 || isAllSame(toPlay) || isStream(toPlay);
        } else {
            if (currentMiddle.getLength() != toPlay.getCurrentSize())
                return false;
            else if (isAllSame(currentMiddle) && isStream(toPlay))
                return false;
            else if (isStream(currentMiddle) && isAllSame(toPlay))
                return false;
            else {
                //Cards are always sorted from lowest to highest
                Card highestMiddle = currentMiddle.getEntry(currentMiddle.getLength() - 1);
                Card highestPlay = toPlay.getEntry(toPlay.getCurrentSize() - 1);

                if (highestMiddle.isHigher(highestPlay))
                    return false;
            }
        }

        return true;
    }

    public List<Card> getCurrentMiddle() {
        return currentMiddle;
    }

    private boolean isAllSame(Card[] cards) {
        return isAllSame(new List<Card>(cards));
    }

    private boolean isStream(Card[] cards) {
        return isStream(new List<Card>(cards));
    }

    private boolean isAllSame(List<Card> cards) {
        int lastValue = cards.getEntry(0).getBig2Value();
        for (int i = 1; i < cards.getCurrentSize(); i++) {
            if (cards.getEntry(i).getBig2Value() != lastValue)
                return false;
        }
        return true;
    }

    private boolean isStream(List<Card> cards) {
        if (cards.getCurrentSize() < 3)
            return false;

        int lastValue = cards.getEntry(0).getBig2Value();
        for (int i = 1; i < cards.getCurrentSize(); i++) {
            if (cards.getEntry(i).getBig2Value() != lastValue + 1)
                return false;
            lastValue = cards.getEntry(i).getBig2Value();
        }

        return true;
    }


    //going by this scoring https://en.wikipedia.org/wiki/Big_Two#Scoring
    private void setScoring(Player[] players){
        int negativeScore = 0;
        for(Player p : players){
            int countCards = p.getHand().getNumOfCards();
            if(countCards == 13){
                p.setScore(13*(-3));
                negativeScore += (13*3);
            } else if(countCards >= 10){
                p.setScore(countCards * (-2));
                negativeScore += (countCards*2);
            } else if (countCards > 0){
                p.setScore(countCards * (-1));
                negativeScore += countCards;
            } else {
                p.setScore(negativeScore);
            }
        }
    }

    public Card[] getCurrentMiddle() {
        return currentMiddle;
    }

}