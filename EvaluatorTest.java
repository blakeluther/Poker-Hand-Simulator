package proj4;

import java.util.ArrayList;
import java.util.Arrays;

public class EvaluatorTest {
    public static void main(String[] args) {
        testRankHand();
        testCompareFlushOrHighCard();
        testComparePairs();
        Tester.finishTests();

    }

    public static void testRankHand(){
        Evaluator eval = new Evaluator();

        // rank flush - int cards
        PokerHand test = new PokerHand(new ArrayList<>(Arrays.asList(new Card(8,2),new Card(10,2),
                new Card(10,2),new Card(8,2),new Card(11,2))));

        Tester.assertEquals("Test int cards - Check to see if hand is correctly recognized (flush)",
                4,
                eval.rankHand(test));

        // rank flush - String cards
        test = new PokerHand(new ArrayList<Card>(Arrays.asList(new Card("jack","Spades"),new Card("10","Spades"),
                new Card("2","Spades"),new Card("3","Spades"),new Card("jack","Spades"))));

        Tester.assertEquals("Test String Cards - Check to see if hand is correctly recognized (flush)",
                4,
                eval.rankHand(test));

        // rank 2 pair - int cards
        test = new PokerHand(new ArrayList<Card>(Arrays.asList(new Card(8,2),new Card(10,0),
                new Card(10,3),new Card(8,1),new Card(11,1))));

        Tester.assertEquals("Test int cards - Check to see if hand is correctly recognized (2pair)",
                3,
                eval.rankHand(test));

        // rank 2 pair - String cards
        test = new PokerHand(new ArrayList<Card>(Arrays.asList(new Card("jack","Spades"),new Card("10","Hearts"),
                new Card("king","Hearts"),new Card("King","Spades"),new Card("jack","Clubs"))));

        Tester.assertEquals("Test String Cards - Check to see if hand is correctly recognized (2pair)",
                3,
                eval.rankHand(test));

        // rank pair - int cards
        test = new PokerHand(new ArrayList<Card>(Arrays.asList(new Card(11,2),new Card(11,0),
                new Card(10,3),new Card(8,1),new Card(11,1))));

        Tester.assertEquals("Test int cards - Check to see if hand is correctly recognized (Pair)",
                2,
                eval.rankHand(test));

        // rank pair - String cards
        test = new PokerHand(new ArrayList<Card>(Arrays.asList(new Card("11","Spades"),new Card("10","Hearts"),
                new Card("11","Hearts"),new Card("13","Spades"),new Card("8","Clubs"))));

        Tester.assertEquals("Test String Cards - Check to see if hand is correctly recognized (Pair)",
                2,
                eval.rankHand(test));

        // rank highcard - int cards
        test = new PokerHand(new ArrayList<Card>(Arrays.asList(new Card(4,2),new Card(10,3),
                new Card(11,3),new Card(13,1),new Card(8,1))));

        Tester.assertEquals("Test int cards - Check to see if hand is correctly recognized (Highcard)",
                1,
                eval.rankHand(test));

        // rank highcard - String cards
        test = new PokerHand(new ArrayList<Card>(Arrays.asList(new Card("4","Spades"),new Card("10","Hearts"),
                new Card("jack","Hearts"),new Card("king","Spades"),new Card("8","Clubs"))));

        Tester.assertEquals("Test String Cards - Check to see if hand is correctly recognized (Highcard)",
                1,
                eval.rankHand(test));
    }

    public static void testCompareFlushOrHighCard(){
        // two int PokerHands
        PokerHand player1 = new PokerHand(new ArrayList<>(Arrays.asList(new Card(8,2),new Card(10,2),
                new Card(10,2),new Card(8,2),new Card(11,2))));

        PokerHand player2 = new PokerHand(new ArrayList<>(Arrays.asList(new Card(8,3),new Card(10,3),
                new Card(10,3),new Card(8,3),new Card(13,3))));

        Tester.assertEquals("Test - Two Int PokerHands - comparing two flushes - is HighCard too - player 2 wins",
                -1,
                player1.compareTo(player2));

        // testing two String HighCard hands

        player1 = new PokerHand(new ArrayList<Card>(Arrays.asList(new Card("4","Spades"),new Card("10","Hearts"),
                new Card("2","Hearts"),new Card("13","Clubs"),new Card("Ace","Clubs"))));

        player2 = new PokerHand(new ArrayList<Card>(Arrays.asList(new Card("4","Hearts"),new Card("queen","Hearts"),
                new Card("jack","Hearts"),new Card("king","Spades"),new Card("8","Clubs"))));

        Tester.assertEquals("Test - Two String PokerHands - comparing two String hands - highcards - player 1 wins",
                1,
                player1.compareTo(player2));

        // testing one int PokerHand and one String PokerHand - HighCards

        player1 = new PokerHand(new ArrayList<Card>(Arrays.asList(new Card("4","Hearts"),new Card("queen","Hearts"),
                new Card("jack","Hearts"),new Card("king","Spades"),new Card("8","Clubs"))));

        player2 = new PokerHand(new ArrayList<>(Arrays.asList(new Card(14,1),new Card(10,3),
                new Card(2,3),new Card(8,0),new Card(9,2))));

        Tester.assertEquals("Test - One int PokerHand, one String Pokerhand - comparing highcards - player 2 wins",
                -1,
                player1.compareTo(player2));
    }

    public static void testComparePairs(){
        // two int PokerHands - two two pairs
        PokerHand player1 = new PokerHand(new ArrayList<Card>(Arrays.asList(new Card(8,2),new Card(10,0),
                new Card(10,3),new Card(8,1),new Card(11,1))));

        PokerHand player2 = new PokerHand(new ArrayList<Card>(Arrays.asList(new Card(8,2),new Card(10,1),
                new Card(11,3),new Card(8,1),new Card(11,2))));

        Tester.assertEquals("Test - two int PokerHands - comparing two two pairs - player 2 wins",
                -1,
                player1.compareTo(player2));

        // two String PokerHands - two one pairs
        player1 = new PokerHand(new ArrayList<Card>(Arrays.asList(new Card("king","Diamonds"),new Card("10","Hearts"),
                new Card("king","Hearts"),new Card("king","Spades"),new Card("8","Clubs"))));

        player2 = new PokerHand(new ArrayList<Card>(Arrays.asList(new Card("11","Spades"),new Card("10","Diamonds"),
                new Card("11","Hearts"),new Card("2","Spades"),new Card("8","Hearts"))));

        Tester.assertEquals("Test - two String PokerHands - comparing two one pairs - player 1 wins",
                1,
                player1.compareTo(player2));

        // one String PokerHand, one int Pokerhand - two one pairs - player 1 ones

        player1 = new PokerHand(new ArrayList<Card>(Arrays.asList(new Card("king","Diamonds"),new Card("10","Hearts"),
                new Card("king","Hearts"),new Card("king","Spades"),new Card("8","Clubs"))));

        player2 = new PokerHand(new ArrayList<Card>(Arrays.asList(new Card(11,2),new Card(11,0),
                new Card(10,3),new Card(8,1),new Card(11,1))));

        Tester.assertEquals("Test - one String Pokerhand, one int Pokerhand - comparing two one pairs - player 1 wins",
                1,
                player1.compareTo(player2));
    }
}
