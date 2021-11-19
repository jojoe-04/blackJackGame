
import game.GamePlay;
import game.PlayerAlreadyRegisteredException;

public class App {

    public static void main(String[] args) throws PlayerAlreadyRegisteredException, InterruptedException {
        GamePlay.startGamePlay();
       GamePlay.continueGamePlay();
    }
}
