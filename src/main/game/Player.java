package game;

import card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Player.
 */
public class Player {
    private final String name;
    private final String playerId;
    private int points;
    private final List<Card> hand;
    private String turn;
    private String strategy = "normal";

    /**
     * Instantiates a new Player.
     *
     * @param playerId the player id
     * @param name     the player name
     */
    public Player(String playerId, String name) {
        this.playerId = playerId;
        this.name = name;
        this.points = 0;
        this.hand = new ArrayList<>();
        this.turn = "";
    }

    /**
     * Add card to player hand list
     *
     * @param card the card
     */
    public void addToHand(Card card) {
        this.hand.add(card);
    }

    /**
     * Gets hand list of player cards
     *
     * @return the hand
     */
    public List<Card> getHand() {
        return hand;
    }

    /**
     * Uses Streams to Calculate Points of Cards in Player's hands
     */
    private void calculatePoints() {
        this.points = 0;
        this.points = hand.stream().map(card -> card.getCardValue().getValue()).reduce(0,Integer::sum);
    }

    /**
     * Calculate points & Gets points.
     *
     * @return the points
     */
    public int getPoints() {
        this.calculatePoints();
        return points;
    }

    /**
     * Calculate turns by assigning turn value to player based on points
     */
    public void calculateTurns() {
        if(this.getPoints() < 17) turn = "hit";
        if(this.getPoints() >= 17) turn = "stick";
        if(this.getPoints() > 21) turn = "bust";
    }

    /**
     * Calculate always hit.
     */
    public void calculateAlwaysHit() {
        if(this.getPoints() < 21) turn = "hit";
        if(this.getPoints() > 21) turn = "bust";
    }

    /**
     * Calculate always stick.
     */
    public void calculateAlwaysStick() {
        turn = "stick";
    }

    /**
     * Assign turns by strategy.
     *
     * @param action the action
     */
    public void assignTurnsByStrategy(String action) {
        if(Objects.equals(action, "normal")){
            this.strategy = "normal";
            calculateTurns();
        }
        if (Objects.equals(action, "always-stick")) {
            this.strategy = "always-stick";
            calculateAlwaysStick();
        }

        if (Objects.equals(action, "always-hit")) {
            this.strategy = "always-hit";
            calculateAlwaysHit();
        }
    }

    /**
     * Calculates turns and return player's turn
     *
     * @return turn
     */
    public String getTurn() {
        assignTurnsByStrategy(strategy);
        return turn;
    }

    /**
     * Gets name of player
     * @return the name
     */
    public String getName() {
        return name;
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
