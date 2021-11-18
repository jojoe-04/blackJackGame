import card.Card;
import card.CardValue;
import card.Deck;
import card.Suit;

public class gApp {

    public static void main(String[] args) {
        System.out.println("Black Jack Game");

        Card card = new Card(Suit.Heart, CardValue.EIGHT);
        Deck deck = new Deck();
        deck.printCards();

        System.out.println(" ");
        deck.shuffle();
        deck.printCards();
    }
}
