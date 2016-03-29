/**
 * Class: COMP 2071
 * Assignment: Lab 4
 * Due Date:
 * Group #: 21
 * Group Members:   Andrew Corp
 *                  Eddie Penta
 *                  Jacob Ollila
 */

public class ComputerPlayer extends Player {
    @Override
    public List<Card> makeMove(Game game) {
        List<Card> toBeat = game.getCurrentMiddle();
        List<Card> hand = getHand().getHand();

        toBeat.sort();
        hand.sort();

        return drawCard(hand, toBeat, false);
    }
    
    public List<Card> drawCard(List<Card> hand, List<Card> toBeat, boolean firstMove) {
        if (firstMove) {
            return startGame(hand);
        }
        else if (toBeat == null || toBeat.getLength() == 0) {
            return startRound(hand);
        }
        else if (!isAStream(toBeat)) {
            if (toBeat.getLength() == 1 && toBeat.getEntry(0).equals(Card.BIG2))
                return find4OfAKind(hand);
            else
                return findLowestCard(hand, toBeat);
        } else {
            return lowestStreamOrPass(hand, toBeat);
        }
    }

    protected List<Card> startGame(List<Card> hand) {
        int streamCount = 4;
        List<Card> toReturn = new List<Card>();
        while (toReturn.getLength() == 0 && streamCount >= 1) {
            toReturn = createStreamWith(hand, Card.LOWEST_BIG2_CARD, streamCount, -1); //First try to create a 4 stream...-1 will force the highest
            if (toReturn == null) toReturn = new List<Card>();
            streamCount--;
        }

        if (toReturn.getLength() != 0) return toReturn;
        else return getAllCardsLike(Card.LOWEST_BIG2_CARD, hand);
    }


    protected List<Card> startRound(List<Card> hand) {
        int streamCount = 4;
        List<Card> toReturn = new List<Card>();
        while (toReturn.getLength() == 0 && streamCount >= 1) {
            toReturn = createLowestStream(hand, null, streamCount, -1); //First try to create a 4 stream
            if (toReturn == null) toReturn = new List<Card>();
            streamCount--;
        }

        if (toReturn.getLength() != 0) return toReturn;
        else return findLowestCombo(hand);
    }

    protected List<Card> findLowestCard(List<Card> hand, List<Card> toBeat) {
        return findLowestCard(hand, toBeat, false);
    }

    protected List<Card> findLowestCard(List<Card> hand, List<Card> toBeat, boolean goOver) {
        int valueToBeat = toBeat.getEntry(0).getBig2Value(); //Assume all cards have the same raw value
        int count = toBeat.getLength();

        for (Card card : hand) {
            if (card.getBig2Value() > valueToBeat) {
                if (getCardCount(card, hand) >= count && goOver) return clampList(getAllCardsLike(card, hand), count);
                else if (getCardCount(card, hand) == count) return getAllCardsLike(card, hand);
            } else if (card.getBig2Value() >= valueToBeat) {
                if (getCardCount(card, hand) == count || (goOver && getCardCount(card, hand) >= count)) {
                    List<Card> allCards = getAllCardsLike(card, hand);

                    if (getHighestSuitFrom(allCards).isHigher(getHighestSuitFrom(toBeat))) {
                        return clampList(allCards, count);
                    }
                }
            }
        }
        return null;
    }

    protected List<Card> find4OfAKind(List<Card> hand) {
        for (Card card : hand) {
            if (getCardCount(card, hand) == 4)
                return getAllCardsLike(card, hand);
        }
        return null;
    }

    protected List<Card> findHighestCard(List<Card> hand, List<Card> toBeat) {
        return findHighestCard(hand, toBeat, false);
    }

    protected List<Card> findHighestCard(List<Card> hand, List<Card> toBeat, boolean goOver) {
        int valueToBeat = toBeat.getEntry(0).getBig2Value(); //Assume all cards have the same raw value
        int count = toBeat.getLength();
        List<Card> highest = null;

        for (Card card : hand) {
            if (card.getBig2Value() > valueToBeat) {
                if ((getCardCount(card, hand) >= count && goOver) || getCardCount(card, hand) == count) {
                    if (highest == null) highest = clampList(getAllCardsLike(card, hand), count);
                    else {
                        List<Card> rs = clampList(getAllCardsLike(card, hand), count);
                        if (highest.getEntry(0).getBig2Value() < rs.getEntry(0).getBig2Value()) highest = rs;
                        else if (highest.getEntry(0).getBig2Value() == rs.getEntry(0).getBig2Value() && rs.getEntry(0).getSuit().isHigher(highest.getEntry(0).getSuit())) highest = rs;
                    }
                }
            } else if (card.getBig2Value() == valueToBeat) {
                if ((getCardCount(card, hand) >= count && goOver) || getCardCount(card, hand) == count) {
                    List<Card> allCards = getAllCardsLike(card, hand);

                    if (getHighestSuitFrom(allCards).isHigher(getHighestSuitFrom(toBeat))) {
                        allCards = clampList(allCards, count);

                        if (highest == null) highest = allCards;
                        else {
                            if (highest.getEntry(0).getBig2Value() < allCards.getEntry(0).getBig2Value()) highest = allCards;
                            else if (highest.getEntry(0).getBig2Value() == allCards.getEntry(0).getBig2Value() && allCards.getEntry(0).getSuit().isHigher(highest.getEntry(0).getSuit())) highest = allCards;
                        }
                    }
                }
            }
        }
        return highest;
    }

