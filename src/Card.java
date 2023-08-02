

public class Card {
    String suit;
    String rank;
    int value;

    public void printCard() {
        System.out.print(rank + " of " + suit);
    }
    public Card(String rank, String suit, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }


    public Card() {}

}
