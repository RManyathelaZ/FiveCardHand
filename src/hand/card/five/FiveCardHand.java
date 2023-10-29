package hand.card.five; /**
 * 
 */

import java.util.*;

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

    private final static ArrayList<Integer> PIP_RF_ORDER = new ArrayList<Integer>(Arrays.asList(10, 11, 12, 13, 1));    // 10, J, , Q, K, A
    private final static ArrayList<Integer> PIP_RF_ORDER_REVERSED = new ArrayList<Integer>(Arrays.asList(1, 13, 12, 11, 10));   // A, K, Q, J, 10

    private final static ArrayList<List<Integer>> PIP_SF_ORDER = new ArrayList<List<Integer>>();

    /**
     * @param args
     */
    public static void main(String[] args) {
        populateArrayCombinations();

        System.out.println("Shuffling... Shuffling... Shuffling... ");
        System.out.println("Your hand: " + generateFiveCardHand());
        System.out.println("You have: " + determineHand(pipList, suitList));
    }

    /**
     * 
     * @return five card hand generated
     */
    private static String generateFiveCardHand() {
        ArrayList<String> hand = new ArrayList<String>();

        Random randSuits = new Random();
        Random randPip = new Random();

        int tempPip;
        int tempSuit;
        try {
            for (int x = 0; x < 5; x++) {
                tempPip = randPip.nextInt(upperBoundPip) + 1;
                tempSuit = randSuits.nextInt(upperBoundSuits) + 1;

                if (x == 0) {
                    hand.add(x, generateCard(tempPip, tempSuit));
                } else {
                    if (!hand.contains(getCard(tempPip, tempSuit))) {
                        hand.add(x, generateCard(tempPip, tempSuit));
                    } else {
                        generateFiveCardHand();     // recursively call the method to avoid duplicates
                    }
                }
            }
        } catch (Exception ex) {
            generateFiveCardHand();     // recursively call the method to avoid duplicates
        }

        String handOutput = "";
        for (String card: hand) {
            handOutput += card.concat(" ");
        }

        return handOutput;
    }
    
    /**
     * 
     * @param pipList the five card pips that were generated
     * @param suitList the five card suits that were generated
     * @return the conclusion on what the hand is
     *
     */
    private static String determineHand(ArrayList<Integer> pipList, ArrayList<Character> suitList) {
        String conclusion = "";

        if(royalFush(pipList, suitList)) {
           conclusion = "Royal Flush";
        }  else if(straightFlush(pipList, suitList)) {
            conclusion = "Straight Flush"; 
        } else if(determinePairHand(pipList, suitList) == 4) {
            conclusion = "Four of a Kind";
        } else if(fullHouse(pipList, suitList) == 5) {
            conclusion = "Full House";
        }  else if(flush(suitList)) {
            conclusion = "Flush";
        } else if(Straight(pipList)) {
            conclusion = "Straight";
        }  else if(determinePairHand(pipList, suitList) == 3) {
            conclusion = "Three of a Kind";
        }  else if(determinePairHand(pipList, suitList) == 2) {
            conclusion = "Two pair";
        } else if(determinePairHand(pipList, suitList) == 1) {
            conclusion = "One Pair";
        } else if(findHighCard(pipList)){
            conclusion = "High card";
        }
        
        return conclusion;
    }

    /**
     *
     * @param pip the list of Pips from the five hand
     * @return The highest ranked card in your hand with an 'A' being the highest and '2' being the lowest
     *
     */
    private static boolean findHighCard(ArrayList<Integer> pip){
        int highCard = 0;

        for (int x = 0; x < pip.size(); x++){
            if( highCard < pip.get(x)) {
                highCard = pip.get(x);
            }
        }

        return highCard > 0;
    }

    /**
     * Will create the card - Pip & suit combination for high ranking cards
     */
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
     * Will create the card - Pip & suit combination for high ranking cards
     * Used to search the card
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

    /**
     *
     * @param suits - the list of the cards' suit
     * @return - the result as to whether hand is a flush
     *
     */
    private static boolean flush(ArrayList<Character> suits) {
        boolean result = false;
        for (int x = 0; x < suits.size(); x++) {
            if (x == 4) {
                result = suits.get(4) == suits.get(3);
            } else if (suits.get(x) == suits.get(x+1)) {
                result = true;
            }
        }

        return result;
    }

    /**
     *
     * @param pips - list of the card's Pip value
     * @return whether the hand has Five cards of any suit, in sequential order
     *
     */
    private static boolean Straight(ArrayList<Integer> pips) {
        boolean result = false;

        for (List<Integer> pipCombo: PIP_SF_ORDER){
            if (pips.equals(pipCombo)){
                result = true;
            }
        }

        return result;
    }

    /**
     *
     * @param pips - list of the card's Pip value
     * @param suits - list of the card's suit value
     * @return whether the hand has Five cards of the same suit in sequential order
     *
     */
    private static boolean straightFlush(ArrayList<Integer> pips, ArrayList<Character> suits) {
        boolean isSameSuit = sameSuit(suits);
        boolean result = false;

        for (List<Integer> pipCombo: PIP_SF_ORDER){
            if (pips.equals(pipCombo) && isSameSuit){
                result = true;
            }
        }

        return result;
    }

    /**
     *
     * @param pips - the list of the card's pip
     * @param suits - the list of the card's suits
     * @return - the result as to whether the hand is a royal flash.
     *
     */
    private static boolean royalFush(ArrayList<Integer> pips, ArrayList<Character> suits) {
        boolean result = false;

        if((pips.equals(PIP_RF_ORDER) || pips.equals(PIP_RF_ORDER_REVERSED) && sameSuit(suits))) {
            result = true;
        }

        return result;
    }

    // determines that the hand has the same suit
    private static boolean sameSuit(ArrayList<Character> suits) {
        return (suits.get(4) == suits.get(0) && suits.get(0) == suits.get(1) && suits.get(1) == suits.get(2) &&
                suits.get(2) == suits.get(3) && suits.get(3) == suits.get(4));
    }

    // the combination of the consecutive pip s for the cards that will qualify as a straight flush
    // either in ascending or descending order
    private static void populateArrayCombinations(){
        ArrayList<Integer> order1 = new ArrayList<Integer>(Arrays.asList(2,3,4,5,6));
        ArrayList<Integer> order2 = new ArrayList<Integer>(Arrays.asList(3,4,5,6,7));
        ArrayList<Integer> order3 = new ArrayList<Integer>(Arrays.asList(4,5,6,7,8));
        ArrayList<Integer> order4 = new ArrayList<Integer>(Arrays.asList(5,6,7,8,9));
        ArrayList<Integer> order5 = new ArrayList<Integer>(Arrays.asList(6,7,8,9,10));
        // reverse
        ArrayList<Integer> order6 = new ArrayList<Integer>(Arrays.asList(6,5,4,3,2));
        ArrayList<Integer> order7 = new ArrayList<Integer>(Arrays.asList(7,6,5,4,3));
        ArrayList<Integer> order8 = new ArrayList<Integer>(Arrays.asList(8,7,6,5,4));
        ArrayList<Integer> order9 = new ArrayList<Integer>(Arrays.asList(9,8,7,6,5));
        ArrayList<Integer> order10 = new ArrayList<Integer>(Arrays.asList(10,9,8,7,6));

        PIP_SF_ORDER.add(order1);
        PIP_SF_ORDER.add(order2);
        PIP_SF_ORDER.add(order3);
        PIP_SF_ORDER.add(order4);
        PIP_SF_ORDER.add(order5);
        PIP_SF_ORDER.add(order6);
        PIP_SF_ORDER.add(order7);
        PIP_SF_ORDER.add(order8);
        PIP_SF_ORDER.add(order9);
        PIP_SF_ORDER.add(order10);
    }

    /**
     * To check the number of Pips with the same value
     * @param pips - the list of the hand's pips
     * @return - map, with the pip as key, the indices as the list of numbers
     *
     */
    private static Map<Integer, List<Integer>> determineNumberOfPairs(ArrayList<Integer> pips) {
        int indexToSkip = -1;
        Map<Integer, List<Integer>> results = new HashMap<>();
        for(int x = 0; x < pips.size(); x++){
            indexToSkip = x;
            List<Integer> values = new ArrayList<Integer>();
            for (int y = 0; y < pips.size(); y++){
                if(y == x) continue;;       // to skip lookup of the current index to compare with
                if(pips.get(x) == pips.get(y)){
                    if(!values.contains(x)) values.add(x);
                    if(!values.contains(y)) values.add(y);
                }
            }
            if (values.size() > 1) results.put(pips.get(x), values);
        }
        return results;
    }

    /**
     * @param pips - list of the card's Pip value
     * @param suits - list of the card's suit value
     * @return to determine the number of pairs the hand has
     *
     */
    private static int determinePairHand(ArrayList<Integer> pips, ArrayList<Character> suits) {
        int result = 0;

        Map<Integer, List<Integer>> pairCount = determineNumberOfPairs(pips);

        if (pairCount.size() == 0) return result;

        if (pairCount.size() == 1) {     // when hand has one pair record
            for (int i : pairCount.keySet()) {
                if (pairCount.get(i).size() == 2) {    // pair
                    int index_a = pairCount.get(i).get(0);
                    int index_b = pairCount.get(i).get(1);

                    if (suits.get(index_a) != suits.get(index_b)) result = 1;
                } else if (pairCount.get(i).size() == 3) {      // potentially Three of a kind
                    int index_a = pairCount.get(i).get(0);
                    int index_b = pairCount.get(i).get(1);
                    int index_c = pairCount.get(i).get(2);

                    if (suits.get(index_a) != suits.get(index_b) &&
                            suits.get(index_b) != suits.get(index_c) &&
                            suits.get(index_c) != suits.get(index_a)) {
                        result = 3;
                    }
                } else if (pairCount.get(i).size() == 4) {      // potentially Four of a kind
                    int index_a = pairCount.get(i).get(0);
                    int index_b = pairCount.get(i).get(1);
                    int index_c = pairCount.get(i).get(2);
                    int index_d = pairCount.get(i).get(2);

                    if (pips.get(index_a) == pips.get(index_b) &&
                            pips.get(index_b) == pips.get(index_c) &&
                            pips.get(index_c) == pips.get(index_d) &&
                            pips.get(index_d) == pips.get(index_a)) {
                        result = 4;
                    }
                }
            }
        } else if (pairCount.size() == 2) {       // when hand has two records
            for (int i : pairCount.keySet()) {   //  two pair hand
                if (pairCount.get(i).size() == 2) {    // pair
                    int index_a = pairCount.get(i).get(0);
                    int index_b = pairCount.get(i).get(1);

                    if (suits.get(index_a) != suits.get(index_b)) result += 1;      // return 2
                }
            }
        }
        return result;
    }

    private static int fullHouse(ArrayList<Integer> pips, ArrayList<Character> suits) {
        int result = 0;

        Map<Integer, List<Integer>> pairCount = determineNumberOfPairs(pips);

        if (pairCount.size() == 0) return result;

        if (pairCount.size() == 2) {
            for (int i: pairCount.keySet()) {// potentially Full house
                if (pairCount.get(i).size() == 2) {
                    int index_a = pairCount.get(i).get(0);
                    int index_b = pairCount.get(i).get(1);

                    if ((pips.get(index_a) == pips.get(index_b)) && (suits.get(index_a) != suits.get(index_b))) {
                        result += 2;
                    }
                } else if (pairCount.get(i).size() == 3) {
                    int index_a = pairCount.get(i).get(0);
                    int index_b = pairCount.get(i).get(1);
                    int index_c = pairCount.get(i).get(2);

                    if (((pips.get(index_a) == pips.get(index_b)) && (pips.get(index_b) == pips.get(index_c)) &&
                        (pips.get(index_c) == pips.get(index_a))) && ((suits.get(index_a) != suits.get(index_b)) &&
                            (suits.get(index_b) != suits.get(index_c)) && (suits.get(index_c) != suits.get(index_a)))) {
                            result += 3;
                    }
                }
            }
        }

        return result;
    }
}
