package game;

import card.Deck;

import java.util.concurrent.TimeUnit;

public class GamePlay {
    private static Deck deck = new Deck();
    private static int roundValue = 0;
    private Boolean roundStatus; // true -> round is onGoing false -> round has ended
    private static final Game blackjack1 = new Game();

    public static void aRound(){


        //Check if Game is Over First
        if(Boolean.TRUE.equals(blackjack1.isGameOver())) {
            System.out.println(" ");
            System.out.println("GAME OVER");
            return;
        }

        System.out.println("GAME CONTINUE");
        roundValue++; // increment round




        // a round is played
        // roundValue is incremented
       // roundStatus changes

        //checks if gameIsOver

    }

    public static void handTwoCards(Player player){
        player.addToHand(deck.pop());
        player.addToHand(deck.pop());
    }

    public static void startGamePlay() throws PlayerAlreadyRegisteredException, InterruptedException {

        blackjack1.initializeGame(); // initialize the Game
        deck.shuffle(); // shuffle Deck

        for(Player player: blackjack1.getPlayers()) { // hand two Cards at beginning
            handTwoCards(player);
        }

        System.out.println(" ");
        System.out.println("The cards have been handed to each player");
        blackjack1.printPlayerCards();

        TimeUnit.SECONDS.sleep(2);
        System.out.println(" ");
        System.out.println("These are your totals");
        blackjack1.printTotalPointsPerPlayer();

        System.out.println(" ");
        System.out.println("These are turns");
        blackjack1.printTurnsPerPlayer();


    }


    public static void main(String[] args) throws PlayerAlreadyRegisteredException, InterruptedException {
        startGamePlay();
        TimeUnit.SECONDS.sleep(2);
        aRound();



    }

}
