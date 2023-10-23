/**
 * 
 */

/**
 * @author robbie manyathela
 *
 */
public class FiveCardHand {
    
    public class SUITS {
        char club = 0x00002663;
        char spade = 0x00002660;
        char heart = 0x00002665;
        char diamond = 0x00002666;
        
        public char getClub() {
            return club;
        }
        
        public char getHeart() {
            return heart;
        }
        
        public char getDiamond() {
            return diamond;
        }
        
        public char getSpade() {
            return spade;
        }
    }
    
    private final char ACE = 'A';
    private final char JACK = 'J';
    private final char QUEEN = 'Q';
    private final char KING = 'K';
    
    private final int upperBoundPip = 13;  // generates numbers between 0 to 12
    private final int upperBoundSuits = 4;  // generates numbers between 0 to 3

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    /**
     * 
     * @return five card hand
     */
    private String generateFiveCardHand() {
        String hand = "";
        
        return hand;
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
