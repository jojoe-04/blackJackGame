package card;

import java.util.ArrayList;
import java.util.Collections;

import static java.lang.System.*;

/**
 * The type Deck.
 */
public class Deck {
    private final ArrayList<Card> cards;

    /**
     * Instantiates a new Deck.
     */
    public Deck() {
        cards = new ArrayList<>();
        for (Suit s : Suit.values()) { // loop through enums and insert cards in ArrayList<cards>
            for (CardValue v: CardValue.values()) {
                this.cards.add(new Card(s,v));
            }
        }
    }

    /**
     * Shuffle.
     */
    public void shuffle(){
        Collections.shuffle(this.cards);
    }


    /**
     * Print cards.
     */
    public void printCards() {
        cards.forEach(out::println);
    }

    /**
     * Pop card.
     *
     * @return the card
     */
    public Card pop(){
        Card last = cards.get(cards.size() - 1);
        cards.remove(cards.size() - 1);
        return last;
    }


    public ArrayList<Card> getCards() {
        return cards;
    }
}
