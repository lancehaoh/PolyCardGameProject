package cardgame;

import cardGame.CardGame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Font;
import javax.swing.border.TitledBorder;

public class GameFrame extends JFrame implements ActionListener {

    Font f = new Font("Serif", Font.BOLD, 18);
    Font g = new Font("Arial", Font.BOLD, 30);
    /*
     * PRIVATE -> Java GUI Elements
     */
    private JMenu fileMenu;
    private JMenu fileMenu2;
    private JMenu fileMenu3;
    private JMenuBar menuBar;
    private JMenuItem menuItems[];
    private JMenuItem menuItems2[];
    private JMenuItem menuItems3[];
    private JMenuItem menuItems4[];
    private TileBackgroundPanel backgroundPanel;
    /*
     * External / Features
     */
    private String items[] = {"Music off", "Track 1", "Track 2"};
    private char itemMnemonics[] = {'M', 'G', 'P'};
    private String items2[] = {"Default", "Black", "Poker", "Sky", "Castle", "Arthur"};
    private char itemMnemonics2[] = {'D','B', 'P', 'S', 'C', 'A'};
    private String items3[] = {"Default", "Red", "Orange", "Blue"};
    private char itemMnemonics3[] = {'D', 'R', 'O', 'B'};
    private String[] sounds = {"sound/Evening_On_The_River.wav", "sound/The_Sad_Rose.wav", "sound/gun.wav"};
    private String[] image = {"","img/black.jpg", "img/poker.jpg", "img/blue.jpg", "img/castle.jpg", "img/arthur.jpg"};
    private SoundList mySoundList;
    JMenu menu, submenu;
    JMenuItem menuItem;
    JMenuItem menuItem2;
    CardGame game = new CardGame();
    /*
     * Player/ AI Interactions
     */
    // Player hands 
    private JButton[] humanHandBtn;
    private JButton[] computerHandBtn;
    private JLabel title;
    private JLabel humanLbl;
    private JLabel computerLbl;
    private JPanel humanCardPnl;
    private JPanel computerCardPnl;
    private JPanel northPnl;
    private JPanel westPnl;
    private JPanel eastPnl;
    // These are for the center text area, as well as the panels for the selected cards. 
    private JPanel centerPnl;
    private JPanel centerPnlTop;
    private JPanel centerPnlBot;
    private JTextArea statistics;
    // These are for the bottom panel, which contains restart, showcards, exit and features. 
    private JButton[] controlBtn;
    private String[] buttonLbl = {"Draw Cards", "Restart", "Statistics", "Cheat!", "Exit"};
    private JPanel bannerPnl;
    private int cardsInHand = game.getHumanHandSize();
    private JPanel southPnl;
    // These displays the user's and computer's selected cards. 
    private JButton centerPnlUSelection;
    private JButton centerPnlCSelection;
    int gameStyle = 3;
    private Color themeClr;
    private TitledBorder t, t2, t3, t4, t5;

    private Color setFontVar() {
        Color myColor;
        switch (gameStyle) {
            case 1:
                myColor = Color.GREEN;
                break;
            case 2:
                myColor = Color.WHITE;
                break;
            default:
                myColor = Color.BLACK;
        }
        return myColor;
    }

    private void setOpacity(boolean opacity) {
        fileMenu.setOpaque(opacity);
        menuBar.setOpaque(opacity);
        title.setOpaque(opacity);
        humanLbl.setOpaque(opacity);
        computerLbl.setOpaque(opacity);
        humanCardPnl.setOpaque(opacity);
        computerCardPnl.setOpaque(opacity);
        northPnl.setOpaque(opacity);
        westPnl.setOpaque(opacity);
        eastPnl.setOpaque(opacity);
        // These are for the center text area, as well as the panels for the selected cards. 
        centerPnl.setOpaque(opacity);
        centerPnlTop.setOpaque(opacity);
        centerPnlBot.setOpaque(opacity);
        statistics.setOpaque(opacity);
        centerPnlUSelection.setOpaque(opacity);
        centerPnlUSelection.setContentAreaFilled(opacity);
        centerPnlCSelection.setOpaque(opacity);
        centerPnlCSelection.setContentAreaFilled(opacity);
        // These are for the bottom panel, which contains restart, showcards, exit and features. 

        for (int i = 0; i < controlBtn.length; i++) {
            controlBtn[i].setContentAreaFilled(opacity);
            controlBtn[i].setOpaque(opacity);
        }

        for (int i = 0; i < humanHandBtn.length; i++) {
            humanHandBtn[i].setContentAreaFilled(opacity);
            humanHandBtn[i].setOpaque(opacity);
        }

        for (int i = 0; i < computerHandBtn.length; i++) {
            computerHandBtn[i].setContentAreaFilled(opacity);
            computerHandBtn[i].setOpaque(opacity);
        }

        //private JRadioButton[] colorBtn;

        bannerPnl.setOpaque(opacity);
        southPnl.setOpaque(opacity);
        // These displays the user's and computer's selected cards. 
        centerPnlUSelection.setOpaque(opacity);
        centerPnlCSelection.setOpaque(opacity);

    }

