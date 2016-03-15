import java.util.Random;

public final class Card implements Comparable<Card>, Cloneable {
    private int value;
    private Suit suit;

    /**
     * The lowest possible card in Big2
     */
    public static final Card LOWEST_BIG2_CARD = new Card(3, Suit.SPADES);

    /**
     * The highest possible card in Big2
     */
    public static final Card BIG2 = new Card(2, Suit.HEARTS);

    /**
     * Create a new deck that is sorted and return that deck as a {@link java.util.List} of {@link Card} objects
     * @return An ordered {@link java.util.List} representing a poker deck
     */
    public static List<Card> createOrderedDeck() {
        List<Card> cards = new List<Card>();

        for (int value = 2; value <= 14; value++) {
            for (Suit suit : Suit.values()) {
                cards.add(new Card(value, suit));
            }
        }

        return cards;
    }

    /**
     * Shuffle a {@link java.util.List} of {@link Card} objects
     * @param cards The {@link java.util.List} to shuffle
     */
    public static void shuffleCards(List<Card> cards) {
        Random random = new Random();
        for (int i = cards.getCurrentSize() - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            Card card = cards.getEntry(index);
            cards.replace(index, cards.getEntry(i));
            cards.replace(i, card);
        }
    }

    /**
     * This method is the same method as {@link Card#createOrderedDeck()}, but the deck is shuffled
     * using the {@link Card#shuffleCards(List)} method before the list is returned
     * @return A shuffled poker deck
     */
    public static List<Card> createShuffledDeck() {
        List<Card> deck = createOrderedDeck();

        int shuffleCount = new Random().nextInt(100) + 40;

        for (int i = 0; i < shuffleCount; i++)
            shuffleCards(deck);

        return deck;
    }

    /**
     * Create a new card
     * @param value The raw value of this card. 2 being 2, 3 being 3...11 being jack, 12 being queen, 13 being king, and 14 being ace. A raw value of 1 will
     *              also result in an ace.
     * @param suit The {@link Card.Suit} of this card
     */
    public Card(int value, Suit suit) {
        this.value = value;
        this.suit = suit;
    }

    public Card(Card card) {
        this.value = card.getRawValue();
        this.suit = card.getSuit();
    }

    /**
     * Whether this card is a Jack
     * @return True if this card is a Jack, otherwise false
     */
    public boolean isJack() {
        return value == 11;
    }

    /**
     * Whether this card is a Queen
     * @return True if this card is a Queen, otherwise false
     */
    public boolean isQueen() {
        return value == 12;
    }

    /**
     * Whether this card is a King
     * @return True if this card is a King, otherwise false
     */
    public boolean isKing() {
        return value == 13;
    }

    /**
     * Whether this card is an Ace
     * @return True if this card is an Ace, otherwise false
     */
    public boolean isAce() {
        return value == 14 || value == 1;
    }

    /**
     * Get the name of this card. <br></br>
     * If this card is a face card, then the name of the face card is returned, otherwise the number is returned. <br></br>
     * <b>The suite is not included! To get the full name, use the {@link Card#toString()} method to get the
     * full name</b>
     * @return The name of the card
     */
    public String getName() {
        if (isAce())
            return "Ace";
        if (isKing())
            return "King";
        if (isQueen())
            return "Queen";
        if (isJack())
            return "Jack";
        else
            return "" + value;
    }

    /**
     * Get the poker value of this card. The poker value is the raw value of the card with the following exceptions: <br></br>
     * * All face values except Ace is 10
     * * Ace is returned as 11
     * @return The poker value of this card
     */
    public int getPokerValue() {
        if (value > 10) {
            if (isAce())
                return 11;
            return 10;
        }
        return value;
    }

    /**
     * Get the Big2 value of this card. This value returned the raw value with the following exceptions: <br></br>
     * * 2 is returned as 15, since in Big2, 2 is the highest value
     * @return The Big2 value of this card
     */
    public int getBig2Value() {
        return value == 2 ? 15 : value;
    }

    public boolean isHigher(Card card) {
        if (getBig2Value() > card.getBig2Value())
            return true;
        else if (getBig2Value() == card.getBig2Value()) {
            if (getSuit().isHigher(card.suit))
                return true;
        }
        return false;
    }

    /**
     * Get the raw value of this card, where Ace can be either 14 or 1
     * @return The raw value of this card
     */
    public int getRawValue() {
        return value;
    }

    /**
     * Get the {@link Card.Suit} of this card
     * @return The {@link Card.Suit}
     */
    public Suit getSuit() {
        return suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        return value == card.value && suit == card.suit;

    }

    @Override
    public int hashCode() {
        int result = value;
        result = 31 * result + suit.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return getName() + " of " + getSuit();
    }

    @Override
    public int compareTo(Card o) {
        int oRawValue = o.getRawValue() == 2 ? 15 : o.getRawValue();
        int rawValue = value == 2 ? 15 : value;

        if (oRawValue == rawValue) {
            return o.getSuit().getRawValue() - suit.getRawValue();
        }
        return getRawValue() - o.getRawValue();
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        Object obj = super.clone();
        if (obj instanceof Card) {
            ((Card)obj).value = value;
            ((Card)obj).suit = suit;
        }
        return obj;
    }

    /**
     * Clone the card, <b>card</b>
     * @param card The card to clone
     * @return A cloned card
     * @see Object#clone()
     */
    public static Card clone(Card card) {
        return new Card(card);
    }

    public enum Suit {

        HEARTS(4),
        DIAMONDS(3),
        CLUBS(2),
        SPADES(1);

        int value;

        Suit(int value) {
            this.value = value;
        }

        public boolean isHigher(Suit suit) {
            return suit.value < value;
        }

        public int getRawValue() {
            return value;
        }

        @Override
        public String toString() {
            return super.name().substring(0, 1) + super.name().substring(1).toLowerCase();
        }
    }
}
