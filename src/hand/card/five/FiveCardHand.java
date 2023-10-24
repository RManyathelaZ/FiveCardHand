package hand.card.five; /**
 * 
 */

import java.util.ArrayList;
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

    private static ArrayList<Integer> pipList = new ArrayList<>();
    private static ArrayList<Character> suitList = new ArrayList<>();

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Shuffling... Shuffling... Shuffling... ");
        System.out.println("Your hand: " + generateFiveCardHand());
        System.out.println("You have: " + determineHand(pipList, suitList));
    }

    /**
     * 
     * @return five card hand
     */
    private static String generateFiveCardHand() {
        ArrayList<String> hand = new ArrayList<String>();

        Random randSuits = new Random();
        Random randPip = new Random();

        int tempPip;
        int tempSuit;

        for(int x = 0; x < 5; x++) {
            tempPip = randPip.nextInt(upperBoundPip) + 1;
            tempSuit = randSuits.nextInt(upperBoundSuits) + 1;

            if (x == 0){
                hand.add(x, generateCard(tempPip, tempSuit));
            } else {
                if (!hand.contains(getCard(tempPip, tempSuit))) {
                    hand.add(x, generateCard(tempPip, tempSuit));
                }
            }
        }
        
        return hand.toString();
    }
    
    /**
     * 
     * @param pipList the five card pips that were generated
     * @param suitList the five card suits that were generated
     * @return the conclusion on what the hand is
     */
    private static String determineHand(ArrayList<Integer> pipList, ArrayList<Character> suitList) {
        String conclusion = "";

        if(false) {
           conclusion = "Royal Flush";
        }  else if(false) {
            conclusion = "Straight Flush"; 
        } else if(false) {
            conclusion = "Four of a Kind";
        } else if(false) {
            conclusion = "Full House";
        }  else if(false) {
            conclusion = "Flush";
        } else if(false) {
            conclusion = "Straight";
        }  else if(false) {
            conclusion = "Three of a Kind";
        }  else if(false) {
            conclusion = "Two pair";
        } else if(false) {
            conclusion = "One Pair";
        } else if(findHighCard(pipList)){
            conclusion = "High card";
        }
        
        return conclusion;
    }

    private static boolean findHighCard(ArrayList<Integer> pip){
        int highCard = 0;

        for (int x = 0; x < pip.size() - 1; x++){
            if (pip.get(x+1) != null && pip.get(x) > pip.get(x+1)){
                highCard = pip.get(x);
            } else if (pip.get(x+1) != null && pip.get(x) < pip.get(x+1)){
                highCard = pip.get(x+1);
            }
        }

        return highCard > 0;
    }

    private static String generateCard(int pip, int suit) {
        String card = "";

        if(pip == 1 && suit == 1){
            card = String.valueOf(ACE) + SUITS.getSpade();
            pipList.add(pip);
            suitList.add(SUITS.getSpade());
        } else if(pip == 1 && suit == 2){
            card =  String.valueOf(ACE) + SUITS.getHeart();
            pipList.add(pip);
            suitList.add(SUITS.getHeart());
        } else if(pip == 1 && suit == 3){
            card =  String.valueOf(ACE) + SUITS.getDiamond();
            pipList.add(pip);
            suitList.add(SUITS.getDiamond());
        } else if(pip == 1 && suit == 4){
            card =  String.valueOf(ACE) + SUITS.getClub();
            pipList.add(pip);
            suitList.add(SUITS.getClub());
        } else if(pip == 11 && suit == 1){
            card =  String.valueOf(JACK) + SUITS.getSpade();
            pipList.add(pip);
            suitList.add(SUITS.getSpade());
        } else if(pip == 11 && suit == 2){
            card =  String.valueOf(JACK) + SUITS.getHeart();
            pipList.add(pip);
            suitList.add(SUITS.getHeart());
        } else if(pip == 11 && suit == 3){
            card =  String.valueOf(JACK) + SUITS.getDiamond();
            pipList.add(pip);
            suitList.add(SUITS.getDiamond());
        } else if(pip == 11 && suit == 4){
            card =  String.valueOf(JACK) + SUITS.getClub();
            pipList.add(pip);
            suitList.add(SUITS.getClub());
        } else if(pip == 12 && suit == 1){
            card =  String.valueOf(QUEEN) + SUITS.getSpade();
            pipList.add(pip);
            suitList.add(SUITS.getSpade());
        } else if(pip == 12 && suit == 2){
            card =  String.valueOf(QUEEN) + SUITS.getHeart();
            pipList.add(pip);
            suitList.add(SUITS.getHeart());
        } else if(pip == 12 && suit == 3){
            card =  String.valueOf(QUEEN) + SUITS.getDiamond();
            pipList.add(pip);
            suitList.add(SUITS.getDiamond());
        } else if(pip == 12 && suit == 4){
            card =  String.valueOf(QUEEN) + SUITS.getClub();
            pipList.add(pip);
            suitList.add(SUITS.getClub());
        } else if(pip == 13 && suit == 1){
            card =  String.valueOf(KING) + SUITS.getSpade();
            pipList.add(pip);
            suitList.add(SUITS.getSpade());
        } else if(pip == 13 && suit == 2){
            card =  String.valueOf(KING) + SUITS.getHeart();
            pipList.add(pip);
            suitList.add(SUITS.getHeart());
        } else if(pip == 13 && suit == 3){
            card =  String.valueOf(KING) + SUITS.getDiamond();
            pipList.add(pip);
            suitList.add(SUITS.getDiamond());
        } else if(pip == 13 && suit == 4){
            card =  String.valueOf(KING) + SUITS.getClub();
            pipList.add(pip);
            suitList.add(SUITS.getClub());
        } else if (suit == 1){
            card = String.valueOf(pip) + SUITS.getSpade();
            pipList.add(pip);
            suitList.add(SUITS.getSpade());
        } else if (suit == 2){
            card = String.valueOf(pip) + SUITS.getHeart();
            pipList.add(pip);
            suitList.add(SUITS.getHeart());
        } else if (suit == 3){
            card = String.valueOf(pip) + SUITS.getDiamond();
            pipList.add(pip);
            suitList.add(SUITS.getDiamond());
        } else if (suit == 4){
            card = String.valueOf(pip) + SUITS.getClub();
            pipList.add(pip);
            suitList.add(SUITS.getClub());
        }
        return card;
    }

    /**
     *
     */
    private static String getCard(int pip, int suit) {
        String card = "";
        if(pip == 1 && suit == 1){
            card = String.valueOf(ACE) + SUITS.getSpade();
        } else if(pip == 1 && suit == 2){
            card =  String.valueOf(ACE) + SUITS.getHeart();
        } else if(pip == 1 && suit == 3){
            card =  String.valueOf(ACE) + SUITS.getDiamond();
        } else if(pip == 1 && suit == 4){
            card =  String.valueOf(ACE) + SUITS.getClub();
        } else if(pip == 11 && suit == 1){
            card =  String.valueOf(JACK) + SUITS.getSpade();
        } else if(pip == 11 && suit == 2){
            card =  String.valueOf(JACK) + SUITS.getHeart();
        } else if(pip == 11 && suit == 3){
            card =  String.valueOf(JACK) + SUITS.getDiamond();
        } else if(pip == 11 && suit == 4){
            card =  String.valueOf(JACK) + SUITS.getClub();
        } else if(pip == 12 && suit == 1){
            card =  String.valueOf(QUEEN) + SUITS.getSpade();
        } else if(pip == 12 && suit == 2){
            card =  String.valueOf(QUEEN) + SUITS.getHeart();
        } else if(pip == 12 && suit == 3){
            card =  String.valueOf(QUEEN) + SUITS.getDiamond();
        } else if(pip == 12 && suit == 4){
            card =  String.valueOf(QUEEN) + SUITS.getClub();
        } else if(pip == 13 && suit == 1){
            card =  String.valueOf(KING) + SUITS.getSpade();
        } else if(pip == 13 && suit == 2){
            card =  String.valueOf(KING) + SUITS.getHeart();
        } else if(pip == 13 && suit == 3){
            card =  String.valueOf(KING) + SUITS.getDiamond();
        } else if(pip == 13 && suit == 4){
            card =  String.valueOf(KING) + SUITS.getClub();
        } else if (suit == 1){
            card = String.valueOf(pip) + SUITS.getSpade();
        } else if (suit == 2){
            card = String.valueOf(pip) + SUITS.getHeart();
        } else if (suit == 3){
            card = String.valueOf(pip) + SUITS.getDiamond();
        } else if (suit == 4){
            card = String.valueOf(pip) + SUITS.getClub();
        }

        return card;
    }
}