    private void backgroundImage(String image) {
        TileBackgroundPanel temp = (TileBackgroundPanel) getContentPane();
        temp.setTileIcon(new ImageIcon(image));
    }

    public Color changeTheme(Color themeClr) {
        for (int i = 0; i < controlBtn.length; i++) {
            controlBtn[i].setForeground(themeClr);
        }
        title.setForeground(themeClr);
        fileMenu.setForeground(themeClr);
        fileMenu2.setForeground(themeClr);
        fileMenu3.setForeground(themeClr);
        humanLbl.setForeground(themeClr);   
        computerLbl.setForeground(themeClr);
        statistics.setForeground(themeClr);
        t.setTitleColor(themeClr);
        t2.setTitleColor(themeClr);
        t3.setTitleColor(themeClr);
        t4.setTitleColor(themeClr);
        t5.setTitleColor(themeClr);
        return themeClr;
    }
    
    public GameFrame() {

        ////////////////////////////////////////
        fileMenu = new JMenu("Audio");
        menuBar = new JMenuBar();
        menuItems = new JMenuItem[items.length];

        fileMenu.setMnemonic(0); //Add mnemonic on the JMenu "Audio"  
        menuBar.add(fileMenu); //Adding JMenu on JMenuBar  
        //Constructing JMenuItem using "for loop"  
        for (int count = 0; count < menuItems.length; count++) {
            menuItems[count] = new JMenuItem(items[count]); //Constructing JMenuItem with the Specified String menus 
            menuItems[count].addActionListener(this);
            menuItems[count].setMnemonic(itemMnemonics[count]); //Adding mnemonics on JMenuItem  
            fileMenu.add(menuItems[count]); //Add JMenuItem on JMenu  
        }
        ////////////////////////////////////////
        fileMenu2 = new JMenu("Background");
        fileMenu2.setForeground(setFontVar());
        menuItems2 = new JMenuItem[items2.length];

        fileMenu2.setMnemonic('B'); //Add mnemonic on the JMenu "Background"  
        menuBar.add(fileMenu2); //Adding JMenu on JMenuBar  
        //Constructing JMenuItem using "for loop"  
        for (int count = 0; count < menuItems2.length; count++) {
            menuItems2[count] = new JMenuItem(items2[count]); //Constructing JMenuItem with the Specified String menus 
            menuItems2[count].addActionListener(this);
            menuItems2[count].setMnemonic(itemMnemonics2[count]); //Adding mnemonics on JMenuItem  
            fileMenu2.add(menuItems2[count]); //Add JMenuItem on JMenu  
        }


        ///////////////////////////////////////

        fileMenu3 = new JMenu("Text Colors");
        fileMenu3.setForeground(setFontVar());
        menuItems3 = new JMenuItem[items3.length];

        fileMenu3.setMnemonic('T'); //Add mnemonic on the JMenu "Text Colors"  
        menuBar.add(fileMenu3); //Adding JMenu on JMenuBar  
        //Constructing JMenuItem using "for loop"  
        for (int count = 0; count < menuItems3.length; count++) {
            menuItems3[count] = new JMenuItem(items3[count]); //Constructing JMenuItem with the Specified String menus 
            menuItems3[count].addActionListener(this);
            menuItems3[count].setMnemonic(itemMnemonics3[count]); //Adding mnemonics on JMenuItem  
            fileMenu3.add(menuItems3[count]); //Add JMenuItem on JMenu  
        }

        ///////////////////////////////////////
        
        ///////////////////////////////////////////

        //backgroundImage(image[0]);
        backgroundPanel = new TileBackgroundPanel(new ImageIcon(image[0]));
        backgroundPanel.setTileImage(false);
        setContentPane(backgroundPanel);
        getContentPane().setLayout(new BorderLayout());

        mySoundList = new SoundList(sounds.length, sounds);
        /*
         * //////////////////////////////////////////////////////////////////////////////////////////////////////
         * Create the north panel
         * ///////////////////////////////////////////////^///////////////////////////////////////////////////////
         */
        northPnl = new JPanel(new BorderLayout());    // Create a title panel to store the heading

        bannerPnl = new JPanel();
        title = new JLabel("Card Game");            // Create title heading of Game
        title.setFont(g);                           // Set the font-properties for the heading
        title.setForeground(setFontVar());
        bannerPnl.add(title);
        // Set the font color for the heading        
        northPnl.add(bannerPnl, BorderLayout.CENTER);
        northPnl.add(menuBar, BorderLayout.SOUTH);

        //Create panels and buttons to represents the human and computer players' hands
        //Create borders around the two panels with their respective labels

        westPnl = new JPanel(new GridLayout(game.getHumanHandSize(), 1));
        Font myFont = new Font("SansSerif", Font.BOLD, 13);
        
        t = BorderFactory.createTitledBorder("Player Hand");
        westPnl.setBorder(t);

        eastPnl = new JPanel(new GridLayout(game.getComputerHandSize(), 1));
        t2 = BorderFactory.createTitledBorder("Comp Hand");
        eastPnl.setBorder(t2);

        humanHandBtn = new JButton[game.getHumanHandSize()];
        computerHandBtn = new JButton[game.getComputerHandSize()];

        for (int i = 0; i < humanHandBtn.length; i++) {// Create buttons for the human player to click
            humanHandBtn[i] = new JButton();
            humanHandBtn[i].setPreferredSize(new Dimension(95, 120));  // Resize the buttons
            humanHandBtn[i].setIcon(game.getHumanHandIcon(i));         // Add a card icon to each of the buttons
            humanHandBtn[i].addActionListener(this);                   // Make every button interactive
            westPnl.add(humanHandBtn[i]);                              // Add the buttons to the respective panel

            computerHandBtn[i] = new JButton();                         // Create buttons for the computer player
            computerHandBtn[i].setPreferredSize(new Dimension(95, 120));// Resize the buttons
            computerHandBtn[i].setIcon(game.getComputerHandIcon(i));    // Add a card icon to each of the buttons
            eastPnl.add(computerHandBtn[i]);                            // Add the buttons to the respective panel
        }

        /*
         * //////////////////////////////////////////////////////////////////////////////////////////////////////
         * Create all the panels and buttons to display the user and computer's
         * card selections. Create all the panels in the center of the frame
         *////////////////////////////////////////////////////////////////////////////////////////////////////*/

        centerPnl = new JPanel(new GridLayout(2, 1)); // Create a panel in the center of the frame

        centerPnlUSelection = new JButton();   // Create interface to display selection of the human player
        centerPnlUSelection.setIcon(null);     // Make the interface blank by default
        centerPnlCSelection = new JButton();   // Create interface to display selection of the computer
        centerPnlUSelection.setIcon(null);     // Make the interface blank by default

        centerPnlTop = new JPanel(new GridLayout(1, 2));    // Create interface to hold the two interfaces
        t3 = BorderFactory.createTitledBorder("Player's selection");
        centerPnlTop.setBorder(t3);

        humanLbl = new JLabel("Player card:");                     // Create label to identify the human player's interface
        humanCardPnl = new JPanel(new BorderLayout());             // Create a panel to hold the label
        humanCardPnl.add(humanLbl, BorderLayout.NORTH);            // Add the label to the panel
        humanCardPnl.add(centerPnlUSelection, BorderLayout.CENTER);// Add the human player's interface to the panel

        computerLbl = new JLabel("Computer card:");                   // Create label to identify the computer's interface
        computerLbl.setForeground(Color.black);                        // Set the font color of the label
        computerCardPnl = new JPanel(new BorderLayout());             // Create a panel to hold the label
        computerCardPnl.add(computerLbl, BorderLayout.NORTH);         // Add the label to the panel
        computerCardPnl.add(centerPnlCSelection, BorderLayout.CENTER);// Add the computer's interface to the panel


        centerPnlTop.add(humanCardPnl);     // Add panel holding the label and interface for human player to interface panel 
        centerPnlTop.add(computerCardPnl);  // Add panel holding the label and interface for computer to interface panel 

        centerPnlBot = new JPanel(new BorderLayout());  // Create a Statistics panel
        statistics = new JTextArea(5, 5);               // Create a textarea to store the statistics
        statistics.setFont(f);                          // Set the font-color of words inside the textarea
        statistics.setEditable(false);                  // Set the text area to be un-editable
        setStatsDisplay();                              // Input statistics data into the textarea
        t4 = BorderFactory.createTitledBorder("Computer's selection");
        centerPnlTop.setBorder(t4);        
        centerPnlBot.add(statistics, BorderLayout.CENTER);                     // Add the textarea to Statistics Panel

        centerPnl.add(centerPnlTop, BorderLayout.NORTH); // Add the interface panel to the north of center panel
        centerPnl.add(centerPnlBot, BorderLayout.SOUTH); // Add the statistics panel to the south of center panel

        /*
         * //////////////////////////////////////////////////////////////////////////////////////////////////////
         * Create all the control buttons to for the user to click and play the
         * game
         *////////////////////////////////////////////////////////////////////////////////////////////////////*/

        controlBtn = new JButton[buttonLbl.length];        // Create an array of control buttons
        southPnl = new JPanel(new GridLayout(1, 6));       // Create a panel in the south of frame
        t5 = BorderFactory.createTitledBorder("Computer's selection");
        southPnl.setBorder(t5);   
        for (int i = 0; i < buttonLbl.length; i++) {
            controlBtn[i] = new JButton(buttonLbl[i]);         // Create all the control buttons

            controlBtn[i].setForeground(setFontVar());
            controlBtn[i].addActionListener(this);             // Make all the control buttons interactive
            southPnl.add(controlBtn[i]);                       // Add all the controi buttons to the south panel
        }
        controlBtn[0].setEnabled(false);                       // Disable the first button by default

        // Adds the north, south, east, west and center panels to their respective positions in the frame.
        add(northPnl, BorderLayout.NORTH);
        add(westPnl, BorderLayout.WEST);
        add(eastPnl, BorderLayout.EAST);
        add(centerPnl, BorderLayout.CENTER);
        add(southPnl, BorderLayout.SOUTH);
    }

