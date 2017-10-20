
package cardGame;

/*******************************************************************************
 * Initiates array and Card                                                    *
 *******************************************************************************
 */

public class Player {
    public Card[] playerHand;
    private Card selectedCard;
    
    /*******************************************************************************
     * set no of cards to be dealt to the player                                   *
     *******************************************************************************
     */
    
    public Player(int handSize){
        
        playerHand = new Card[handSize];
    }
    
    /*******************************************************************************
     * returns the hand size                                                       *
     *******************************************************************************
     */
    public int getHandSize(){
        return playerHand.length;
    }
    
    /*******************************************************************************
     * gets the selected card from the array and assigns it to selectedCards       *
     *******************************************************************************
     */
    
    public void setSelectedCard(int selected){
        selectedCard = playerHand[selected];
    }
    
    /*******************************************************************************
     * gets the selected card and returns it                                       *
     *******************************************************************************
     */
    
    public Card getSelectedCard(){
        return selectedCard;
    }
}