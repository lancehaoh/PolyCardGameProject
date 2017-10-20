package cardGame;

import javax.swing.*;
import java.awt.*;

public class CardGame {
    
    /*************************************************************************************
     * Set the number of cards that are dealt to each player(limited to 52/no of players)*
     * ***********************************************************************************
     */

    Player human;
    Player computer;
    private Deck d1;
    private int setGuess = -1;
    private int score;
    private int humanWins;
    private int computerWins;
    private int computerSelection;
    private int turn = 1;//Keep track of the turn
    private int humanTempScore = 0;
    private int computerTempScore = 0;
    private String status = "";//Keep track of the cards drawn
    private String outcome;
    boolean result;
    private JPanel statsPanel;
    private JTextArea[] statistics;
    private String[] statsLbl = {"Turn", "Your Card", "Computer's card", "Outcome", "Score"};

    /*******************************************************************************
     * Game starts here                                                            *
     *******************************************************************************
     */
    public CardGame() {
        score = 0;
        humanWins = 0;
        computerWins = 0;
        humanTempScore = 0;
        computerTempScore = 0;
        human = new Player(5);//Create a human player
        computer = new Player(5);//Createa a computer player
        d1 = new Deck();//Create a new deck
        d1.shuffle();//Shuffle the deck
        statsPanel = new JPanel(new GridLayout(1, 6));//Create a panel to contain the game statistics
        statistics = new JTextArea[5];
        for (int i = 0; i < statistics.length; i++) {//Create text fields to receive game statistics
            statistics[i] = new JTextArea(10, 12);
            statistics[i].setText(statsLbl[i]);
            statistics[i].setEditable(false);
            statsPanel.add(statistics[i]);
        }
        for (int i = 0; i < human.getHandSize(); i++) {
            human.playerHand[i] = d1.dealACard();
            human.playerHand[i].setShowCard(true);
            computer.playerHand[i] = d1.dealACard();
            computer.playerHand[i].setShowCard(false);
        }
    }

    /*******************************************************************************
     * Get and validate user input                                                 *
     *******************************************************************************
     */
    public void setGuess(int i) {//Prompt the user to select a card from his hand
        do {
            setGuess = i;
        } while (setGuess < 0 || setGuess > (human.getHandSize()));

        //Player has a selected card
        human.setSelectedCard(setGuess);

        //Computer chooses a card
        if (computerSelection == 0) {
            computerSelection = 1;
        }
        computer.setSelectedCard((computer.getHandSize() - 1) - (computerSelection % (computer.getHandSize())));
        computer.getSelectedCard().setShowCard(true);
    }

    /**********************************************************************************
     * Compute and return the result                                                  *
     **********************************************************************************
     */
    
    public void computeResult() {


        if (human.getSelectedCard().isHigher(computer.getSelectedCard())) {
            score++;
            humanWins++;
            humanTempScore++;
            outcome = "You win";         
            result = true;               
        } else {
            score--;
            computerWins++;
            computerTempScore++;
            outcome = "You lose";
            result = false;
        }
        setStats();
        turn++;
        computerSelection++;
    }

    /*******************************************************************************
     *Displays the result in a JOPane after every round                            *
     *******************************************************************************
     */
    
    public void getResult(JFrame parent) {
        String msg;
        if (result) {
            msg = "You won!\nComputer lost!\nYou gain 1 point\nYour score is "
                    + score;
        } else {
            msg = "You lost!\nComputer won!\nYou lose 1 point\nYour score is "
                    + score;
        }
        setGuess = -1;
        JOptionPane.showMessageDialog(parent, msg);
    }
    
    /*******************************************************************************
     * Retrieves the image for the human hand for selection                        *
     * *****************************************************************************
     */

    public ImageIcon getHumanHandIcon(int i) {
        return human.playerHand[i].getDisplayImg();
    }

    /*******************************************************************************
     * Retrieves the image for the card chosen                                     *
     *******************************************************************************
     */
    
    public ImageIcon getHumanGuessIcon() {
        return human.getSelectedCard().getLargeDisplayImg();
    }
    
    /*******************************************************************************
     * Retrieves the image for the computer card selection                         *
     *******************************************************************************
     */
    
    public ImageIcon getComputerHandIcon(int i) {
        return computer.playerHand[i].getDisplayImg();
    }
    /*******************************************************************************
     * Retrieves the image for the computer card chosen                            *
     *******************************************************************************
     */
    public ImageIcon getComputerGuessIcon() {
        return computer.getSelectedCard().getLargeDisplayImg();
    }
    
