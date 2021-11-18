package card;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Deck Class
 * Deck[collection] of Cards[object] of 4 Suits[object]
 */
public class Deck {
    private ArrayList<Card> cards;

    /**
     * Constructor to initialize a Deck object with cards
     */
    public Deck() {
        cards = new ArrayList<Card>();
        for (Suit s : Suit.values()) { // loop through enums and insert cards in ArrayList<cards>
            for (CardValue v: CardValue.values()) {
                this.cards.add(new Card(s,v));
            }
        }
    }

    //
    public void shuffle(){
        Collections.shuffle(this.cards);
    }


    public void printCards() {
        for(Card card : cards) {
           System.out.println(card) ;
        }
    }

}
