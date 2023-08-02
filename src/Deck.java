import java.time.LocalTime;
import java.util.ArrayList;


//add burnt card pile
public class Deck {
    ArrayList<Card> deck = new ArrayList<>();

    //can write all determine classes into one function by passing a card
    public Deck() {
        for (int i = 0; i < 52; i++)
            deck.add(new Card(determineRank(i), determineSuit(i), determineValue(i)));
    }

    public void printDeck() {
        for (Card card: deck) {
            card.printCard();
        }
    }
    public Card draw() {
        LocalTime cardTime = LocalTime.now();
        int randomIndex = (cardTime.getNano() + cardTime.getSecond()) % deck.size();
        Card tempCard = deck.get(randomIndex);
        deck.remove(randomIndex);
        return tempCard;
    }
    public void shuffleDeck() {

        for (int i = 0; i < 1000; i++) {
            LocalTime cardTime = LocalTime.now();
            int randomIndex = (cardTime.getNano() + cardTime.getSecond()) % 52;
            Card temp = deck.get(i % 52);

            deck.remove(i % 52);
            deck.add(randomIndex, temp);


        }
    }
    //HELPER FUNCTIONS
    private String determineRank(int i) {
        if (i % 13 == 1) {
            return "Ace";
        }
        else if (i % 13 == 2) {
            return "Two";
        }
        else if (i % 13 == 3) {
            return "Three";
        }
        else if (i % 13 == 4) {
            return "Four";
        }
        else if (i % 13 == 5) {
            return "Five";
        }
        else if (i % 13 == 6) {
            return "Six";
        }
        else if (i % 13 == 7) {
            return "Seven";
        }
        else if (i % 13 == 8) {
            return "Eight";
        }
        else if (i % 13 == 9) {
            return "Nine";
        }
        else if (i % 13 == 10) {
            return "Ten";
        }
        else if (i % 13 == 11) {
            return "Jack";
        }
        else if (i % 13 == 12) {
            return "Queen";
        }
        else {
            return "King";
        }
    }

    private int determineValue(int i) {
        if (i % 13 == 1) {
            return 1;
        }
        else if (i % 13 == 2) {
            return 2;
        }
        else if (i % 13 == 3) {
            return 3;
        }
        else if (i % 13 == 4) {
            return 4;
        }
        else if (i % 13 == 5) {
            return 5;
        }
        else if (i % 13 == 6) {
            return 6;
        }
        else if (i % 13 == 7) {
            return 7;
        }
        else if (i % 13 == 8) {
            return 8;
        }
        else if (i % 13 == 9) {
            return 9;
        }
        else if (i % 13 == 10) {
            return 10;
        }
        else if (i % 13 == 11) {
            return 10;
        }
        else if (i % 13 == 12) {
            return 10;
        }
        else {
            return 10;
        }
    }
    private String determineSuit(int i) {
        if (i <= 13) {
            return "Clubs";
        }
        else if (i <= 26) {
            return "Diamonds";
        }
        else if (i <= 39) {
            return "Hearts";
        }
        else {
            return "Spades";
        }
    }
}
