package game;

import card.Deck;

import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static java.lang.System.*;

public class GamePlay {
    private static final Deck deck = new Deck();
    private static int roundValue = 0;
    private static final Game blackjack1 = new Game();

    //TODO refactor
    public static String aRound() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        out.println("-------> GAME ROUND " + roundValue);

        //TODO refactor
        if(Boolean.TRUE.equals(blackjack1.isGameOver())) {  //Check if Game is Over First
            out.println(" ");
            out.println("------------ GAME OVER ----------\n\n");

            TimeUnit.SECONDS.sleep(3);
            out.println("Players Left in the Game: ");
            blackjack1.printPlayers();

            TimeUnit.SECONDS.sleep(3);
            out.println("\nWinner of Game is: ");
            out.println(blackjack1.findWinner());
            return "over";
        }

        out.println(
                "{log} > game continued.....\n\n"
        );

        roundValue++;
        return "continue";

    }

    //TODO refactor
    public static void continueGamePlay() throws InterruptedException {
        String gameStatus = aRound();

        if("over".equals(gameStatus)) return;

        while("continue".equals(gameStatus)){
            TimeUnit.SECONDS.sleep(2);

            //TODO refactor to eahanced for Loop
            for(Iterator<Player> it = blackjack1.getPlayers().iterator(); it.hasNext(); ){
                Player player = it.next();

                // TODO throwing exception, must be handled
                if(Objects.equals(player.getTurn(), "bust")) {
                        blackjack1.ejectPlayer(player);
                        out.println("Ejected from Game Player: " + player.getName());
                }
                if(Objects.equals(player.getTurn(), "hit")){
                        out.println(player.getName() + ": has a HIT on turn, a new card from deck will be popped");
                        TimeUnit.SECONDS.sleep(3);
                        player.addToHand(deck.pop());
                        out.println("New Card Handed for Player: " + player.getName());
                }
            }
            processGamePlay();
            TimeUnit.SECONDS.sleep(1);
            gameStatus = aRound();
        }
    }

    //TODO refactor ( take parameter of cards & use streams )
    public static void handTwoCards(Player player){

        player.addToHand(deck.pop());
        player.addToHand(deck.pop());
    }

    //TODO refactor
    public static void processGamePlay() throws InterruptedException {
        out.println(" ");
        out.println("Take a Look at your cards");
        blackjack1.printPlayerCards();

        TimeUnit.SECONDS.sleep(5);
        out.println(" ");
        out.println("These are your card totals");
        blackjack1.printTotalPointsPerPlayer();

        TimeUnit.SECONDS.sleep(3);
        out.println(" ");
        out.println("These are your turns");
        blackjack1.printTurnsPerPlayer();
        TimeUnit.SECONDS.sleep(3);
    }

    //TODO refactor
    public static void startGamePlay() throws PlayerAlreadyRegisteredException, InterruptedException {

        blackjack1.initializeGame(); // initialize the Game
        deck.shuffle(); // shuffle Deck

        for(Player player: blackjack1.getPlayers()) { // Hand two Cards at beginning
            handTwoCards(player);
        }
        out.println("{log} > Two Cards Have been Added to Each Player...");

        TimeUnit.SECONDS.sleep(3);
        processGamePlay();


    }


    public static void main(String[] args) throws PlayerAlreadyRegisteredException, InterruptedException {
        startGamePlay();
        TimeUnit.SECONDS.sleep(3);
        continueGamePlay();



    }

}
