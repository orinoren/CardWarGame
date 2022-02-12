package com.milhama.models;

import java.util.List;

public class Player {
    private String name;
    private List<Card> playerCards;

    public Player(String name) {
        this.name = name;
    }

    public List<Card> getPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(List<Card> playerCards) {
        this.playerCards = playerCards;
    }
}
