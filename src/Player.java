/**
 * Created by ollilaj on 3/15/2016.
 */
public class Player {

    private String name;
    private Hand currentHand = new Hand();
    private boolean myTurn = false;
    private boolean justPlayedACard = false;
    private int position;
    private int score;

    public Player(){
        score = 0;
    }

    public Hand getHand(){
        return currentHand;
    }

    public Boolean isMyTurn(){
        return myTurn;
    }

    public Boolean hasJustPlayedCard(){
        return justPlayedACard;
    }

    public int getPosition(){
        return position;
    }

    public void setScore(int score){
        this.score = score;
    }


}
