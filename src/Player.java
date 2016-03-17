/**
 * Created by ollilaj on 3/15/2016.
 */
public abstract class Player {
    private Hand currentHand = new Hand();
    private int score;

    public Player(){
        score = 0;
    }

    public Hand getHand(){
        return currentHand;
    }

    public void setScore(int score){
        this.score = score;
    }

    public abstract List<Card> makeMove(Game game);
}
