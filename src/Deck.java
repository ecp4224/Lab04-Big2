/**
 * Class: COMP 2071
 * Assignment: Lab 4
 * Due Date: 3/17/16
 * Group #: 21
 * Group Members:   Andrew Corp
 *                  Eddie Penta
 *                  Jacob Ollila
 */

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
