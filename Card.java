package proj4;

public class Card {

    private final int rank;
    private final int suit;

    /**
     * Creates a Card with a given rank and suit.
     *
     * @param rank whole cards (2-10) can either be spelled
     *              out like "two" or numeric like "2". Case
     *              insensitive.
     * @param suit "Spades", "Hearts", "Clubs", or "Diamonds"
     */

    public Card(String rank, String suit) {
        this.rank = getRankNumber(rank);
        this.suit = getSuitNumber(suit);
    }

    /**
     * Creates a Card with a given rank and suit.
     *
     * @param rank The rank of the card, which must be between
     *              2 and 14, inclusive.
     * @param suit The suit of the card, which must be
     *              0=SPADES, 1=HEARTS, 2=CLUBS, or 3=DIAMONDS
     */

    public Card(int rank, int suit) {
        this.rank = rank;
        this.suit = suit;

    }

    /**
     * Converts the String Card's rank to a int value for internal computations
     * Allows for both types of cards to use the same methods
     * @param rank value of card (2-14)
     * @return the integer value of the String Card to be used
     */
    private int getRankNumber(String rank) {
        int rankValue = 0;
        if (rank.equals("two") || rank.equals("Two") || rank.equals("2")) {
            rankValue = 2;
        } else if (rank.equals("three") || rank.equals("Three") || rank.equals("3")) {
            rankValue = 3;
        } else if (rank.equals("four") || rank.equals("Four") || rank.equals("4")) {
            rankValue = 4;
        } else if (rank.equals("five") || rank.equals("Five") || rank.equals("5")) {
            rankValue = 5;
        } else if (rank.equals("six") || rank.equals("Six") || rank.equals("6")) {
            rankValue = 6;
        } else if (rank.equals("seven") || rank.equals("Seven") || rank.equals("7")) {
            rankValue = 7;
        } else if (rank.equals("eight") || rank.equals("Eight") || rank.equals("8")) {
            rankValue = 8;
        } else if (rank.equals("nine") || rank.equals("Nine") || rank.equals("9")) {
            rankValue = 9;
        } else if (rank.equals("ten") || rank.equals("Ten") || rank.equals("10")) {
            rankValue = 10;
        } else if (rank.equals("jack") || rank.equals("Jack")) {
            rankValue = 11;
        } else if (rank.equals("queen") || rank.equals("Queen")) {
            rankValue = 12;
        } else if (rank.equals("king") || rank.equals("King")) {
            rankValue = 13;
        } else if (rank.equals("ace") || rank.equals("Ace")) {
            rankValue = 14;
        }
        return rankValue;
    }

    /**
     * Converts a String Card's suit to an int value for internal computations
     * Allows for both types of Cards to use the same methods
     * @param suit the suit to which the Card belongs to as a String
     * @return the integer value of the String suit to be used
     */
    private int getSuitNumber(String suit) {
        int suitValue = 0;
        if (suit.equals("Spades")) {
            return suitValue;
        } else if (suit.equals("Hearts")) {
            suitValue = 1;
        } else if (suit.equals("Clubs")) {
            suitValue = 2;
        } else if (suit.equals("Diamonds")) {
            suitValue = 3;
        }
        return suitValue;
    }

    /**
     * Returns the integer value of the Card object
     * @return the int value of the Card
     */
    public int getRank() {
        return this.rank;
    }

    /**
     * Returns the Suit Assosciated with the Card objecr
     * @return
     */
    public int getSuit() {
        return this.suit;
    }

    /**
     * Converts the int rank into its correct Face in a real deck of cards
     * Ranks that have the int 2-9 will stay the same, but convert to a String
     * Ranks that are 10-14 will convert to their correct face(ex. 11 - Jack)
     * @return A string that corresponds to the card's corresponding rank integer
     */
    private String getFaceString() {
        String[] FACES = {"Ten", "Jack", "Queen", "King", "Ace"};
        String cardFace;

        if (rank == 11) {
            cardFace = FACES[1];
        } else if (rank == 12) {
            cardFace = FACES[2];
        } else if (rank == 13) {
            cardFace = FACES[3];
        } else if (rank == 14) {
            cardFace = FACES[4];
        } else {
            cardFace = String.valueOf(rank);
        }
        return cardFace;
    }

    /**
     * Converts the initial String suit into its full name
     * @return String that is easily readable by user
     */
    private String getSuitString() {
        String[] SUITS = {"Spades","Hearts","Clubs","Diamonds"};
        String cardSuit;

        if (suit == 0){
            cardSuit = SUITS[0];
        } else if (suit == 1){
            cardSuit = SUITS[1];
        } else if (suit == 2){
            cardSuit = SUITS[2];
        } else {
            cardSuit = SUITS[3];
        }
        return cardSuit;
    }

    /**
     * Takes a Card Object and creates a readable version of the card
     * Takes it face value from previous method "getFaceString"
     * Takes its suit value from previous method "getSuitString"
     * Concatenates the face String and suit String and "of" to form string
     * @return a readable String that resembles the Card object
     */
    public String toString() {
        String face = getFaceString();
        String suit = getSuitString();
        return face + " of " + suit;
    }
}
