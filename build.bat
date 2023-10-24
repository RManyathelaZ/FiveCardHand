REM #######  Build Instruction  ########

javac -d classes src/hand/card/five/FiveCardHand.java

REM ######  Execute the jar file(s)  ########

jar cfe FiveCardHand.jar hand.card.five.FiveCardHand hand/card/five/FiveCardHand.class

REM #######  Run the jar file  #########

java -cp FiveCardHand.jar hand.card.five.FiveCardHand