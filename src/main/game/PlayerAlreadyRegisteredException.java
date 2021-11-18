package game;

public class PlayerAlreadyRegisteredException extends Exception {

    public PlayerAlreadyRegisteredException() {
        super("Player Already Registered");
    }
}
