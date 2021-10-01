//I affirm that I have carried out the attached academic endeavors with full academic honesty.
package proj4;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Scanner userInput;

    private CommunityCardSet community;
    private StudPokerHand player1;
    private StudPokerHand player2;

    private int expectedWinner;
    private int userChoice;
    private int gameNumber = 1;
    private int score;

    /**
     * Gives the user the ability to input numbers for the game
     */
    public Main()
    {
        userInput = new Scanner(System.in);
    }

    /**
     * Plays the game
     * @param args
     */
    public static void main(String[] args)
    {
        new Main().aGame();
    }

    /**
     * The steps to play a round of the game
     * Will allow the player to play another game
     */
    private void aGame()
    {
        score = 0;
        System.out.println("Game " + gameNumber);
        System.out.println("----------------------------------------------");
        Deck deck = new Deck();

        while (!(deck.isEmpty()))
        {
            makeHands(deck);
            score = checkAnswer();
        }
        score = 0;
        playAgain();
    }

    /**
     * Takes a integer type input from the user
     * @return int of what the user typed in
     */
    private int getUserInput()
    {
        return userInput.nextInt();
    }

    /**
     * Creates new StudPokerHand's and a CommunityCardSet
     * The StudPokerHand's get compared and the computer gets the winner
     * (either player 1, player 2 or a Tie)
     * Sets the variable expectedWinner to the program-decided winner
     * @param deck the deck of Cards that the Hands draw from
     */
    private void makeHands(Deck deck)
    {
        int MAX_SIZE_COMMUNITY_CARDS = 5;
        int MAX_SIZE_HOLE = 2;
        int PLAYER2_WIN = -1;

        deck.shuffle();

        community = new CommunityCardSet(new ArrayList<Card>());
        player1 = new StudPokerHand(community, new ArrayList<Card>());
        player2 = new StudPokerHand(community, new ArrayList<Card>());

        for (int i = 0; i < MAX_SIZE_COMMUNITY_CARDS; i++) {
            community.addCard(deck.deal());
        }

        for (int i = 0; i < MAX_SIZE_HOLE; i++) {
            player1.addCard(deck.deal());
            player2.addCard(deck.deal());
        }

        System.out.println("The community cards are:\n" + community.toString());
        System.out.println("\nWhich of the following hands is worth more?");
        System.out.println("\nHand 1 Cards:\n" + player1.toString());
        System.out.println("\nHand 2 Cards:\n" + player2.toString());

        expectedWinner = player1.compareTo(player2);

        if (expectedWinner == PLAYER2_WIN){
            expectedWinner = 2;
        }
    }

    /**
     * Checks for different conditions and decides if the game is over (through incorrect answer / out of cards)
     * or if the player gets the correct answer, the game continues.
     */
    private int checkAnswer()
    {
        System.out.println("\nEnter 1 or 2 (or 0 if they are of equal value)");

        userChoice = getUserInput();
        System.out.println("Input recieved: " + userChoice);
        if (userChoice == expectedWinner)
        {
            score++;
            if (score == 5){
                System.out.println("\n----------------------------------------------");
                System.out.println("NOT ENOUGH CARDS TO CONTINUE GAME");
                System.out.println("Final Score: " + score);
                playAgain();
            } else {
                System.out.println("----------------------------------------------");
                System.out.println("\nYou got it right!");
                System.out.println("New Score: " + score + "\n");
                System.out.println("----------------------------------------------");
                return score;
            }
        } else
        {
            System.out.println("----------------------------------------------");
            System.out.println("\nWrong! Game over!");
            System.out.println("Final Score: " + score);
            System.out.println("\n----------------------------------------------");
            playAgain();
        }
        return score;
    }

    /**
     * Asks the player if they want to play another round
     */
    private void playAgain()
    {
        System.out.println("\nEnter 1 if you would like to play again! (or any number to quit)");
        userChoice = getUserInput();
        if (userChoice == 1){
            gameNumber++;
            System.out.println("----------------------------------------------");
            aGame();
        } else {
            System.out.println("Thanks for Playing!");
            System.exit(0);
        }
    }
}