import java.util.LinkedList;

/**
 * Created by corpa on 3/3/2016.
 */
public class Deck {

    LinkedList<Card> deck;

    private int cardsUsed;

    public Deck() {
        deck = new LinkedList<>();

        int cardCount = 0;

        for(int suit = 0; suit <= 3; suit++) {
            for(int value = 1; value <= 13; value++) {
                deck.add(new Card(value, Card.Suit.values()[suit]));//TODO TEST THIS NOT SURE IF RIGHT
                cardCount++;
            }
        }
        cardsUsed = 0;
    }

    public void shuffle() {
        for(int i = deck.size(); i > 0; i--) {
            int rand = (int) (Math.random()*(i+1));
            Card temp = deck.get(i);
            deck.add(i,deck.get(rand));
            deck.add(rand,temp);
        }
        cardsUsed = 0;
    }

    public int cardsLeft() {
        return deck.size() - cardsUsed;
    }

    public Card dealCard() {
        cardsUsed++;
        return deck.get(cardsUsed-1);
    }
}