    /*******************************************************************************
     * Appends statistics to statistics data                           *
     *******************************************************************************
     */

    public void setStats() {
        statistics[0].append("\n" + turn);
        statistics[1].append("\n" + human.getSelectedCard().getDetail());
        statistics[2].append("\n" + computer.getSelectedCard().getDetail());
        statistics[3].append("\n" + outcome);
        statistics[4].append("\n" + score);
    }

    
        public void getComments(JFrame parent) {
            String comments = "";
        switch (humanTempScore) {
            case 0:
                comments = "You are really lousy!";
                break;
            case 1:
                comments = "You need to rethink your strategy!";
                break;
            case 2:
                comments = "Try harder next time!";
                break;
            case 3:
                comments = "You are quite good!";
                break;
            case 4:
                comments = "You are very good!";
                break;
            case 5:
                comments = "You are an awesome player!";
                break;
        }
        JOptionPane.showMessageDialog(
                parent,
                "You acheived " + humanTempScore + " - " + 
                computerTempScore + " this round. " + comments,
                "Comments",
                JOptionPane.INFORMATION_MESSAGE);  
    }
    /*******************************************************************************
     * Displays the statistics                                                        *
     *******************************************************************************
     */
    
    public void getStats(JFrame parent) {
        JOptionPane.showMessageDialog(parent, statsPanel, "Statistics Summary", JOptionPane.INFORMATION_MESSAGE);
    }

    /*******************************************************************************
     * Returns the selected card icon when called                                  *
     *******************************************************************************
     */
    
    public ImageIcon getComputerCardIcon() {
        return computer.getSelectedCard().getDisplayImg();
    }
    
    /*******************************************************************************
     * Returns the computer's guess after passing it through a formula             *
     *******************************************************************************
     */

    public int getComputerGuess() {
        return (computer.getHandSize() - 1) - (computerSelection % (computer.getHandSize()));
    }
    
    /*******************************************************************************
     * Returns the user's guess                                                    *
     *******************************************************************************
     */

    public int getHumanGuess() {
        return setGuess;
    }
    
    /*******************************************************************************
     * Returns the method if user wins                                             *
     *******************************************************************************
     */

    public int getHumanWins() {
        return humanWins;
    }
    
    
    /*******************************************************************************
     * Returns the method if computer wins                                         *
     *******************************************************************************
     */

    public int getComputerWins() {
        return computerWins;
    }
    
    /*******************************************************************************
     * gets and returns the score                                                  *
     *******************************************************************************
     */

    public int getScore() {
        return score;
    }
    
    /*******************************************************************************
     * gets the human's hand size and returns it                                   *
     *******************************************************************************
     */

    public int getHumanHandSize() {
        return human.getHandSize();
    }
    
    /*******************************************************************************
     * get the computer's hand size and returns it                                 *
     *******************************************************************************
     */

    public int getComputerHandSize() {
        return computer.getHandSize();
    }
    
    /*******************************************************************************
     * starts another cycle of dealing the cards                                   *
     *******************************************************************************
     */

    public void nextRound() {
        for (int i = 0; i < human.getHandSize(); i++) {
            humanTempScore = 0;
            computerTempScore = 0;
            human.playerHand[i] = d1.dealACard();
            human.playerHand[i].setShowCard(true);
            computer.playerHand[i] = d1.dealACard();
            computer.playerHand[i].setShowCard(false);
        }
    }
    
    /*******************************************************************************
     * restarts the game when the user wants to or when there are no more cards    *
     *******************************************************************************
     */

    public void restartGame() {
        score = 0;
        humanWins = 0;
        computerWins = 0;
        humanTempScore = 0;
        computerTempScore = 0;
        turn = 1;
        d1.resetCardPosition();
        d1.shuffle();
        for (int i = 0; i < statistics.length; i++) {//Create text fields to receive game statistics
            statistics[i].setText(statsLbl[i]);
        }
        for (int i = 0; i < human.getHandSize(); i++) {
            human.playerHand[i] = d1.dealACard();
            human.playerHand[i].setShowCard(true);
            computer.playerHand[i] = d1.dealACard();
            computer.playerHand[i].setShowCard(false);
        }
    }
    
    public ImageIcon getComCardImage(int posNum){
        computer.setSelectedCard(posNum);
        computer.getSelectedCard().setShowCard(true);
        return getComputerCardIcon();
    }

    /******************************************************************************
     * Test whether game has ended                                                *
     ******************************************************************************
     */
    public boolean endGame() {
        //If the deck still has cards for another round, continue the game
        return d1.currentCardPosition + (human.getHandSize() * 2) >= d1.getSize();
    }
}