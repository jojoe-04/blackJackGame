package game;

import card.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private String playerId;
    private int points ;
    private List<Card> hand;
    private Boolean turn;

    public Player(String playerId, String name) {
        this.playerId = playerId;
        this.name = name;
        this.points = 0;
        this.hand = new ArrayList<Card>();
        this.turn = false;
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

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", playerId='" + playerId + '\'' +
                ", points=" + points +
                ", hand=" + hand +
                ", turn=" + turn +
                '}';
    }
}