    private void setStatsDisplay() {//Update the data in the statistics textarea
        statistics.setText(
                "Number of times Player won:" + "\t       " + game.getHumanWins()
                + "\n" + "Number of times Computer won:" + "    " + game.getComputerWins()
                + "\n" + "Your score:" + "\t \t       " + game.getScore());
    }

    /*
     * //////////////////////////////////////////////////////////////////////////////////////////////////////
     * Create all the events that will occur when buttons in the frame are
     * clicked
     *////////////////////////////////////////////////////////////////////////////////////////////////////*/
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < humanHandBtn.length; i++) {
            if (e.getSource() == humanHandBtn[i]) { // If the any of the buttons in the human hand is clicked
                mySoundList.miscSound(sounds[2]);
                game.setGuess(i);                   // Set the player's guess accordingly
                humanHandBtn[i].setEnabled(false);  // Disable the button that he just clicked
                centerPnlUSelection.setIcon(game.getHumanGuessIcon());      // Display image of selected card in the interface panel
                computerHandBtn[game.getComputerGuess()].setEnabled(false); // Blank out one card from computer's hand
                computerHandBtn[game.getComputerGuess()].setIcon(game.getComputerCardIcon()); // Reveal image of selected card in the computer's hand
                centerPnlCSelection.setIcon(game.getComputerGuessIcon());  // Display image of selected card in the interface panel                 
                game.computeResult();               // Compute the result
                game.getResult(this);                   // Retrieve the result and display it
                setStatsDisplay();                  // Update the statistics textarea
                cardsInHand--;
                if (cardsInHand == 0) {
                    game.getComments(this);
                }
                if (cardsInHand == 0 && !game.endGame()) {
                    controlBtn[0].setEnabled(true);     // If the game has not ended, the button will be disabled
                } else {
                    controlBtn[0].setEnabled(false);    // If the game has ended, the button will not be disabled
                }         // Update the number of cards left in human player's hand
                if (cardsInHand == 0 && game.endGame()) {
                    JOptionPane.showMessageDialog(// If no cards left in hand and insufficient cards to continue
                            this, // Throw an message
                            "There are insufficient cards left to play this game.\n"
                            + "The current game has ended.\n"
                            + "Please restart or exit the game.");
                }
            }
        }

        if (e.getSource() == controlBtn[0]) {   // If button is clicked, a new game is initialized. 
            controlBtn[0].setEnabled(false);
            game.nextRound();
            cardsInHand = humanHandBtn.length;
            for (int i = 0; i < humanHandBtn.length; i++) {
                humanHandBtn[i].setIcon(game.getHumanHandIcon(i));
                humanHandBtn[i].setEnabled(true);
                computerHandBtn[i].setIcon(game.getComputerHandIcon(i));
                computerHandBtn[i].setEnabled(true);
            }

        } else if (e.getSource() == controlBtn[1]) { // If button is clicked, the game is restarted. 
            game.restartGame();
            setStatsDisplay();
            controlBtn[0].setEnabled(false);
            centerPnlUSelection.setIcon(null);
            centerPnlCSelection.setIcon(null);
            cardsInHand = humanHandBtn.length;
            for (int i = 0; i < humanHandBtn.length; i++) {
                humanHandBtn[i].setIcon(game.getHumanHandIcon(i));
                humanHandBtn[i].setEnabled(true);
                computerHandBtn[i].setIcon(game.getComputerHandIcon(i));
                computerHandBtn[i].setEnabled(true);
            }
        } else if (e.getSource() == controlBtn[2]) { // If button is clicked, fetches the stats and displays it 
            game.getStats(this);
        } else if (e.getSource() == controlBtn[4]) { // If button is clicked, the game ends, and the program is closed. 
            game.endGame();
            game.getStats(this);
            JOptionPane.showMessageDialog(this, "Thank you for playing!");
            System.exit(0);
        } else if (e.getSource() == controlBtn[3]) {
            int revealCardPos = -1;
            do {
                try {
                    revealCardPos = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter the position number of the card\n"
                            + "you want to reveal (from 0 to " + (game.getComputerHandSize() - 1) + ") "
                            + "or type -1 to exit"));
                } catch (NumberFormatException z) {
                    JOptionPane.showMessageDialog(this, "Invalid/No Input!");
                }
            } while ((revealCardPos < 0 || revealCardPos > game.getComputerHandSize() - 1) && (revealCardPos != -1));

            if (revealCardPos != -1) {
                computerHandBtn[revealCardPos].setIcon(game.getComCardImage(revealCardPos));
                JOptionPane.showMessageDialog(this, "Card no. " + revealCardPos + " has been revealed!");
            }
        }

        if (e.getSource() == menuItems[0]) {
            mySoundList.stopSound(mySoundList.currentlyPlaying);
        } else if (e.getSource() == menuItems[1]) {
            mySoundList.playSound(sounds[0]);
        } else if (e.getSource() == menuItems[2]) {
            mySoundList.playSound(sounds[1]);
        }
        
        if (e.getSource() == menuItems2[0]) {
            setOpacity(true);
            backgroundImage(image[0]);
            changeTheme(Color.BLACK);
        } else if (e.getSource() == menuItems2[1]) {
            setOpacity(false);
            backgroundImage(image[1]);
            changeTheme(Color.WHITE);
        } else if (e.getSource() == menuItems2[2]) {
            setOpacity(false);
            backgroundImage(image[2]);
            changeTheme(Color.WHITE);
        } else if (e.getSource() == menuItems2[3]) {
            setOpacity(false);
            backgroundImage(image[3]);
            changeTheme(Color.CYAN);
        } else if (e.getSource() == menuItems2[4]) {
            setOpacity(false);
            backgroundImage(image[4]);
            changeTheme(Color.BLACK);
        } else if (e.getSource() == menuItems2[5]) {
            setOpacity(false);
            backgroundImage(image[5]);
            changeTheme(Color.WHITE);
        }
        
        if (e.getSource() == menuItems3[0]) {
           changeTheme(Color.getColor("BLUE"));
        } else if (e.getSource() == menuItems3[1]) {
           changeTheme(Color.RED);
        } else if (e.getSource() == menuItems3[2]) {
           changeTheme(Color.ORANGE); 
        } else if (e.getSource() == menuItems3[3]) {
           changeTheme(Color.BLUE); 
        }
    }
}