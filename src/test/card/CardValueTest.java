package card;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardValueTest {
    CardValue cd = CardValue.ACE;

    @Test
    void getValue() {
        assertEquals(11, cd.getValue());
    }

    @Test
    void values() {
    }

    @Test
    void valueOf() {
    }
}