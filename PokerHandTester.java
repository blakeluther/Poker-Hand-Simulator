package proj4;

import java.util.ArrayList;
import java.util.Arrays;

public class PokerHandTester {

    public static void main(String[] args) {
        testAddCard();
        testGetCard();
        testCompareTo();
        Tester.finishTests();

    }

    public static void testAddCard() {
        PokerHand testHand = new PokerHand(new ArrayList<Card>());
        testHand.addCard(new Card("Ten", "Spades"));
        testHand.addCard(new Card(2, 1));

        Tester.assertEquals("Test - Does the addCard method add the Card to the ArrayList inside the PokerHand",
                "2 of Hearts",
                testHand.getIthCard(1).toString());
    }

    public static void testGetCard() {
        PokerHand testHand = new PokerHand(new ArrayList<Card>());
        testHand.addCard(new Card("Ten", "Spades"));
        testHand.addCard(new Card(2, 1));
        testHand.addCard(new Card("Queen", "Hearts"));
        testHand.addCard(new Card("King", "Clubs"));
        testHand.addCard(new Card("six", "Diamonds"));

        Tester.assertEquals("Test - getting the card at index 0",
                "10 of Spades",
                testHand.getIthCard(0).toString());

        Tester.assertEquals("Test - getting the card at index 2",
                "Queen of Hearts",
                testHand.getIthCard(2).toString());

        Tester.assertEquals("Test - getting the card at index 4",
                "6 of Diamonds",
                testHand.getIthCard(4).toString());
    }

    public static void testCompareTo() {
        // String Hands
        PokerHand flushHand = new PokerHand(new ArrayList<Card>(Arrays.asList(new Card("five", "Clubs"), new Card("Nine", "Clubs"),
                new Card("eight", "Clubs"), new Card("King", "Clubs"), new Card("jack", "Clubs"))));

        PokerHand twoPairHand = new PokerHand(new ArrayList<Card>(Arrays.asList(new Card("eight", "Spades"), new Card("six", "Spades"),
                new Card("king", "Hearts"), new Card("king", "Spades"), new Card("eight", "Hearts"))));

        PokerHand pairHand = new PokerHand(new ArrayList<Card>(Arrays.asList(new Card("Ace", "Spades"), new Card("six", "Spades"),
                new Card("nine", "Spades"), new Card("Ace", "Clubs"), new Card("Queen", "Hearts"))));

        PokerHand highCardHand = new PokerHand(new ArrayList<Card>(Arrays.asList(new Card("eight", "Diamonds"), new Card("six", "Diamonds"),
                new Card("nine", "Hearts"), new Card("king", "Diamonds"), new Card("queen", "Clubs"))));

        // flush v. flush - tie

        PokerHand spadesFlushHand = new PokerHand(new ArrayList<Card>(Arrays.asList(new Card("five", "Spades"), new Card("Nine", "Spades"),
                new Card("eight", "Spades"), new Card("King", "Spades"), new Card("jack", "Spades"))));

        Tester.assertEquals("Test - Flush v. Flush (king high) - tie",
                0,
                spadesFlushHand.compareTo(flushHand));

        //2 flushes - 4th card is high card
        PokerHand clubsFlushHand = new PokerHand(new ArrayList<Card>(Arrays.asList(new Card(5, 2), new Card(9, 2),
                new Card(7, 2), new Card(13, 2), new Card(11, 2))));

        Tester.assertEquals("Test: 2 flush hands - 4th card is highcard", -1,
                clubsFlushHand.compareTo(spadesFlushHand));

        //flush v. two pair

        Tester.assertEquals("Test: flush v. 2pair", 1,
                flushHand.compareTo(twoPairHand));

        // flush v. pair
        Tester.assertEquals("Test: flush v. pair", 1,
                flushHand.compareTo(pairHand));

        //flush v. highcard

        Tester.assertEquals("Test: flush v. highcard", 1,
                flushHand.compareTo(highCardHand));

        // two pair v. two pair - first pair is different

        PokerHand testTwoPairHand = new PokerHand(new ArrayList<Card>(Arrays.asList(new Card("nine", "Spades"), new Card("two", "Spades"),
                new Card("ace", "Hearts"), new Card("ace", "Spades"), new Card("nine", "Hearts"))));

        Tester.assertEquals("Test: flush v. highcard", -1,
                twoPairHand.compareTo(testTwoPairHand));

        // two pair v. two pair - high card is different

        PokerHand sameTwoPairHand1 = new PokerHand(new ArrayList<Card>(Arrays.asList(new Card("nine", "Clubs"), new Card("five", "Spades"),
                new Card("ace", "Diamonds"), new Card("ace", "Clubs"), new Card("nine", "Diamonds"))));

        PokerHand sameTwoPairHand2 = new PokerHand(new ArrayList<Card>(Arrays.asList(new Card("nine", "Spades"), new Card("two", "Spades"),
                new Card("ace", "Hearts"), new Card("ace", "Spades"), new Card("nine", "Hearts"))));

        Tester.assertEquals("Test: two two pairs (ace and 9) - highcard different",
                1,
                sameTwoPairHand1.compareTo(sameTwoPairHand2));

        // full house v. two pair

        PokerHand fullHouseHand = new PokerHand(new ArrayList<Card>(Arrays.asList(new Card("seven", "Clubs"), new Card("king", "Spades"),
                new Card("king", "Diamonds"), new Card("king", "Clubs"), new Card("seven", "Diamonds"))));

        Tester.assertEquals("Test: two pair (ace and 9) v.s. full house (king and seven)",
                1,
                sameTwoPairHand1.compareTo(fullHouseHand));

        // two pair v. pair
        Tester.assertEquals("Test: two pair(king, 8) v. pair(ace)",
                -1,
                pairHand.compareTo(twoPairHand));

        // two pair v. highcard
        Tester.assertEquals("Test: two pair (king, 8) v. highcard(king)",
                -1,
                highCardHand.compareTo(twoPairHand));

        // pair v. pair - pair value is different

        PokerHand pairHand1 = new PokerHand(new ArrayList<Card>(Arrays.asList(new Card("two", "Clubs"), new Card("five", "Spades"),
                new Card("king", "Diamonds"), new Card("king", "Clubs"), new Card("ten", "Diamonds"))));

        PokerHand pairHand2 = new PokerHand(new ArrayList<Card>(Arrays.asList(new Card("three", "Spades"), new Card("two", "Spades"),
                new Card("ace", "Hearts"), new Card("ace", "Spades"), new Card("eight", "Hearts"))));

        Tester.assertEquals("Test: pair v.s. pair - different pair value",
                -1,
                pairHand1.compareTo(pairHand2));

        // pair v. pair - 2nd highcard is different

        pairHand1 = new PokerHand(new ArrayList<Card>(Arrays.asList(new Card("two", "Clubs"), new Card("five", "Spades"),
                new Card("king", "Diamonds"), new Card("king", "Clubs"), new Card("ten", "Diamonds"))));

        pairHand2 = new PokerHand(new ArrayList<Card>(Arrays.asList(new Card("three", "Clubs"), new Card("six", "Spades"),
                new Card("king", "Hearts"), new Card("king", "Spades"), new Card("ten", "Clubs"))));

        Tester.assertEquals("Test: equal pairs, 2nd high card is different",
                -1,
                pairHand1.compareTo(pairHand2));

        // pair v. highcard
        Tester.assertEquals("Test: pair(kings) v. highcard(king)",
                1,
                pairHand.compareTo(highCardHand));
    }
}
