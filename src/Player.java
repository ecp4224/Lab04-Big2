/**
 * Class: COMP 2071
 * Assignment: Lab 4
 * Due Date: 3/17/16
 * Group #: 21
 * Group Members:   Andrew Corp
 *                  Eddie Penta
 *                  Jacob Ollila
 */

public abstract class Player {
    private Hand currentHand = new Hand();
    private int score;
    private int position;

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

    public void setPosition(int num){
        position = num;
    }

    public int getPosition(){
       return position;
    }

    public int getScore() {
        return score;
    }

    public void printHand() {
        currentHand.printHand();
    }
}
