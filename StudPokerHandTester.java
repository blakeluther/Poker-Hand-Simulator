package proj4;

import java.util.ArrayList;
import java.util.Arrays;

public class StudPokerHandTester {
    public static void main(String[] args) {
        testAddCard();
        testGetIthCard();
        testCompareTo();
        Tester.finishTests();

    }

    public static void testAddCard(){
        ArrayList<Card> communityCards = new ArrayList<>();
        ArrayList<Card> player = new ArrayList<>();

        CommunityCardSet community = new CommunityCardSet(communityCards);

        StudPokerHand studPlayer = new StudPokerHand(community, player);

        studPlayer.addCard(new Card("10","Hearts"));
        studPlayer.getIthCard(0);

        Tester.assertEquals("Test - Adding a card to the player hand in StudPokerHand",
                "10 of Hearts",
                studPlayer.getIthCard(0).toString());
    }

    public static void testGetIthCard(){
        ArrayList<Card> player = new ArrayList<>();
        CommunityCardSet community = new CommunityCardSet(new ArrayList<>());
        StudPokerHand studPlayer = new StudPokerHand(community, player);
        studPlayer.addCard(new Card("10","Hearts"));
        studPlayer.addCard(new Card("3","Clubs"));

        Tester.assertEquals("Test - Adding a card to the player hand in StudPokerHand",
                "10 of Hearts",
                studPlayer.getIthCard(0).toString());

        Tester.assertEquals("Test - Adding a card to the player hand in StudPokerHand",
                "3 of Clubs",
                studPlayer.getIthCard(1).toString());
    }

