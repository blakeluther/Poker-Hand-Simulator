package proj4;

import java.util.ArrayList;

public class CommunityCardSet {

    private ArrayList<Card> cardList;

    /**
     * Initializes a Community Card object
     * @param cardList
     */
    public CommunityCardSet(ArrayList<Card> cardList){
        this.cardList = cardList;
    }

    /**
     * Adds a Card object to the community Hand
     * If the size of the hand is greater is 5,
     * it will not add the card
     * @param c the Card object we're adding
     */
    public void addCard(Card c){
        int MAX_COMMUNITY_SIZE = 5;
        if (cardList.size() < MAX_COMMUNITY_SIZE){
            cardList.add(c);
        }
    }

    /**
     * Returns the card at the given index; assuming index is valid in the Hand
     * @param cardIndex the location of the card you want to get
     * @return a Card object
     */
    public Card getIthCard(int cardIndex){
        if (cardIndex < 0 || cardIndex > cardList.size()){
            return null;
        } else {
            return cardList.get(cardIndex);
        }
    }

    /**
     * Gives the current size of the Community Hand
     * @return int - representing the number of cards in the hand
     */
    public int getCommunityCardSize(){
        return cardList.size();
    }

    /**
     * Creates a string of the Cards inside the Community Hand
     * @return String of Cards in Community Hand
     */
    public String toString() {
        StringBuilder cardListString = new StringBuilder("");
        cardListString.append(getIthCard(0)).append(" ").append("|");
        for (int card = 1; card < cardList.size(); card++)
        {
            if (card == cardList.size() - 1)
            {
                cardListString.append(" ").append(getIthCard(card));
            } else {
                cardListString.append(" ").append(getIthCard(card)).append(" ").append("|");
            }
        }
        return cardListString.toString();
    }
}
