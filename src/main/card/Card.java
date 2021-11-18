package card;

import java.util.Objects;

public class Card implements Comparable<Card> {

    private Suit suit;
    private CardValue cardValue;

    public Card(Suit suit, CardValue cardValue) {
        this.suit = suit;
        this.cardValue = cardValue;
    }

    public Suit getSuit() {
        return suit;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(getSuit(), getCardValue());
    }

    /**
     * Compares value of Two Cards
     * @param other Card Object
     * @return comparison of cards
     */
    @Override
    public int compareTo(Card other) {
        return this.cardValue.compareTo(other.cardValue);
    }
}
