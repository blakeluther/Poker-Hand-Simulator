package proj4;

import java.util.ArrayList;
import java.util.Arrays;

public class FormatTester {

    public static void main(String[] args) {
        testMakeValueList();
        testGetSingleNumbers();
        testGetPairValues();
        Tester.finishTests();

    }

    public static void testMakeValueList(){
        // int Cards
        PokerHand test = new PokerHand(new ArrayList<Card>(Arrays.asList(new Card(4,2),new Card(10,3),
                new Card(11,3),new Card(13,1),new Card(8,1))));

        String expected = "[13, 11, 10, 8, 4]";
        Tester.assertEquals("Testing: what list of values that gets returned from the makeValueList method - Int Card",
                expected,
                String.valueOf(Format.makeValueList(test)));

        // String Cards
        test = new PokerHand(new ArrayList<Card>(Arrays.asList(new Card("4","Spades"),new Card("10","Hearts"),
                new Card("jack","Hearts"),new Card("king","Spades"),new Card("8","Clubs"))));

        Tester.assertEquals("Testing: what list of values that gets returned from the makeValueList method - String Card",
                expected,
                String.valueOf(Format.makeValueList(test)));
    }

    public static void testGetSingleNumbers(){
        // int cards
        PokerHand test = new PokerHand(new ArrayList<Card>(Arrays.asList(new Card(11,2),new Card(10,3),
                new Card(11,3),new Card(13,1),new Card(11,1))));

        String expected = "[13, 10]";

        Tester.assertEquals("Test: (3ofAKind) what list of values come back if we want only the number that occur once in PokerHand",
                expected,
                String.valueOf(Format.getSingleNumbers(test)));

        // String Cards
        test = new PokerHand(new ArrayList<Card>(Arrays.asList(new Card("4","Spades"),new Card("10","Hearts"),
                new Card("jack","Hearts"),new Card("king","Spades"),new Card("8","Clubs"))));

        expected= "[13, 11, 10, 8, 4]";

        Tester.assertEquals("Testing: What values only appear once in a pokerhand - String Cards",
                expected,
                String.valueOf(Format.getSingleNumbers(test)));

    }

    public static void testGetPairValues(){
        // int cards
        PokerHand test = new PokerHand(new ArrayList<Card>(Arrays.asList(new Card(8,2),new Card(10,0),
                new Card(10,3),new Card(8,1),new Card(11,1))));

        String expected = "[10, 8]";

        Tester.assertEquals("Test: (2pair) checking to see what values come back if we only want number that occur" +
                        "more than once in PokerHand",
                expected,
                String.valueOf(Format.getPairValues(test)));

        // int cards
        test = new PokerHand(new ArrayList<Card>(Arrays.asList(new Card(11,2),new Card(11,0),
                new Card(10,3),new Card(8,1),new Card(11,1))));

        expected = "[11]";

        Tester.assertEquals("Test: (4pair) checking to see what values come back if we only want number that occur" +
                        "more than once in PokerHand",
                expected,
                String.valueOf(Format.getPairValues(test)));

        // string cards
        test = new PokerHand(new ArrayList<Card>(Arrays.asList(new Card("jack","Spades"),new Card("10","Hearts"),
                new Card("jack","Hearts"),new Card("king","Spades"),new Card("8","Clubs"))));

        expected= "[11]";

        Tester.assertEquals("Testing: what values in the hand are pairs",
                expected,
                String.valueOf(Format.getPairValues(test)));

        // string cards
        test = new PokerHand(new ArrayList<Card>(Arrays.asList(new Card("jack","Spades"),new Card("10","Hearts"),
                new Card("king","Hearts"),new Card("King","Spades"),new Card("jack","Clubs"))));

        expected= "[13, 11]";

        Tester.assertEquals("Testing: what values in the hand are pairs",
                expected,
                String.valueOf(Format.getPairValues(test)));
    }
}
