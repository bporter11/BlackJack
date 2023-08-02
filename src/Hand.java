import java.util.ArrayList;

public class Hand {
    ArrayList<Card> hand = new ArrayList<>();

    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public Card removeCardFromHand() {
        Card tempCard = hand.get(0);
        hand.remove(0);
        return tempCard;
    }

    public int getHandValue(){
        int total = 0;
        for (Card card: hand)
            total+= card.value;
        return total;
    }

    public void printHand() {
        for (Card card: hand) {
            card.printCard();
            System.out.print(" ");
        }
    }
}
