package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game {
    private final List<Player> players;
    private int numberOfPlayers;
    private final Scanner scanner = new Scanner(System.in);


    public Game() {
        this.players = new ArrayList<>();
    }

    private void registerPlayer(Player player) throws PlayerAlreadyRegisteredException {

        if(this.isPlayerRegistered(player)) // check if player is not already registered
            throw new PlayerAlreadyRegisteredException();

        players.add(player);
    }

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

    public List<String> takePlayerNames(){
        List<String> names = new ArrayList<>();

        String name = scanner.nextLine();
        for(int i = 0; i < numberOfPlayers; i++){
            System.out.print("Enter Player Name: ");
            name = scanner.nextLine();
            names.add(name);
        }
        return names;
    }

    public void printPlayers(){
        for(Player player : players){
            System.out.println(player);
        }
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public List<Player> getPlayers() {
        return players;
    }


    public void printPlayerCards(){
        for(Player player : players){
            System.out.println(player.getName() + ": "+ player.getHand());
        }
    }

    public void printTotalPointsPerPlayer(){
        for(Player player : players){
            System.out.println(player.getName() + ": "+ player.getPoints());
        }
    }

    public void printTurnsPerPlayer(){
        for(Player player : players){
            System.out.println(player.getName() + ": " + player.getTurn());
        }
    }

    public Boolean allPlayersStick(){
       // for all players if all one player status is not stick -> return false
        for(Player player : players){
            if(Objects.equals(player.getTurn(), "stick")) return false;
        }

        System.out.println("RULE: All Players Stick in This Round");
        return true;
    }

    public Boolean playerHit21(){
        for(Player player : players){
            if(player.getPoints() == 21)
            {
                System.out.println("RULE: A player hit exactly 21" + player);
                return true;
            }
        }
        return false;
    }


    public Boolean isGameOver() {
        if(Boolean.TRUE.equals(this.playerHit21()))
            return true;

        if(players.size() == 1) {
            System.out.println("RULE: Only One Player Left");
            return true;
        }

        return Boolean.TRUE.equals(this.allPlayersStick());
    }

    /**
     * Set up Players For the Game
     * @throws PlayerAlreadyRegisteredException
     */
    public void initializeGame() throws PlayerAlreadyRegisteredException, InterruptedException {
        System.out.println("BLACK JACK GAME HAS STARTED");
        System.out.print("Enter number of Players: ");

        int numPlayers = scanner.nextInt();
        System.out.println("Please wait for Game to Setup");
        TimeUnit.SECONDS.sleep(2);

        configurePlayers(numPlayers);   // configure number of players playing game

        List<String> names = this.takePlayerNames();   // take player names

        this.registerPlayers(names);  // register players
        System.out.println(" ");
        System.out.println("The List of players playing the game are : ");
        this.printPlayers();

    }

    public void endGame(){


    }
}
