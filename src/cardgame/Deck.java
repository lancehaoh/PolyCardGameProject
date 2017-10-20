package cardGame;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Deck {
    /*******************************************************************************
     * Initiates the deck of cards, giving the array a value                       *
     *******************************************************************************
     */
    Card[] deck = new Card[52];
    int currentCardPosition;
    
    /*******************************************************************************
     * Back image initialized, and scaled accordingly                              *
     *******************************************************************************
     */

    public Deck() {
        ImageIcon backImg = new ImageIcon("cardimg\\r1.png");
        ImageIcon backImgSmall = backImg;
        backImgSmall = new ImageIcon(backImg.getImage().getScaledInstance(60, 80, Image.SCALE_SMOOTH));
        
     /*******************************************************************************
      * Assigns the numerical value to an image card                                *
      *******************************************************************************
      */
        
        for (int i = 0; i < deck.length; i++) {
            ImageIcon frontImg = new ImageIcon("cardimg\\" + (i+1) + ".png");
            ImageIcon frontImgSmall = frontImg;
            frontImgSmall = new ImageIcon(frontImg.getImage().getScaledInstance(60, 80, Image.SCALE_SMOOTH));
            deck[i] = new Card(
                    i / 13, (i % 13) + 1, frontImg, backImg, 
                    frontImgSmall, backImgSmall);
        }
    }
    
    /*******************************************************************************
     * Returns the value of the deck's size                                        *
     *******************************************************************************
     */
    
    public int getSize() {
        return deck.length;
    }
    
    /*******************************************************************************
     * gets the detail (suits)  of the card                                        *
     ******************************************************************************* 
     */

    public void printDetail() {
        for (int i = 0; i < deck.length; i++) {
            deck[i].getDetail();
        }
    }

    
    /*******************************************************************************
     * Shuffles the card                                                           *
     *******************************************************************************
     */
    
    public void shuffle() {
        currentCardPosition = 0;
        for (int i = 0; i < 10000; i++) {
            int pos_1 = (int) (Math.random() * deck.length);
            int pos_2 = (int) (Math.random() * deck.length);

            Card tempPos = deck[pos_1];
            deck[pos_1] = deck[pos_2];
            deck[pos_2] = tempPos;
        }
    }
    
    /*******************************************************************************
     * If there are still cards left, deal. If not, return value.                  *
     *******************************************************************************
     */
    
    public Card dealACard() {
        if (currentCardPosition == deck.length) {
            return null;
        } else {            
            return deck[currentCardPosition++];
        }
    }
    
    /*******************************************************************************
     * If reset is called, or set card position to 0                               *
     *******************************************************************************
     */
    
    public void resetCardPosition(){
        currentCardPosition = 0;
    }
    
    
}
