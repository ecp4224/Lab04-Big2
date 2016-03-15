/**
 * Created by ollilaj on 3/15/2016.
 */
public class Player {

    private String name;
    private Hand currentHand = new Hand();
    private boolean isMyTurn = false;

    public Player(){

    }

    public Hand getHand(){
        return currentHand;
    }
}
