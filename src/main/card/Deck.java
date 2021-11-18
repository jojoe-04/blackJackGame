package card;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<Card>();
        for (Suit s : Suit.values()) {
            for (CardValue v: CardValue.values()) {
                this.cards.add( new Card(s,v));
            }
        }
    }

    public void shuffle(){
        Collections.shuffle(this.cards);
    }


    public void printCards() {
        for(Card card : cards) {
           System.out.println(card) ;
        }
    }

}
