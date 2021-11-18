package game;

import card.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private String playerId;
    private int points;
    private List<Card> hand;
    private String turn;

    public Player(String playerId, String name) {
        this.playerId = playerId;
        this.name = name;
        this.points = 0;
        this.hand = new ArrayList<Card>();
        this.turn = "";
    }

    public void addToHand(Card card) {
        this.hand.add(card);
    }

    public String getPlayerId() {
        return playerId;
    }

    public int getPoints() {
        this.caculatePoints();
        return points;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void calculateTurns() {
        if(this.getPoints() < 17) turn = "hit";
        if(this.getPoints() >= 17) {turn = "stick";}
        if(this.getPoints() > 21) {turn = "bust";}
    }

    public String getTurn() {
        this.calculateTurns();
        return turn;
    }

    public String getName() {
        return name;
    }

    // refactor to use stream
    public void caculatePoints(){
        for(Card c : this.hand){
            this.points += c.getCardValue().getValue();
        }

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
