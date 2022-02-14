package com.milhama.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardWarGameUtils {
    private int indexForHuman = 0;
    private int indexForComputer = 0;
    private boolean inGame = true;
    private boolean roundActive = true;

    public List<Card> initCardList() {
        List<Card> cardDeck = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 13; j++) {
                cardDeck.add(new Card(j));
            }
        }
        return cardDeck;
    }

    public void startGame(Player human, Player computer) {
        System.out.println("GAME STARTED!!!");
        List<Card> humanCards = human.getPlayerCards();
        List<Card> computerCards = computer.getPlayerCards();
        List<Card> humanWinningCards = new ArrayList<>();
        List<Card> computerWinningCards = new ArrayList<>();
        while (this.inGame) {
            while (this.roundActive) {
                this.indexForHuman = checkIfPlayerOutOfCards(humanCards, humanWinningCards, this.indexForHuman, "human");
                this.indexForComputer = checkIfPlayerOutOfCards(computerCards, computerWinningCards, this.indexForComputer, "computer");
                if (inGame) {
                    checkHand(humanCards, computerCards, humanWinningCards, computerWinningCards);
                }
            }
        }
    }

    private void checkHand(List<Card> humanCards, List<Card> computerCards, List<Card> humanWinningCards, List<Card> computerWinningCards) {
        Card humanCard = humanCards.get(this.indexForHuman);
        Card computerCard = computerCards.get(this.indexForComputer);
        if (humanCard.getValue() > computerCard.getValue()) {
            System.out.println("human won\t\t\t\t " + "h:" + humanCard.getValue() + " , c:" + computerCard.getValue());
            humanWinningCards.add(humanCard);
            humanWinningCards.add(computerCard);
            this.indexForHuman++;
            this.indexForComputer++;
        } else if (humanCard.getValue() < computerCard.getValue()) {
            System.out.println("computer won\t\t\t " + "h:" + humanCard.getValue() + " , c:" + computerCard.getValue());
            computerWinningCards.add(computerCard);
            computerWinningCards.add(humanCard);
            this.indexForHuman++;
            this.indexForComputer++;
        } else {
            handleTie(humanCards, computerCards, humanWinningCards, computerWinningCards);
        }
    }

    private void handleTie(List<Card> humanCards, List<Card> computerCards, List<Card> humanWinningCards, List<Card> computerWinningCards) {
        Card humanCard = humanCards.get(this.indexForHuman);
        Card computerCard = computerCards.get(this.indexForComputer);
        System.out.println("its a tie\t\t\t  " + "\t h:" + humanCard.getValue() + " , c:" + computerCard.getValue());
        ArrayList<Card> tieCardsList = new ArrayList<>();
        tieCardsList.add(humanCard);
        tieCardsList.add(computerCard);
        boolean isTie = true;
        this.indexForHuman++;
        this.indexForComputer++;
        while (isTie) {
            for (int i = 1; i < 4; i++) {
                this.indexForHuman = checkIfPlayerOutOfCards(humanCards, humanWinningCards, this.indexForHuman, "human");
                this.indexForComputer = checkIfPlayerOutOfCards(computerCards, computerWinningCards, this.indexForComputer, "computer");
                if (!this.inGame) {
                    isTie = false;
                    break;
                }
                humanCard = humanCards.get(this.indexForHuman);
                computerCard = computerCards.get(this.indexForComputer);
                System.out.println("card in tie number " + i + "\t h:" + humanCard.getValue() + ", c: " + computerCard.getValue());
                tieCardsList.add(humanCard);
                tieCardsList.add(computerCard);
                this.indexForHuman++;
                this.indexForComputer++;
            }
            if (!isTie) {
                break;
            }
            this.indexForHuman = checkIfPlayerOutOfCards(humanCards, humanWinningCards, this.indexForHuman, "human");
            this.indexForComputer = checkIfPlayerOutOfCards(computerCards, computerWinningCards, this.indexForComputer, "computer");
            if (!this.inGame) {
                isTie = false;
            } else {
                System.out.println("tie breaker \t\t\t h:" + humanCards.get(this.indexForHuman).getValue() + " , c:" + computerCards.get(this.indexForComputer).getValue());
                tieCardsList.add(humanCards.get(this.indexForHuman));
                tieCardsList.add(computerCards.get(this.indexForComputer));
                if (humanCards.get(this.indexForHuman).getValue() < computerCards.get(this.indexForComputer).getValue()) {
                    System.out.println("computer won tie\t\t " + "h:" + humanCards.get(this.indexForHuman).getValue() + " , c:" + computerCards.get(this.indexForComputer).getValue() + "\n");
                    computerWinningCards.addAll(tieCardsList);
                    isTie = false;

                } else if (humanCards.get(this.indexForHuman).getValue() > computerCards.get(this.indexForComputer).getValue()) {
                    System.out.println("human won tie\t\t\t " + "h:" + humanCards.get(this.indexForHuman).getValue() + " , c:" + computerCards.get(this.indexForComputer).getValue() + "\n");
                    humanWinningCards.addAll(tieCardsList);
                    isTie = false;
                }
            }

            this.indexForHuman++;
            this.indexForComputer++;

        }

    }

    private int checkIfPlayerOutOfCards(List<Card> cards, List<Card> winningCards, int index, String name) {
        if (index == cards.size()) {
            System.out.println("\n" + name + " out of cards");
            if (winningCards.isEmpty()) {
                this.inGame = false;
                this.roundActive = false;
                if (name.equalsIgnoreCase("human")) {
                    System.out.println("the winner is computer !!!!!!!!!!!!!!!");
                } else {
                    System.out.println("the winner is human !!!!!!!!!!!!!");
                }
            } else {
                cards.clear();
                Collections.shuffle(winningCards);
                cards.addAll(winningCards);
                winningCards.clear();
                System.out.println("\n" + name + " getting winning cards with size of " + cards.size());
                cards.forEach(x -> System.out.print(x.getValue() + ","));
                System.out.println("\n");
                return 0;
            }
        }
        return index;
    }
}
