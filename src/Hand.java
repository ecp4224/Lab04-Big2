/**
 * Created by corpa on 3/3/2016.
 */
public class Hand {

    private int numOfCards;
    private List<Card> cards;

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
        for(int i = 0; i <= cards.getCurrentSize(); i++){
            System.out.println(cards.getEntry(i));
        }
    }

    public void removeCards(List<Card> cards) {
        for (int i = 0; i < cards.getLength(); i++) {
            Card c = cards.getEntry(i);
            removeCardFromHand(c);
        }
    }
}
