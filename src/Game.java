
public class Game {
    private Player[] players = new Player[4];
    private Deck theDeck = new Deck();
    private String whoseTurn;


    public Game() {

        //deal to all players
        for(int p = 0; p < 4; p++){

            players[p] = new Player();

            for(int i = 0; i < 13; i++){

                players[p].getHand().addCardToHand(theDeck.dealCard());

            }
        }

        for(int p = 0; p < 4; p++) {





        }






    }
}
