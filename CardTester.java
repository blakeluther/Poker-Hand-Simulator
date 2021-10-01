package proj4;

public class CardTester {

    public static void main(String[] args) {
        testToString();
        testGetRank();
        testGetSuit();
        Tester.finishTests();
    }

    public static void testToString(){
        Card testCard = new Card("eight","Spades");
        Tester.assertEquals("Test: Creating a readable card from String Card",
                "8 of Spades",
                testCard.toString());

        testCard = new Card(12,2);
        Tester.assertEquals("Test: Creating a readable card from Int Card",
                "Queen of Clubs",
                testCard.toString());
    }

    public static void testGetRank(){
        Card testCard = new Card("3","Hearts");
        Tester.assertEquals("Test: Getting only the rank int from a String Card",
                3,
                testCard.getRank());

        testCard = new Card(3,2);
        Tester.assertEquals("Test: Getting only the rank int from a Int Card",
                3,
                testCard.getRank());
    }

    public static void testGetSuit(){
        Card testCard = new Card("3","Spades");
        Tester.assertEquals("Test: Getting only the suit int from a String Card",
                0,
                testCard.getSuit());

        testCard = new Card(3,0);
        Tester.assertEquals("Test: Getting only the suit int from a int Card",
                0,
                testCard.getSuit());
    }
}
