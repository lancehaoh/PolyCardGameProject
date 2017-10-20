package cardGame;

import javax.swing.ImageIcon;

public class Card {
    
    /*******************************************************************************
     * Declares variables                                                          *
     *******************************************************************************
     */
    
    public static final String[] suitArray = {"DIAMOND", "CLUB", "HEARTS", "SPADE"};
    private int number;
    private int suit;
    private ImageIcon valueImg;
    private ImageIcon backImg;
    private ImageIcon valueImgSmall;
    private ImageIcon backImgSmall;
    private boolean showCard;

    /*******************************************************************************
     * Gets the variables and assign it to the respective names                    *                                       *
     *******************************************************************************
     */
    
    public Card(
            int suit, int number, ImageIcon valueImg, ImageIcon backImg,
            ImageIcon valueImgSmall, ImageIcon backImgSmall) {
        this.number = number;
        this.suit = suit;
        this.valueImg = valueImg;
        this.backImg = backImg;
        this.valueImgSmall = valueImgSmall;
        this.backImgSmall = backImgSmall;
    }
    
    /*******************************************************************************
     * Determines the orientation of the cards (face up or down)                   *                                      *
     *******************************************************************************
     */

    public void setShowCard(boolean value) {
        this.showCard = value;
    }

    /*******************************************************************************
     * Gets the cards to be displayed, if loop for orientation of cards            *                                                       *
     *******************************************************************************
     */
    
    public ImageIcon getDisplayImg() {
        if (showCard) {
            return valueImgSmall;
        } else {
            return backImgSmall;
        }
    }
    
    
    /*******************************************************************************
     * Gets the original sized image for the user and computer's selection.        *                                               *
     *******************************************************************************
     */

    public ImageIcon getLargeDisplayImg() {
        if (showCard) {
            return valueImg;
        } else {
            return backImg;
        }
    }
    
    /*******************************************************************************
     * Gets the details of the card (suit type) and returns the value              *
     *******************************************************************************
     **/

    public String getDetail() {
        if (number <= 10 && number != 1) {
            return (number + " OF " + suitArray[suit]);
        } else if (number == 11) {
            return ("JACK OF " + suitArray[suit]);
        } else if (number == 12) {
            return ("QUEEN OF " + suitArray[suit]);
        } else if (number == 13) {
            return ("KING OF " + suitArray[suit]);
        } else {
            return ("ACE OF " + suitArray[suit]);
        }
    }
    
    /*******************************************************************************
     * Compares the values of the cards (values, followed by suits)                *
     *******************************************************************************
     */

    public boolean isHigher(Card computerCard) {
        if (this.number != computerCard.number) {
            if (this.number == 1) {
                return true;
            } else if (this.number > computerCard.number) {
                if (computerCard.number == 1) {
                    return false;
                }else{
                    return true;
                }
            } else {
                return false;
            }
        } else {
            if (this.suit > computerCard.suit) {
                return true;
            } else {
                return false;
            }
        }
    }
}
