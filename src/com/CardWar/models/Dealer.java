package com.milhama.models;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Dealer {
    private List<Card> deck;

    public Dealer(List<Card> deck) {
        this.deck = deck;
    }

    public void shuffleCard() {
        Collections.shuffle(this.deck);
    }

    public void dealCardsToPlayers(Player player, Player computer) {

        int middleOfDeck = this.deck.size() / 2;
        int size = this.deck.size();
        List<Card> playerCards = new ArrayList<>();
        List<Card> computerCards = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (i < middleOfDeck) {
                playerCards.add(this.deck.get(i));
            } else {
                computerCards.add(this.deck.get(i));
            }
        }
        player.setPlayerCards(playerCards);
        computer.setPlayerCards(computerCards);
        this.deck.clear();
    }


    public List<Card> getDeck() {
        return deck;
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
    }

}