    protected <T> List<T> clampList(List<T> list, int count) {
        List<T> toReturn = new List<T>();
        for (int i = 0; i < count; i++) {
            toReturn.add(list.getEntry(i));
        }
        return toReturn;
    }

    protected List<Card> lowestStreamOrPass(List<Card> hand, List<Card> toBeat) {
        int streamCount = getStreamCount(toBeat); //A stream of 3,4,5 is a stream count of 1. A stream of 3,3,4,4,5,5 is a stream count of 2
        int cardCount = toBeat.getLength() / streamCount; //3,4,5 will be 3, 3,3,4,4,5,5 will be 3, ect..

        Card highestCard = toBeat.getEntry(toBeat.getLength() - streamCount); //The last card in a stream will always be the highest value card

        return createLowestStream(hand, highestCard, streamCount, cardCount);
    }

    protected boolean anyCardHasValueOf(int value, List<Card> cards) {
        for (Card card : cards) {
            if (card.getBig2Value() == value)
                return true;
        }

        return false;
    }

    protected boolean isStartOfStream(List<Card> hand, Card card, Card streamCache) {
        for (int i = 1; i <= 4; i++) {
            List<Card> temp = createStreamWith(hand, card, i, 3, streamCache);
            if (temp != null && temp.getLength() > 0) return true;
        }
        return false;
    }

    protected boolean isStartOfStream(List<Card> hand, Card card) {
        return isStartOfStream(hand, card, null);
    }

    protected List<Card> createStreamWith(List<Card> hand, Card with, int streamCount, int cardCount) {
        return createStreamWith(hand, with, streamCount, cardCount, null);
    }

    protected List<Card> createStreamWith(List<Card> hand, Card with, int streamCount, int cardCount, Card streamCache) {
        List<Card> stream = new List<Card>();

        List<Card> allCards = getAllCardsLike(with, hand, null, true);
        if (allCards.getLength() < streamCount) return null;

        for (int i = 0; i < streamCount; i++) {
            stream.add(allCards.getEntry(i));
        }


        int i = 1;
        int nextValue = with.getBig2Value() + 1;

        while (true) {
            if (nextValue == 15) return null; //We can't have 2's in the stream

            List<Card> nextCards = findCardForStream(hand, nextValue, streamCount);
            if (nextCards == null || nextCards.getLength() == 0 || nextCards.getLength() != streamCount) {
                break;
            }
            stream.addAll(nextCards);

            nextValue++;
            i++;

            if (stream.getLength() / streamCount == cardCount) break;
        }

        if (i >= 3) return stream;
        else return null;
        /*if (nextValue == 15) return null; //We can't have 2's in the stream

        nextCards = findCardForStream(hand, nextValue, streamCount);
        if (nextCards == null || nextCards.getLength() == 0) {
            stream.clear();
            return null;
        }
        stream.addAll(nextCards);

        return stream;*/
    }

    protected List<Card> createLowestStream(List<Card> hand, Card highestCard, int streamCount, int cardCount) {
        List<Card> stream = new List<Card>();
        for (Card card : hand) {
            if (highestCard == null || (card.getBig2Value() == highestCard.getBig2Value() && card.getSuit().isHigher(highestCard.getSuit())) || card.getBig2Value() > highestCard.getBig2Value()) {

                List<Card> s = createStreamWith(hand, card, streamCount, cardCount);
                if (s != null && s.getLength() / streamCount == cardCount) return s;
                /*if (getCardCount(card, hand, null, true) >= streamCount) {
                    List<Card> allCards = getAllCardsLike(card, hand, null, true);
                    for (int i = 0; i < streamCount; i++) {
                        stream.add(allCards.getEntry(i));
                    }
                } else {
                    stream.clear();
                    continue; //We can't use this card as a starting card..
                }


                int nextValue = card.getBig2Value() + 1;
                if (nextValue == 15) return null; //We can't have 2's in the stream

                List<Card> nextCards = findCardForStream(hand, nextValue, streamCount);
                if (nextCards == null || nextCards.getLength() == 0 || nextCards.getLength() != streamCount) {
                    stream.clear();
                    continue; //We can't make a stream with this card..
                }
                stream.addAll(nextCards);

                nextValue++;
                if (nextValue == 15) return null; //We can't have 2's in the stream

                nextCards = findCardForStream(hand, nextValue, streamCount);
                if (nextCards == null || nextCards.getLength() == 0 || nextCards.getLength() != streamCount) {
                    stream.clear();
                    continue; //We can't make a stream with this card..
                }
                stream.addAll(nextCards);

                return stream;*/
            }
        }
        return stream;
    }

