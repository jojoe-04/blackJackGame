package card;

/**
 * The enum Card value.
 */
public enum CardValue {
    /**
     * Two card value.
     */
    TWO(2),
    /**
     * Three card value.
     */
    THREE(3),
    /**
     * Four card value.
     */
    FOUR(4),
    /**
     * Five card value.
     */
    FIVE(5),
    /**
     * Six card value.
     */
    SIX(6),
    /**
     * Seven card value.
     */
    SEVEN(7),
    /**
     * Eight card value.
     */
    EIGHT(8),
    /**
     * Nine card value.
     */
    NINE(9),
    /**
     * Ten card value.
     */
    TEN(10),
    /**
     * Jack card value.
     */
    JACK(10),
    /**
     * Queen card value.
     */
    QUEEN(10),
    /**
     * King card value.
     */
    KING(10),
    /**
     * Ace card value.
     */
    ACE(11);

    private final int value;

    CardValue(int value) {
        this.value = value;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public int getValue() {
        return value;
    }

}
