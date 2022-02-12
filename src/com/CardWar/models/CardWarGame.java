package com.milhama.models;

import java.util.List;

public class CardWarGame {
    CardWarGameUtils utils = new CardWarGameUtils();
    public void start() {
        List<Card> cardDeck = utils.initCardList();
        Dealer dealer = new Dealer(cardDeck);
        dealer.shuffleCard();
        Player human = new Player("human");
        Player computer = new Player("computer");
        dealer.dealCardsToPlayers(human, computer);
        utils.startGame(human, computer);
    }
}
