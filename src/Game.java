
public class Game {

    private Player[] players = new Player[4];
    private Deck theDeck = new Deck();
    private Player whoseTurn = players[0];


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
}
