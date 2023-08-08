import java.util.Scanner;

public class Blackjack extends Game{

    boolean playerBlackJack ;
    boolean dealerBlackJack ;
    boolean bothBlackJack;
    boolean potentialSplit;
    boolean doubleDown;
    boolean playableHand;
    Hand dealersHand = new Hand();
    Hand playersHand = new Hand();
    Deck blackJackDeck = new Deck();

    //deals the cards to the player and the dealer
    @Override
    public void startGame() {
        playerBlackJack = false;
        dealerBlackJack = false;
        potentialSplit = false;
        doubleDown = false;
        bothBlackJack = false;
        dealersHand = new Hand();
        playersHand = new Hand();
        blackJackDeck = new Deck();
        blackJackDeck.shuffleDeck();
        for (int i = 0; i < 2; i++) {
            dealersHand.addCardToHand(blackJackDeck.draw());
            playersHand.addCardToHand(blackJackDeck.draw());
        }
    }

    //check if dealer has a blackjack
    //if not then print only one card for the dealers hand
    //if blackjack then refer to another function for handling this
    //possibly called handleBlackjack
    //prints the dealers 1 card showing
    public void printDealerHand1() {
        dealersHand.hand.get(0).printCard();

    }

    //prints the whole hand with the hole card included
    public void printDealerHand2() {
        dealersHand.printHand();
    }

    public int getDealerValue1() {
        return dealersHand.hand.get(0).value;
    }

    //handles hands where there is an ace present
    //ace can be either a 1 or 11
    //if hand < 10 then ace == 1 or 11
    //if hand > 10 then ace == 1
    //if hand == 10 then ace == 11
    public void handleAce() {
        if (playersHand.hand.get(0).rank.equals("Ace") || playersHand.hand.get(1).rank.equals("Ace")) {
            if (playersHand.getHandValue() - 1 < 10) {

            }
        }
    }

    //this function will check for a blackjack in either to dealers hand
    //or players hand and will set the player/dealer blackjacks booleans to true
    public void checkBlackJack() {
        if (dealersHand.hand.get(0).value == 1 && dealersHand.hand.get(1).value == 10) {
            dealerBlackJack = true;
        }
        if (playersHand.hand.get(0).value == 1 && playersHand.hand.get(1).value == 10) {
            playerBlackJack = true;
        }
        if (playerBlackJack && dealerBlackJack) {
            bothBlackJack = true;
        }
    }

    //this function will handle the game outcome if a blackjack is present
    public void handleBlackJack() {
        if (dealerBlackJack && !playerBlackJack) {
            System.out.print("Dealer has blackjack, play again.");
        }
        else if (!dealerBlackJack && playerBlackJack) {
            System.out.print("You have a blackjack, very nice!");
        }
        else if (dealerBlackJack && playerBlackJack) {
            System.out.print("Dealer has blackjack and so do you, PUSH");
        }
    }

    //prints the cards from the players hand along with its corresponding value
    public void printPlayersHand(Hand hand) {
        hand.printHand();
    }

    //adds a card to the playersHand
    public void hitPlayersHand(Hand hand) {
        hand.addCardToHand(blackJackDeck.draw());
    }

    //adds a card to the dealersHand
    public void hitDealersHand() {
        dealersHand.addCardToHand(blackJackDeck.draw());
    }

    public void checkSplit() {
        if (playersHand.hand.get(0).value == playersHand.hand.get(1).value) {
            potentialSplit = true;
        }
    }

    public void handleSplit() {
        Hand playersHand1 = new Hand();
        Hand playersHand2 = new Hand();
        playersHand1.addCardToHand(playersHand.removeCardFromHand());
        playersHand2.addCardToHand(playersHand.removeCardFromHand());
    }


    public void handleDoubleDown() {

    }

    public void printWelcomeMessage() {
        System.out.println("Welcome to broppos Blackjack, Good Luck!");
    }