    protected Card getHighestCardFrom(List<Card> cards) {
        Card highest = null;
        for (Card card : cards) {
            if (highest == null) highest = card;
            else if (card.getBig2Value() > highest.getBig2Value() || (card.getBig2Value() == highest.getBig2Value() && card.getSuit().isHigher(highest.getSuit()))) highest = card;
        }

        return highest;
    }

    protected List<Card> findCardForStream(List<Card> hand, int nextValue, int streamCount) {
        List<Card> stream = new List<Card>();
        for (Card card : hand) {
            if (card.getBig2Value() == nextValue) {
                if (getCardCount(card, hand, null, true) >= streamCount) {
                    List<Card> allCards = getAllCardsLike(card, hand, null, true);
                    for (int i = 0; i < streamCount; i++) {
                        stream.add(allCards.getEntry(i));
                    }
                } else return null; //We can't make a stream :c

                return stream;
            }
        }

        return null;
    }

    protected List<Card> findHighestCombo(List<Card> hand) {
        List<Card> combo = new List<Card>();

        for (Card card : hand) {
            if (combo.getLength() == 0)
                combo.addAll(getAllCardsLike(card, hand));
            else if (card.getBig2Value() > combo.getEntry(0).getBig2Value() && getCardCount(card, hand) >= combo.getLength()) {
                combo.clear();
                combo.addAll(getAllCardsLike(card, hand));
            }
        }

        return combo;
    }

    protected List<Card> findLowestCombo(List<Card> hand) {
        List<Card> combo = new List<Card>();

        for (Card card : hand) {
            if (combo.getLength() == 0)
                combo.addAll(getAllCardsLike(card, hand));
            else if (card.getBig2Value() < combo.getEntry(0).getBig2Value() && getCardCount(card, hand) >= combo.getLength()) {
                combo.clear();
                combo.addAll(getAllCardsLike(card, hand));
            }
        }

        return combo;
    }

    protected List<Card> findHighestComboOf(List<Card> hand, int limit) {
        List<Card> combo = new List<Card>();

        for (Card card : hand) {
            if (combo.getLength() == 0)
                combo.addAll(getAllCardsLike(card, hand));
            else if (card.getBig2Value() > combo.getEntry(0).getBig2Value() && getCardCount(card, hand) == limit) {
                combo.clear();
                combo.addAll(getAllCardsLike(card, hand));
            }
        }

        return combo;
    }

    protected List<Card> findLowestComboOf(List<Card> hand, int limit) {
        List<Card> combo = new List<Card>();

        for (Card card : hand) {
            if (combo.getLength() == 0 && getCardCount(card, hand) == limit)
                combo.addAll(getAllCardsLike(card, hand));
            else if (combo.getLength() > 0 && card.getBig2Value() < combo.getEntry(0).getBig2Value() && getCardCount(card, hand) == limit) {
                combo.clear();
                combo.addAll(getAllCardsLike(card, hand));
            }
        }

        return combo;
    }

    protected int getComboCount(List<Card> hand, int limit) {
        int count = 0;
        List<Card> cards = new List<Card>();

        for (Card card : hand) {
            if (cards.contains(card)) continue;

            if (getCardCount(card, hand) == limit) {
                count++;
                cards.addAll(getAllCardsLike(card, hand));
            }
        }

        return count;
    }

    protected Card.Suit getHighestSuitFrom(List<Card> cards) {
        Card.Suit suit = null;

        for (Card card : cards) {
            if (suit == null) {
                suit = card.getSuit();
            }
            else if (card.getSuit().isHigher(suit)) {
                suit = card.getSuit();
            }
        }

        return suit;
    }

    protected List<Card> getAllCardsLike(Card card, List<Card> hand) {
        return getAllCardsLike(card, hand, null, false);
    }

    protected List<Card> getAllCardsLike(Card card, List<Card> hand, Card streamCache, boolean ignoreStream) {
        List<Card> toReturn = new List<Card>();
        for (Card c : hand) {
            if (c.getBig2Value() == card.getBig2Value()) {
                if (!ignoreStream && streamCache == null && isStartOfStream(hand, c, c)) {
                    streamCache = c;
                    continue;
                }
                toReturn.add(c);
            }
        }

        return toReturn;
    }

    protected int getCardCount(Card value, List<Card> hand) {
        return getCardCount(value, hand, null, false);
    }

    protected int getCardCount(Card value, List<Card> hand, Card streamCache, boolean ignoreStream) {
        return getAllCardsLike(value, hand, streamCache, ignoreStream).getLength();
    }

    protected boolean isAStream(List<Card> cards) {
        if (cards.getLength() < 3)
            return false;

        int lastValue = cards.getEntry(0).getBig2Value();
        for (int i = 1; i < cards.getLength(); i++) {
            if (cards.getEntry(i).getBig2Value() != lastValue)
                return true;
        }

        return false;
    }

    protected int getStreamCount(List<Card> cards) {
        int count = 1;
        int lastValue = cards.getEntry(0).getBig2Value();
        for (int i = 1; i < cards.getLength(); i++) {
            if (cards.getEntry(i).getBig2Value() == lastValue) count++;
            else return count;
        }
        return count;
    }
}
