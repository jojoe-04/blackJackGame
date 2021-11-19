package game;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.lang.System.*;

/**
 * The type Game.
 */
public class Game {
    private final List<Player> players;
    private int numberOfPlayers;
    private final Scanner scanner = new Scanner(in);

    /**
     * Instantiates a new Game.
     */
    public Game() {
        this.players = new CopyOnWriteArrayList<>();
    }

    private void registerPlayer(Player player) throws PlayerAlreadyRegisteredException {
        if(this.isPlayerRegistered(player)) // check if player is not already registered
            throw new PlayerAlreadyRegisteredException();
        players.add(player);
    }

    /**
     * Register players.
     *
     * @param playerNames the player names
     * @throws PlayerAlreadyRegisteredException the player already registered exception
     */
    public void registerPlayers(List<String> playerNames) throws PlayerAlreadyRegisteredException {
        for(int i=0; i < playerNames.size(); i++){
            Player randomPlayer = new Player(("00"+ i),playerNames.get(i));
            this.registerPlayer(randomPlayer);
        }
    }

    private boolean isPlayerRegistered(Player player) {
        return players.contains(player);
    }

    private void configurePlayers(int numPlayers){
        this.numberOfPlayers = numPlayers;
    }

    /**
     * Take player names list.
     *
     * @return the list
     */
    public List<String> takePlayerNames(){
        List<String> names = new ArrayList<>();
        String name = scanner.nextLine();
        for(int i = 1; i < numberOfPlayers+1; i++){
            out.print("Enter Player " + i + " Name: ");
            name = scanner.nextLine();
            names.add(name);
        }
        return names;
    }

    /**
     * Print players.
     *
     * @throws InterruptedException the interrupted exception
     */
    public void printPlayers() throws InterruptedException {
        for (Player player : players) {
            TimeUnit.SECONDS.sleep(1);
            out.println(player);
        }
    }

    /**
     * Eject player.
     *
     * @param player the player
     */
    public void ejectPlayer(Player player){
        players.remove(player);
    }

    /**
     * Gets number of players.
     *
     * @return the number of players
     */
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /**
     * Gets players.
     *
     * @return the players
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Print player cards.
     */
    public void printPlayerCards(){
        players.forEach(player ->  out.println(player.getName() + ": "+ player.getHand()));
    }

    /**
     * Print total points per player.
     */
    public void printTotalPointsPerPlayer(){
        players.forEach(player -> out.println(player.getName() + ": "+ player.getPoints()));
    }

    /**
     * Print turns per player.
     */
    public void printTurnsPerPlayer(){
        players.forEach(player -> out.println(player.getName() + ": " + player.getTurn()));
    }

    /**
     * All players stick boolean.
     *
     * @return the boolean
     */
    public Boolean allPlayersStick(){
        for(Player player : players){
            if(!Objects.equals(player.getTurn(), "stick")) return false;
        }
        out.println("RULE: All Players Stick in This Round");
        return true;
    }

    /**
     * Player hit 21 boolean.
     *
     * @return the boolean
     */
    public Boolean playerHit21(){
        for(Player player : players){
            if(player.getPoints() == 21) {
                out.println("RULE: A player hit exactly 21: \n" + player);
                return true;
            }
        }
        return false;
    }

    /**
     * Is game over boolean.
     *
     * @return the boolean
     */
    public Boolean isGameOver() {
        if(Boolean.TRUE.equals(this.playerHit21()))
            return true;

        if(players.size() == 1) {
            out.println("RULE: Only One Player Left");
            return true;
        }
        return Boolean.TRUE.equals(this.allPlayersStick());
    }

    /**
     * Get game rules string.
     *
     * @return the string
     */
    public String getGameRules(){
        return "If the player's total is less than 17, they \"hit\" (i.e. get dealt" +
                "another card from the top of the deck). \n" +
                "If the player's total is 17 or greater, they stick (i.e. don't get dealt another card) \n" +
                "the player's total is greater than 21, they \"go bust\" (i.e. are\n" +
                "ejected from the game) \n";
    }

    /**
     * Sets up number of players.
     *
     * @return the up number of players
     */
    public int setUpNumberOfPlayers() {
            out.print("Enter number of Players: ");
            String numPlayers = scanner.nextLine();

            if(Objects.equals(numPlayers, "")){
                out.println("No Input was Entered! Default Number of Players: 3");
                return 3;
            }
            while (Integer.parseInt(numPlayers)  <= 1 || Integer.parseInt(numPlayers ) >= 6) {
                out.println("Wrong Input! Enter a number greater than 0 and lesser than 6");
                out.print("Enter number of Players: ");
                numPlayers = scanner.next();
            }
            return Integer.parseInt(numPlayers);
    }

    /**
     * Add players to game.
     *
     * @throws PlayerAlreadyRegisteredException the player already registered exception
     */
    public void addPlayersToGame() throws PlayerAlreadyRegisteredException {
        List<String> names = this.takePlayerNames();// take player names
        this.registerPlayers(names);  // register players
    }

    /**
     * Initialize game.
     *
     * @throws PlayerAlreadyRegisteredException the player already registered exception
     */
    public void initializeGame() throws PlayerAlreadyRegisteredException {
        out.println("\n ------ WELCOME TO THE BLACK JACK GAME ------\n");
        out.println("Here are the game rules: ");
        out.println(getGameRules());

        configurePlayers(setUpNumberOfPlayers());   // configure number of players playing game
        addPlayersToGame(); // Get player names and Add players to game
        chooseStrategy();
        out.println("Please wait for Game to Setup");
    }

    /**
     * Find winner player.
     *
     * @return the player
     */
    public Player findWinner() {
        Optional<Player> player = Optional.of(players.stream().max(Comparator.comparing(Player::getPoints))).get();
        return player.orElseGet(() -> new Player("#####", "No Winner"));
    }

    /**
     * Choose strategy.
     */
    public void chooseStrategy(){
        out.println("\nThe List of Strategies Available are: always-hit, always-stick, normal");
        int index = 1;
        for (Player p : players){
            out.print("Enter Player" + (index++) + " strategy: ");
            String strategy = scanner.next();
            while(true) {
                if(Objects.equals(strategy, "normal") || Objects.equals(strategy, "always-hit") || Objects.equals(strategy, "always-stick")) break;
                else {
                    out.println("This Strategy Doest Not Exist, Enter either of [ always-hit, always-stick, normal ]");
                    out.print("Enter another strategy: ");
                    strategy = scanner.next();
                }
            }
            p.assignTurnsByStrategy(strategy);
        }
    }
}
