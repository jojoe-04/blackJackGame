package card;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    Card card = new Card(Suit.Heart, CardValue.EIGHT);
    Card cardSame = new Card(Suit.Heart, CardValue.EIGHT);

    @Test
    void getSuit() {
        assertEquals(Suit.Heart, card.getSuit());
    }

    @Test
    void getCardValue() {
        assertEquals(CardValue.EIGHT, card.getCardValue());
    }

    @Test
    void testEquals() {
        assertEquals(card, cardSame);
        assertTrue(card.equals(cardSame));
    }

    @Test
    void compareTo() {
        assertEquals(0, card.compareTo(cardSame));
    }
}