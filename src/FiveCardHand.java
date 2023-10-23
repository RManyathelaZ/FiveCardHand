/**
 * 
 */

import java.util.Random;

/**
 * @author robbie manyathela
 *
 */
public class FiveCardHand {
    
    public static class SUITS {
        // Using the order of highest to lowest - SHDC
        private final static char spade = 0x00002660;
        private final static char heart = 0x00002665;
        private final static char diamond = 0x00002666;
        private final static char club = 0x00002663;
        
        public static char getClub() {
            return club;
        }
        
        public static char getHeart() {
            return heart;
        }
        
        public static char getDiamond() {
            return diamond;
        }
        
        public static char getSpade() {
            return spade;
        }
    }
    
    private static final char ACE = 'A';
    private static final char JACK = 'J';
    private static final char QUEEN = 'Q';
    private static final char KING = 'K';
    
    private static final int upperBoundPip = 13;  // generates numbers between 0 to 12
    private static final int upperBoundSuits = 4;  // generates numbers between 0 to 3

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Shuffling... Shuffling... Shuffling... ");
        System.out.println("Your hand: " + generateFiveCardHand());
    }

    /**
     * 
     * @return five card hand
     */
    private static String generateFiveCardHand() {
        StringBuilder hand = new StringBuilder();

        Random randSuits = new Random();
        Random randPip = new Random();

        int tempPip = 0;
        int tempSuit = 0;

        for(int x = 0; x < 5; x++){
            tempPip = randPip.nextInt(upperBoundPip) + 1;
            tempSuit = randSuits.nextInt(upperBoundSuits) + 1;

            switch(tempPip){
                case 1:     // Ace - A
                    hand.append(ACE);
                    break;
                case 11:    // Jack - J
                    hand.append(JACK);
                    break;
                case 12:    // Queen - Q
                    hand.append(QUEEN);
                    break;
                case 13:    // King - K
                    hand.append(KING);
                    break;
                default:    // 2 to 10
                    hand.append(tempPip);
                    break;
            }

            switch (tempSuit){
                case 1:
                    hand.append(SUITS.getSpade());
                    break;
                case 2:
                    hand.append(SUITS.getHeart());
                    break;
                case 3:
                    hand.append(SUITS.getDiamond());
                    break;
                case 4:
                    hand.append(SUITS.getClub());
                    break;
            }
        }
        
        return hand.toString();
    }
    
    /**
     * 
     * @param hand the five card hand that was generated
     * @return the conclusion on what the hand is
     */
    private String determineHand(String hand) {
        String conclusion = "";
        
        StringBuffer buffer = new StringBuffer();
        
        if(hand.contentEquals(buffer)) {
           conclusion = "Royal Flush";
        }  else if(hand.contentEquals(buffer)) {
            conclusion = "Straight Flush"; 
        } else if(hand.contentEquals(buffer)) {
            conclusion = "Four of a Kind";
        } else if(hand.contentEquals(buffer)) {
            conclusion = "Full House";
        }  else if(hand.contentEquals(buffer)) {
            conclusion = "Flush";
        } else if(hand.contentEquals(buffer)) {
            conclusion = "Straight";
        }  else if(hand.contentEquals(buffer)) {
            conclusion = "Three of a Kind";
        }  else if(hand.contentEquals(buffer)) {
            conclusion = "Two pair";
        } else if(hand.contentEquals(buffer)) {
            conclusion = "Pair";
        } else if(hand.contentEquals(buffer)){
            conclusion = "High card";
        }
        
        return conclusion;
    }
}
