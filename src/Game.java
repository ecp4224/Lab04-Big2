import java.util.Arrays;

public class Game {

    private Player[] players = new Player[4];
    private Deck theDeck;
    private Player whoseTurn = players[0];

    private Card[] currentMiddle = new Card[0];


    public Game() {
        theDeck = new Deck();
        theDeck.shuffle();

        //deal to all players
        for(int p = 0; p < 4; p++){
            players[p] = new Player();
            for(int i = 0; i < 13; i++){
                players[p].getHand().addCardToHand(theDeck.dealCard());
            }
        }


        //simulates game process, going through turns
        while(whoseTurn.getHand() != null){
            if(whoseTurn.hasJustPlayedCard()){
                if(whoseTurn.getPosition() == 3){
                    whoseTurn = players[0];
                }
                else{
                    whoseTurn = players[whoseTurn.getPosition() + 1];
                }
            }
        }
    }

    public boolean requestMove(List<Card> toPlay) {
        toPlay.sort();

        if (!isValidMove(toPlay))
            return false;

        currentMiddle = toPlay.toArray();
        return true;
    }

    public boolean isValidMove(List<Card> toPlay) {
        if (!isAllSame(toPlay) && !isStream(toPlay))
            return false;

        if (currentMiddle.length == 0) {
            return toPlay.getCurrentSize() == 1 || isAllSame(toPlay) || isStream(toPlay);
        } else {
            if (currentMiddle.length != toPlay.getCurrentSize())
                return false;
            else if (isAllSame(currentMiddle) && isStream(toPlay))
                return false;
            else if (isStream(currentMiddle) && isAllSame(toPlay))
                return false;
            else {
                //Cards are always sorted from lowest to highest
                Card highestMiddle = currentMiddle[currentMiddle.length - 1];
                Card highestPlay = toPlay.getEntry(toPlay.getCurrentSize() - 1);

                if (highestMiddle.isHigher(highestPlay))
                    return false;
            }
        }

        return true;
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
}
