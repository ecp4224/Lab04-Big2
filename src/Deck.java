import java.util.LinkedList;

/**
 * Created by corpa on 3/3/2016.
 */
public class Deck {

    List<Card> deck;

    private int cardsUsed;

    public Deck() {
        deck = Card.createOrderedDeck();
        cardsUsed = 0;
    }

    public void shuffle() {
        deck = Card.createShuffledDeck();
        cardsUsed = 0;
    }

    public int cardsLeft() {
        return deck.getCurrentSize() - cardsUsed;
    }

    public Card dealCard() {
        cardsUsed++;
        return deck.getEntry(cardsUsed-1);
    }
}
