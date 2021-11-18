import card.Card;
import card.CardValue;
import card.Deck;
import card.Suit;
import game.Game;
import game.PlayerAlreadyRegisteredException;

public class App {

    public static void main(String[] args) throws PlayerAlreadyRegisteredException {
        System.out.println("Black Jack Game");
/*
        Card card = new Card(Suit.Heart, CardValue.EIGHT);
        Deck deck = new Deck();
        deck.printCards();

        System.out.println(" ");
        deck.shuffle();
        deck.printCards();*/

        Game game = new Game();
        game.initializeGame();

    }
}
