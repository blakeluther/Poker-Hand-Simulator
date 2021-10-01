package proj4;

public class DeckTester {

    public static void main(String[] args) {
        testDealCard();
        testGetDeckSize();
        testGatherDeck();
        testIsEmpty();
        Tester.finishTests();
    }

    public static void testDealCard(){
        Deck testDeck = new Deck();
        Tester.assertEquals("Test dealing the first card in deck",
                "2 of Spades",
                String.valueOf(testDeck.deal()));
    }

    public static void testGetDeckSize(){
        Deck testDeck = new Deck();
        for (int j =0; j<15;j++){
            testDeck.deal();
        }
        Tester.assertEquals("Test - checking size of deck after 15 cards dealt",
                37,
                testDeck.size());
    }

    public static void testGatherDeck(){
        Deck testDeck = new Deck();
        testDeck.deal();
        testDeck.deal();
        testDeck.gather();
        Tester.assertEquals("Test - checking to see if gatherDeck works",
                52,
                testDeck.size());
    }

    public static void testIsEmpty(){
        Deck testDeck = new Deck();
        Tester.assertEquals("Test - Check to see if isEmpty can recognize full deck",
                false,
                testDeck.isEmpty());

        for (int i  = 0; i < 52; i++){
            testDeck.deal();
        }

        Tester.assertEquals("Test - checking to see if isEmpty recognizes empty deck",
                true,
                testDeck.isEmpty());
    }
}
