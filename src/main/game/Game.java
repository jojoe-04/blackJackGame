package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private List<Player> players;
    private int numberOfPlayers;
    private Scanner scanner = new Scanner(System.in);


    public Game() {
        this.players = new ArrayList<Player>();
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

    private void configurePlayers(int numPlayers) throws PlayerAlreadyRegisteredException{
        this.numberOfPlayers = numPlayers;
    }

    public List<String> takePlayerNames(){
        List<String> names = new ArrayList<String>();

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

    /**
     * Set up Players For the Game
     * @throws PlayerAlreadyRegisteredException
     */
    public void initializeGame() throws PlayerAlreadyRegisteredException{
        System.out.println("BLACK JACK GAME HAS STARTED");
        System.out.print("Enter number of Players: ");

        int numPlayers = scanner.nextInt();
        System.out.println("Please wait for Game to Setup");

        // configure number of players playing game
        configurePlayers(numPlayers);

        // take player names
        List<String> names = this.takePlayerNames();
        System.out.println(names);


        // register players
        this.registerPlayers(names);
        this.printPlayers();
    }
}
