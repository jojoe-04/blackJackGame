package card;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    Deck deck = new Deck();

    @Test
    void shuffle() {

        // check if order is different
    }

    @Test
    void pop() {
    }

    @Test
    void totalNumberOfCards() {
        assertEquals(52, deck.getCards().size());
    }
}