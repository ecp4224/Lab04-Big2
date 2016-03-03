/**
 * Created by corpa on 3/2/2016.
 */
public class Card {

    private int value;
    private int suit;

    public final int DIAMONDS = 0;
    public final int CLUBS = 1;
    public final int HEARTS = 2;
    public final int SPADES = 3;

    public Card() {
        suit = SPADES;
        value = 1;
    }

    public String getSuitAsString(){
        switch (suit) {
            case SPADES: return "Spades";
            case HEARTS: return "Hearts";
            case CLUBS: return "Clubs";
            case DIAMONDS: return "Diamonds";
            default: return "null";
        }
    }

    public String getValueAsString() {
        switch (value){
            case 1: return "3";
            case 2: return "4";
            case 3: return "5";
            case 4: return "6";
            case 5: return "7";
            case 6: return "8";
            case 7: return "9";
            case 8: return "10";
            case 9: return "Jack";
            case 10: return "Queen";
            case 11: return "King";
            case 12: return "Ace";
            case 13: return "2";
            default: return null;
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getSuit() {
        return suit;
    }

    public void setSuit(int suit) {
        this.suit = suit;
    }

}
