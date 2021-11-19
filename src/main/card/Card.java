package card;

import java.util.Objects;

/**
 * The type Card.
 */
public class Card implements Comparable<Card> {

    private final Suit suit;
    private final CardValue cardValue;

    /**
     * Instantiates a new Card.
     *
     * @param suit      the suit
     * @param cardValue the card value
     */
    public Card(Suit suit, CardValue cardValue) {
        this.suit = suit;
        this.cardValue = cardValue;
    }

    /**
     * Gets suit.
     *
     * @return the suit
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Gets card value.
     *
     * @return the card value
     */
    public CardValue getCardValue() {
        return cardValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return getSuit() == card.getSuit() && getCardValue() == card.getCardValue();
    }

    /**
     * Overridden Hashcode Method
     * @return  hashcode for two objects
     */
    @Override
    public int hashCode() {
        return Objects.hash(getSuit(), getCardValue());
    }

    /**
     * Compare to Card Objects
     * @param other - another card to compare
     * @return compareTo value
     */
    @Override
    public int compareTo(Card other) {
        return this.cardValue.compareTo(other.cardValue);
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit=" + suit +
                ", cardValue=" + cardValue +
                '}';
    }
}
