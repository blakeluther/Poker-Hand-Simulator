package proj4;

import java.util.ArrayList;

public class CommunityCardSetTester {

    public static void main(String[] args) {
        testAddCardAndSizeOfDeck();
        testGetIthCard();
        Tester.finishTests();
    }

    public static void testAddCardAndSizeOfDeck(){
        CommunityCardSet community = new CommunityCardSet(new ArrayList<Card>());
        community.addCard(new Card(3,2));
        community.addCard(new Card("ten","Spades"));
        Tester.assertEquals("Test - making sure the arraylist that holds the commumnity cards can recieve cards",
                2,
                community.getCommunityCardSize());
    }

    public static void testGetIthCard(){
        CommunityCardSet community = new CommunityCardSet(new ArrayList<Card>());
        community.addCard(new Card(3,2));
        community.addCard(new Card("ten","Spades"));
        Tester.assertEquals("Test - getting a card a specific index (0)",
                "3 of Clubs",
                community.getIthCard(0).toString());
    }
}