    //DEALERS RULES
    //-hit on everything except greater than  17
    //-hit soft 17
    //-any 10 and an ace =='s blackjack
    //--dealer wins on blackjack unless player has a blackjack too
    //-try to implement hole card
    //PLAYERS RULES
    //-any 10 and an ace =='s blackjack
    //--if player has a blackjack and the dealer has an ace up then player can opt for even money
    @Override
    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        int gameState = 0; //0 = initial deal
        // 2 = initial hit phase, 4 = double down, 5 = split, 6 = busted, 7 = dealer hit phase, 8 = quit, 9 = play again
        boolean playingGame = true;
        while (playingGame) {
            switch (gameState) {
                case 0 -> //start of game, checks for any blackjacks before proceeding
                {
                    startGame();
                    printWelcomeMessage();
                    System.out.println("Dealing Cards...");
                    System.out.println("----------------------------------------");

                    System.out.print("You have: ");
                    playersHand.printHand();
                    System.out.println();
                    System.out.println(playersHand.getHandValue());

                    System.out.print("Dealer has: ");
                    printDealerHand1();
                    System.out.println();
                    System.out.println(getDealerValue1());
                    checkBlackJack();
                    if (bothBlackJack) {
                        System.out.println("You and Dealer both have Blackjack, PUSH.");
                        System.out.println("Press 1 to quit, 2 to play again.");
                        String choice = scanner.nextLine(); // Read a line of text from the console
                        if (choice.equals("1"))
                            gameState = 9;
                        else
                            gameState = 0;
                    } else if (playerBlackJack) {
                        System.out.println("You have Blackjack, WIN.");
                        System.out.println("Press 1 to quit, 2 to play again.");
                        String choice = scanner.nextLine(); // Read a line of text from the console
                        if (choice.equals("1"))
                            gameState = 9;
                        else
                            gameState = 0;
                    } else if (dealerBlackJack) {
                        System.out.println("Dealer has Blackjack, LOSE.");
                        System.out.println("Press 1 to quit, 2 to play again.");
                        String choice = scanner.nextLine(); // Read a line of text from the console
                        if (choice.equals("1"))
                            gameState = 9;
                        else
                            gameState = 0;
                    } else
                        gameState = 1;
                }
                case 1 -> { //initial hit phase
                    if (potentialSplit) {
                        System.out.println("Would you like to Hit, Double down, Split, or Stand");
                        System.out.println("Press 1 to hit, 2 to Double down, 3 to Split, 4 to Stand");
                        String choice = scanner.nextLine();
                        if (choice.equals("1"))
                            gameState = 2; //hit
                        else if (choice.equals("2"))
                            gameState = 3; //double down
                        else if (choice.equals("3"))
                            gameState = 4; //split
                        else if (choice.equals("4"))
                            gameState = 5; //stand
                    } else {
                        System.out.println("Would you like to Hit, Double down, or Stand");
                        System.out.println("Press 1 to hit, 2 to Double down, 3 to Stand");
                        String choice = scanner.nextLine();
                        if (choice.equals("1"))
                            gameState = 2; //hit
                        else if (choice.equals("2"))
                            gameState = 3; //double down
                        else if (choice.equals("3"))
                            gameState = 5; //stand
                    }
                }
                case 2 -> { //secondary hit phase
                    playersHand.addCardToHand(blackJackDeck.draw());
                    System.out.print("You have: ");
                    playersHand.printHand();
                    System.out.println();
                    System.out.println(playersHand.getHandValue());
                    if (playersHand.getHandValue() <= 21) {
                        System.out.println("Would you like to Hit or Stand");
                        System.out.println("Press 1 to hit, 2 to Stand");
                        String choice = scanner.nextLine();
                        if (choice.equals("1"))
                            gameState = 2; //hit
                        else if (choice.equals("2")) {
                            gameState = 5; //stand
                        }

                    } else {
                        System.out.println("You have Busted!");
                        System.out.println("Dealer Had: ");
                        printDealerHand2();
                        System.out.println(dealersHand.getHandValue());
                        System.out.println("Press 1 to quit, 2 to play again.");
                        String choice = scanner.nextLine();
                        if (choice.equals("1"))
                            gameState = 9; //quit
                        else
                            gameState = 0; //play again
                    }
                }
                case 3 -> { //double down
                    playersHand.addCardToHand(blackJackDeck.draw());
                    if (playersHand.getHandValue() > 21) {
                        System.out.print("You have: ");
                        playersHand.printHand();
                        System.out.println();
                        System.out.println(playersHand.getHandValue());
                        System.out.println("You have Busted!");
                        System.out.println("Dealer Had: ");
                        printDealerHand2();
                        System.out.println(dealersHand.getHandValue());
                        System.out.println("Press 1 to quit, 2 to play again.");
                        String choice = scanner.nextLine();
                        if (choice.equals("1"))
                            gameState = 9; //quit
                        else
                            gameState = 0; //play again
                    } else {
                        gameState = 5;
                    }
                } //split functionality
                case 4, 5 -> { //stand / dealer draw
                    if (dealersHand.getHandValue() < 17) {
                        dealersHand.addCardToHand(blackJackDeck.draw());
                        System.out.print("You have: ");
                        playersHand.printHand();
                        System.out.println();
                        System.out.println(playersHand.getHandValue());

                        System.out.print("Dealer has: ");
                        printDealerHand2();
                        System.out.println();
                        System.out.println(dealersHand.getHandValue());
                        if (dealersHand.getHandValue() > 21) { //dealer has busted
                            System.out.println("Dealer has busted, you Win!");
                            System.out.println("Press 1 to quit, 2 to play again.");
                            String choice = scanner.nextLine();
                            if (choice.equals("1"))
                                gameState = 9; //quit
                            else
                                gameState = 0; //play again
                        }

                    } else {
                        if (dealersHand.getHandValue() < playersHand.getHandValue()) {
                            System.out.print("You have: ");
                            playersHand.printHand();
                            System.out.println();
                            System.out.println(playersHand.getHandValue());

                            System.out.print("Dealer has: ");
                            printDealerHand2();
                            System.out.println();
                            System.out.println(dealersHand.getHandValue());

                            System.out.println("You Win!");
                            System.out.println("Press 1 to quit, 2 to play again.");
                            String choice = scanner.nextLine();
                            if (choice.equals("1"))
                                gameState = 9; //quit
                            else
                                gameState = 0; //play again
                        } else if (dealersHand.getHandValue() > playersHand.getHandValue()) {
                            System.out.print("You have: ");
                            playersHand.printHand();
                            System.out.println();
                            System.out.println(playersHand.getHandValue());

                            System.out.print("Dealer has: ");
                            printDealerHand2();
                            System.out.println();
                            System.out.println(dealersHand.getHandValue());

                            System.out.println("Dealer wins...");
                            System.out.println("Press 1 to quit, 2 to play again.");
                            String choice = scanner.nextLine();
                            if (choice.equals("1"))
                                gameState = 9; //quit
                            else
                                gameState = 0; //play again
                        } else {
                            System.out.print("You have: ");
                            playersHand.printHand();
                            System.out.println();
                            System.out.println(playersHand.getHandValue());

                            System.out.print("Dealer has: ");
                            printDealerHand2();
                            System.out.println();
                            System.out.println(dealersHand.getHandValue());

                            System.out.println("PUSH.");
                            System.out.println("Press 1 to quit, 2 to play again.");
                            String choice = scanner.nextLine();
                            if (choice.equals("1"))
                                gameState = 9; //quit
                            else
                                gameState = 0; //play again
                        }

                    }
                }
                case 9 -> {
                    playingGame = false;
                }
            }

        }
    }





    //@Override
    //public void endGame() {
//
    //}
}
