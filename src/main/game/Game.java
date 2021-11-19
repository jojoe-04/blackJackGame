package game;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.lang.System.*;

public class Game {
    private final List<Player> players;
    private int numberOfPlayers;
    private final Scanner scanner = new Scanner(in);


    public Game() {
        this.players = new CopyOnWriteArrayList<>();
    }

    //TODO refactor
    private void registerPlayer(Player player) throws PlayerAlreadyRegisteredException {

        if(this.isPlayerRegistered(player)) // check if player is not already registered
            throw new PlayerAlreadyRegisteredException();

        players.add(player);
    }

    //TODO refactor
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

    //TODO refactor to use streams and error handling
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

    //TODO refactor to use streams
    public void printPlayers() throws InterruptedException {
        for(Player player : players){
            TimeUnit.SECONDS.sleep(3);
            out.println(player);
        }
    }

    public void ejectPlayer(Player player){
        players.remove(player);
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public List<Player> getPlayers() {
        return players;
    }

    //TODO refactor to use streams
    public void printPlayerCards(){
        for(Player player : players){
            out.println(player.getName() + ": "+ player.getHand());
        }
    }

    //TODO refactor to use streams
    public void printTotalPointsPerPlayer(){
        for(Player player : players){
            out.println(player.getName() + ": "+ player.getPoints());
        }
    }

    //TODO refactor to use streams
    public void printTurnsPerPlayer(){
        for(Player player : players){
            out.println(player.getName() + ": " + player.getTurn());
        }
    }

    //TODO refactor
    public Boolean allPlayersStick(){
        for(Player player : players){
            if(!Objects.equals(player.getTurn(), "stick")) return false;
        }
        out.println("RULE: All Players Stick in This Round");
        return true;
    }

    //TODO refactor
    public Boolean playerHit21(){
        for(Player player : players){
            if(player.getPoints() == 21)
            {
                out.println("RULE: A player hit exactly 21: \n" + player);
                return true;
            }
        }
        return false;
    }

    //TODO refactor
    public Boolean isGameOver() {
        if(Boolean.TRUE.equals(this.playerHit21()))
            return true;

        if(players.size() == 1) {
            out.println("RULE: Only One Player Left");
            return true;
        }

        return Boolean.TRUE.equals(this.allPlayersStick());
    }

    public String getGameRules(){
        return "If the player's total is less than 17, they \"hit\" (i.e. get dealt" +
                "another card from the top of the deck). \n" +
                "If the player's total is 17 or greater, they stick (i.e. don't get dealt another card) \n" +
                "the player's total is greater than 21, they \"go bust\" (i.e. are\n" +
                "ejected from the game) \n";
    }

    //TODO refactor
    public void initializeGame() throws PlayerAlreadyRegisteredException, InterruptedException {
        out.println("\n ------ WELCOME TO THE BLACK JACK GAME ------\n");
        out.println("Here are the game rules -> ");
        out.println(getGameRules());


        //TODO refactor into method and do error handling
        TimeUnit.SECONDS.sleep(3);
        out.print("Enter number of Players: ");

        int numPlayers = scanner.nextInt();
        out.println("Please wait for Game to Setup");
        TimeUnit.SECONDS.sleep(3);

        configurePlayers(numPlayers);   // configure number of players playing game

        List<String> names = this.takePlayerNames();   // take player names

        this.registerPlayers(names);  // register players
        out.println(" ");
        out.println("The List of players playing the game are : ");
        this.printPlayers();
        TimeUnit.SECONDS.sleep(3);

    }

    // //TODO refactor to use Optional
    public Player findWinner() {
        return players.stream().max(Comparator.comparing(Player::getPoints)).get();

    }

}
