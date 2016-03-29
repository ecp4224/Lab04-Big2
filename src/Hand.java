/**
 * Class: COMP 2071
 * Assignment: Lab 4
 * Due Date: 3/17/16
 * Group #: 21
 * Group Members:   Andrew Corp
 *                  Eddie Penta
 *                  Jacob Ollila
 */

public class Hand {

    private int numOfCards;
    private List<Card> cards = new List<>();

    public Hand() {

    }

    public List<Card> getHand(){
        return cards;
    }

    public boolean addCardToHand(Card newCard){
        numOfCards++;
        return cards.add(newCard);
    }

    public boolean removeCardFromHand(Card cardToBeRemoved){
        numOfCards--;
        return cards.remove(cardToBeRemoved);
    }

    public int getNumOfCards(){
        return numOfCards;
    }

    public void printHand(){
        for(int i = 0; i < cards.getCurrentSize(); i++){
            System.out.println("    " + cards.getEntry(i));
        }
    }

    public void removeCards(List<Card> cards) {
        for (int i = 0; i < cards.getLength(); i++) {
            Card c = cards.getEntry(i);
            removeCardFromHand(c);
        }
    }
}
