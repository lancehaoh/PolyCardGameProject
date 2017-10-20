package cardgame;

import javax.swing.*;

public class GameFrameUser {
        public static void main(String[] args) {
        GameFrame e = new GameFrame();  // Initiates a new game
        e.setTitle("Card Game");                // Sets the title of the program
        e.setSize(600, 700);                    // Sets the size of the program
        e.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Closes the program when the program is closed
        e.setVisible(true);                     // Sets the visibility of the program
        e.setResizable(false);
    }
}
