import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;


public class BlackJack {
    private class Card {
        String value; 
        String type;

        Card(String value, String type) {
            this.value = value;
            this.type = type;
        }

        public String toString() {
            return value + "-" + type;
        }

        public int getValue() {
            if ("AJQK".contains(value)) {
                if (value.equals("A")) {
                    return 11;
                }
                return 10;
            }
            return Integer.parseInt(value); //2-10
        }

        public boolean isAce() {
            return value.equals("A");
        }
    }


    ArrayList<Card> deck;
    Random random = new Random();


    Card hiddenCard;
    ArrayList<Card> dealerHand;
    int dealerSum;
    int dealerAceCount;

    ArrayList<Card> playerHand;
    int playerSum;
    int playerAceCount;

    //window

    int boardWidth = 600;
    int boardHeight = 600;
    
    JFrame frame = new JFrame("Black Jack Game");
    JPanel gamePanel = new JPanel();




    BlackJack() {
        startGame();

        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel.setLayout(new BorderLayout());
        gamePanel.setBackground(new Color(50, 100, 80));
        frame.add(gamePanel);
    }

public void startGame() {
    buildDeck();
    shuffleDeck();

    dealerHand = new ArrayList<Card>();
    dealerSum = 0;
    dealerAceCount = 0;

    hiddenCard = deck.remove(deck.size() - 1);
    dealerSum += hiddenCard.getValue();
    dealerAceCount += hiddenCard.isAce() ? 1 : 0;

    Card card = deck.remove(deck.size() - 1);
    dealerSum += card.getValue();

    dealerAceCount += card.isAce() ? 1: 0;
    dealerHand.add(card);

    //player 

    playerHand = new ArrayList<Card>();
    playerSum = 0;
    playerAceCount = 0;

    for (int i =0; i < 2; i++) {
        card = deck.remove(deck.size()-1);
        playerSum += card.getValue();
        playerAceCount += card.isAce() ? 1 : 0;
        playerHand.add(card);

    }

}

public void buildDeck() {
    deck = new ArrayList<Card>();
    String[] values = {"A", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    String[] types = {"C", "D", "H", "S"};


    for (int i =0; i < types.length; i++) {
        for (int j =0; j < values.length; j++) {
            Card card = new Card(values[j], types[i]);
            deck.add(card);
        }
    }

}

public void shuffleDeck() {
    for (int i =0; i < deck.size(); i++) {
        int j = random.nextInt(deck.size());
        Card currentCard = deck.get(i);
        Card randomCard = deck.get(j);
        deck.set(i, randomCard);
        deck.set(j, currentCard);


    }

    System.out.println(deck);
}



}
