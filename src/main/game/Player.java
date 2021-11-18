package game;

import card.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String playerId;
    private int points ;
    private List<Card> hand;
    private Boolean turn;

    public Player(String playerId, int points, List<Card> hand, Boolean turn) {
        this.playerId = playerId;
        this.points = points;
        this.hand = hand;
        this.turn = turn;
    }

    public String getPlayerId() {
        return playerId;
    }

    public int getPoints() {
        return points;
    }

    public List<Card> getHand() {
        return hand;
    }

    public Boolean getTurn() {
        return turn;
    }
}