    public static void testCompareTo(){
        // stud test - both cards use community cards - tie
        ArrayList<Card> player1 = new ArrayList<>(Arrays.asList(new Card("two","Spades"), new Card("four", "Diamonds")));
        ArrayList<Card> player2 = new ArrayList<>(Arrays.asList(new Card("three","Hearts"), new Card("five", "Clubs")));

        CommunityCardSet community = new CommunityCardSet(new ArrayList<>(Arrays.asList(new Card("ten","Spades"), new Card("Jack","Diamonds"),
                new Card("Jack","Hearts"), new Card("ten","Diamonds"), new Card("6","Spades"))));

        StudPokerHand studPlayer1 = new StudPokerHand(community, player1);
        StudPokerHand studPlayer2 = new StudPokerHand(community, player2);

        Tester.assertEquals("Test: stud test - both cards use community cards - tie",
                0,
                studPlayer1.compareTo(studPlayer2));

        // stud test - both hands use different community Cards - using same player 1 and player 2 hands as before

        community = new CommunityCardSet(new ArrayList<>(Arrays.asList(new Card("ten","Spades"), new Card("two","Diamonds"),
                new Card("four","Hearts"), new Card("king","Diamonds"), new Card("five","Spades"))));

        studPlayer1 = new StudPokerHand(community, player1);
        studPlayer2 = new StudPokerHand(community, player2);

        Tester.assertEquals("Test: stud test - use different community cards - 2pair(2&4) vs. pair(5) - player 1 wins",
                1,
                studPlayer1.compareTo(studPlayer2));

        // stud test - two pair v. 4 of a kind

        player1 = new ArrayList<>(Arrays.asList(new Card("king","Spades"), new Card("king", "Diamonds")));
        player2 = new ArrayList<>(Arrays.asList(new Card("ten", "Hearts"), new Card("four", "Diamonds")));

        community = new CommunityCardSet(new ArrayList<>(Arrays.asList(new Card("ten","Spades"), new Card("two","Diamonds"),
                new Card("king","Diamonds"), new Card("king","Hearts"), new Card("four","Spades"))));

        studPlayer1 = new StudPokerHand(community, player1);
        studPlayer2 = new StudPokerHand(community, player2);

        Tester.assertEquals("Test: stud test: 4 of a kind (K) v. 2 pair(10,4) - player 1 wins",
                1,
                studPlayer1.compareTo(studPlayer2));

        // stud test - two flushes - different on 4th card

        player1 = new ArrayList<>(Arrays.asList(new Card("ten","Spades"), new Card("king", "Diamonds")));
        player2 = new ArrayList<>(Arrays.asList(new Card("ten", "Hearts"), new Card("six", "Spades")));

        community = new CommunityCardSet(new ArrayList<>(Arrays.asList(new Card("two","Spades"), new Card("three","Diamonds"),
                new Card("king","Spades"), new Card("jack","Spades"), new Card("ace","Spades"))));

        studPlayer1 = new StudPokerHand(community, player1);
        studPlayer2 = new StudPokerHand(community, player2);

        Tester.assertEquals("Test: stud test: two flushes - different on 4th card (10 v. 6) - player 1 wins",
                1,
                studPlayer1.compareTo(studPlayer2));

        // stud test - both one pairs  - tied

        player1 = new ArrayList<>(Arrays.asList(new Card("ten","Spades"), new Card("six", "Diamonds")));
        player2 = new ArrayList<>(Arrays.asList(new Card("ten", "Hearts"), new Card("six", "Spades")));

        community = new CommunityCardSet(new ArrayList<>(Arrays.asList(new Card("two","Spades"), new Card("ten","Diamonds"),
                new Card("king","Spades"), new Card("jack","Hearts"), new Card("ace","Spades"))));

        studPlayer1 = new StudPokerHand(community, player1);
        studPlayer2 = new StudPokerHand(community, player2);

        Tester.assertEquals("Test: stud test: two pairs - tied",
                0,
                studPlayer1.compareTo(studPlayer2));

        // stud test - highcard hands - different on 5th card

        player1 = new ArrayList<>(Arrays.asList(new Card("nine","Spades"), new Card("six", "Diamonds")));
        player2 = new ArrayList<>(Arrays.asList(new Card("seven", "Hearts"), new Card("six", "Spades")));

        community = new CommunityCardSet(new ArrayList<>(Arrays.asList(new Card("two","Spades"), new Card("ten","Diamonds"),
                new Card("Queen","Spades"), new Card("jack","Hearts"), new Card("ace","Diamonds"))));

        studPlayer1 = new StudPokerHand(community, player1);
        studPlayer2 = new StudPokerHand(community, player2);

        Tester.assertEquals("Test: stud test: highcard hands - different on 5th card - player 1 wins",
                1,
                studPlayer1.compareTo(studPlayer2));

        // stud test - highcard hands - tied

        player1 = new ArrayList<>(Arrays.asList(new Card("nine","Spades"), new Card("six", "Diamonds")));
        player2 = new ArrayList<>(Arrays.asList(new Card("seven", "Hearts"), new Card("nine", "Clubs")));

        studPlayer1 = new StudPokerHand(community, player1);
        studPlayer2 = new StudPokerHand(community, player2);

        Tester.assertEquals("Test: stud test: highcard hands - tied",
                0,
                studPlayer1.compareTo(studPlayer2));

        // stud test - hole cards make two different two pairs

        player1 = new ArrayList<>(Arrays.asList(new Card("nine","Spades"), new Card("jack", "Diamonds")));
        player2 = new ArrayList<>(Arrays.asList(new Card("ace", "Hearts"), new Card("six", "Spades")));

        community = new CommunityCardSet(new ArrayList<>(Arrays.asList(new Card("two","Spades"), new Card("nine","Diamonds"),
                new Card("jack","Spades"), new Card("six","Hearts"), new Card("ace","Diamonds"))));

        studPlayer1 = new StudPokerHand(community, player1);
        studPlayer2 = new StudPokerHand(community, player2);

        Tester.assertEquals("Test: hole cards make two different two pairs - (9 & Jack) v. (Ace & 6) - player 2 wins",
                -1,
                studPlayer1.compareTo(studPlayer2));

        // stud test - two pair v. pair

        player1 = new ArrayList<>(Arrays.asList(new Card("nine","Spades"), new Card("jack", "Diamonds")));
        player2 = new ArrayList<>(Arrays.asList(new Card("ace", "Hearts"), new Card("six", "Spades")));

        community = new CommunityCardSet(new ArrayList<>(Arrays.asList(new Card("two","Spades"), new Card("nine","Diamonds"),
                new Card("jack","Spades"), new Card("six","Hearts"), new Card("queen","Diamonds"))));

        studPlayer1 = new StudPokerHand(community, player1);
        studPlayer2 = new StudPokerHand(community, player2);

        Tester.assertEquals("Test: two pair (nine & jack) v.s. pair (6) - two pair wins",
                1,
                studPlayer1.compareTo(studPlayer2));

        // stud test - two pair v. full house

        player1 = new ArrayList<>(Arrays.asList(new Card("nine","Spades"), new Card("nine", "Diamonds")));
        player2 = new ArrayList<>(Arrays.asList(new Card("ace", "Hearts"), new Card("six", "Spades")));

        community = new CommunityCardSet(new ArrayList<>(Arrays.asList(new Card("two","Spades"), new Card("nine","Hearts"),
                new Card("jack","Spades"), new Card("jack","Hearts"), new Card("six","Diamonds"))));

        studPlayer1 = new StudPokerHand(community, player1);
        studPlayer2 = new StudPokerHand(community, player2);

        Tester.assertEquals("Test: two pair (Jack & six) v.s. full house (9 and jack) - full house wins",
                1,
                studPlayer1.compareTo(studPlayer2));

        // stud test - two pair v. full house

        player1 = new ArrayList<>(Arrays.asList(new Card("king","Clubs"), new Card("king", "Diamonds")));
        player2 = new ArrayList<>(Arrays.asList(new Card("ace", "Hearts"), new Card("king", "Spades")));

        community = new CommunityCardSet(new ArrayList<>(Arrays.asList(new Card("eight","Spades"), new Card("ace","Hearts"),
                new Card("jack","Spades"), new Card("king","Hearts"), new Card("eight","Diamonds"))));

        studPlayer1 = new StudPokerHand(community, player1);
        studPlayer2 = new StudPokerHand(community, player2);

        Tester.assertEquals("Test: two pair (ace & king) v.s. full house (king & eight) - two pair wins",
                -1,
                studPlayer1.compareTo(studPlayer2));
    }
}
