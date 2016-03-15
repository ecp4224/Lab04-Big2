
public class Game {

    private Player[] players = new Player[4];
    private Deck theDeck = new Deck();
    private Player whoseTurn = players[0];

    private Card[] currentMiddle = new Card[0];


    public Game() {

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

    private boolean isValidMove(List<Card> toPlay) {
        if (currentMiddle.length == 0) {
            if (toPlay.getCurrentSize() == 1)
                return true;
            else {
                return isAllSame(toPlay) || isStream(toPlay);
            }
        } else {
            if (currentMiddle.length == 1 && toPlay.getCurrentSize() != 1)
                return false;
            else if (currentMiddle.length == 1 && toPlay.getCurrentSize() == 1) {

            }
        }
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
