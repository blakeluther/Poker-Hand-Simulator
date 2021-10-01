package proj4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {

    private List<Card> deckOfCards;
    private int nextToDeal = 0; // identifies the next index that will be dealt out

    /**
     * Initializes the deck of cards if user were to initiate a new reference to the deck class
     */
    public Deck(){
        int[] SUITS = {0,1,2,3};
        int[] RANKS = {2,3,4,5,6,7,8,9,10,11,12,13,14};

        this.deckOfCards = new ArrayList<>();

        for (int suit : SUITS){
            for (int rank : RANKS){
                deckOfCards.add(new Card(rank, suit));
            }
        }
    }

    /**
     * Randomizes the location of each card by taking the index of one card,
     * and another and swaps the index of both.
     * Note: Only shuffles un-dealt cards if you were to draw cards first
     */
    public void shuffle(){
        Random rand = new Random();
        for (int cardIndex = nextToDeal; cardIndex < deckOfCards.size(); cardIndex++){
            int firstCard = rand.nextInt(deckOfCards.size());
            int secondCard = rand.nextInt(deckOfCards.size());
            Collections.swap(deckOfCards, firstCard, secondCard);
        }
    }

    /**
     * "Deals" the next Card that is on top of the Deck of Cards
     * Will find the card that has the index of int nextToDeal
     * If deck size is 0, then it will return null.
     * @return null, if deck size is 0, otherwise the Card on top of the deck
     */
    public Card deal() {
        if (deckOfCards.isEmpty()) {
            return null;
        }
        Card dealtCard = deckOfCards.get(nextToDeal);
        nextToDeal++;
        return dealtCard;
    }

    /**
     * Calculates the number of cards left that can be dealt out,
     * by removing the number of cards already dealt by the int nextToDeal
     * @return An integer that represents the number of cards in the deck
     */
    public int size(){
        return deckOfCards.size() - nextToDeal;
    }

    /**
     * Re-sets the variable nextToDeal back to zero,
     * to signify that all cards can be dealt again
     */
    public void gather(){
        nextToDeal = 0;
    }

    public boolean isEmpty(){
        return nextToDeal == 52;
    }

    /**
     * Creates a large string that consists of every card left in the deck,
     * One line consists a the Card number, and the readable version of the card
     * @return String that consists of the cards still in the deck.
     */
    public String toString(){
        System.out.println(nextToDeal);
        StringBuilder myDeck = new StringBuilder();
        for (int cardIndex = nextToDeal + 1; cardIndex <= deckOfCards.size(); cardIndex++){
            myDeck.append("Card ").append(cardIndex).append(": ").append(deckOfCards.get(cardIndex - 1)).append("\n");
        }
        return myDeck.toString();
    }
}
