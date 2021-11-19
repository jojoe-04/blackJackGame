package game;

import card.Deck;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static java.lang.System.*;

public  class GamePlay {
    private static final Deck deck = new Deck();
    private static int roundValue = 0;
    private static final Game blackjack = new Game();

    private GamePlay() {
    }

    public static String playRound() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        out.println("-------> GAME ROUND " + roundValue);

        if(Boolean.TRUE.equals(blackjack.isGameOver())) {
            out.println(" ");
            out.println("------------ GAME OVER ----------\n\n");

            TimeUnit.SECONDS.sleep(2);
            logPlayers();
            TimeUnit.SECONDS.sleep(2);
            logWinner();

            return "over";
        }

        out.println("\n{log} < game continued.....>\n\n");
        roundValue++;
        return "continue";
    }

    public static void continueGamePlay() throws InterruptedException {
        String gameStatus = playRound();
        if("over".equals(gameStatus)) return; // don't continue when game is not over

        while("continue".equals(gameStatus)){

            TimeUnit.SECONDS.sleep(2);

            for (Player player : blackjack.getPlayers()) {
                if (Objects.equals(player.getTurn(), "bust")) {
                    blackjack.ejectPlayer(player);
                    out.println("Ejected from Game Player: " + player.getName());
                }
                if (Objects.equals(player.getTurn(), "hit")) {
                    out.println(player.getName() + ": has a HIT on turn, a new card from deck will be popped");
                    TimeUnit.SECONDS.sleep(2);
                    player.addToHand(deck.pop());
                    out.println("New Card Handed for Player: " + player.getName());
                }
            }
            logGamePlay();
            TimeUnit.SECONDS.sleep(1);
            gameStatus = playRound();
        }
    }

    public static void handCards(Player player, int numOfCards){
        for(int i = 0; i < numOfCards; i++){
            player.addToHand(deck.pop());
        }
    }

    private static void logWinner() {
        out.println("\nWinner of Game is: ");
        out.println(blackjack.findWinner());
    }

    private static void logPlayers() throws InterruptedException {
        out.println("\nCurrent Players In Game: ");
        blackjack.printPlayers();
    }

    private static void logPoints(){
        out.println("\nThese are your card totals");
        blackjack.printTotalPointsPerPlayer();
    }

    private static void logCards(){
        out.println("\nTake a Look at your cards");
        blackjack.printPlayerCards();
    }

    private static void logTurns(){
        out.println("\nThese are your turns");
        blackjack.printTurnsPerPlayer();
    }

    public static void logGamePlay() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        logCards();

        TimeUnit.SECONDS.sleep(3);
        logPoints();

        TimeUnit.SECONDS.sleep(3);
        logTurns();
    }

    public  static void startGamePlay() throws PlayerAlreadyRegisteredException, InterruptedException {
        blackjack.initializeGame(); // initialize the Game
        TimeUnit.SECONDS.sleep(2);

        out.println("\nPlease wait for Game to Setup");
        TimeUnit.SECONDS.sleep(2);
        logPlayers(); // get current players in game

        TimeUnit.SECONDS.sleep(2);
        deck.shuffle(); // shuffle Deck

        for(Player player: blackjack.getPlayers()) {
            handCards(player, 2); // hand two Cards at beginning to each player
        }
        out.println("{log} > ...two Cards Have been Added to Each Player...");

        TimeUnit.SECONDS.sleep(3);
        logGamePlay();
    }

}
