    package View;

import Model.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.net.URL;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.*;

/**
 *
 * @author Antonis Ntroumpogiannis AM 4014
 */
public class View extends JFrame {

    private ArrayList<JButton> p1TCards = new ArrayList<JButton>();
    private ArrayList<JButton> p2TCards = new ArrayList<JButton>();
    private ArrayList<Stack<JButton>> p1RailCards = new ArrayList<>();
    private ArrayList<Stack<JButton>> p2RailCards = new ArrayList<>();
    private ArrayList<JButton> p1DCards = new ArrayList<JButton>();
    private ArrayList<JButton> p2DCards = new ArrayList<JButton>();
    private JButton[] center_cards_Buttons = new JButton[5];
    private JButton p1_moveFromRail = new JButton("Move cards to On-The-Track");
    private JButton p2_moveFromRail = new JButton("Move cards to On-The-Track");
    private JButton p1_playButton = new JButton("Play Cards");
    private JButton p2_playButton = new JButton("Play Cards");
    private JButton p1_destination_tickets = new JButton("My destination tickets");
    private JButton p1_bigC_cards = new JButton("My big cities bonus cards");
    private JButton p2_destination_tickets = new JButton("My destination tickets");
    private JButton p2_bigC_cards = new JButton("My big cities bonus cards");
    private JButton trainC_deck;
    private JButton DestC_deck;
    private JButton Submit_choices = new JButton("Submit Choices");
    private ArrayList<JCheckBox> checkBoxes = new ArrayList<>();
    private ArrayList<JLabel> p1_track_cards = new ArrayList<>();
    private ArrayList<JLabel> p2_track_cards = new ArrayList<>();
    private JLayeredPane p1_deck, p2_deck, p1_rail, p2_rail, p1_track, p2_track, p1_scoreboard, p2_scoreboard, p1_tickets, p2_tickets;
    private JLayeredPaneExtension p1_area, p2_area;
    private ClassLoader cldr;
    private ArrayList<TrainCard> cards;
    private BigCitiesCard[] bigC_cards = new BigCitiesCard[6];
    private TrainCard[] center_cards = new TrainCard[5];
    private JLabel Train_deck_label, Tickets_deck_label, p1_turn_label, p2_turn_label, p1_score_label, p2_score_label;
    private JLabel bigC_labels[] = new JLabel[6];
    private ArrayList<JButton> PlayedCards = new ArrayList<>();
    private JDialog ChooseC_Window;
    private JLayeredPaneExtension basic;
    private int YesNoButton;
    private Deck mainDeck;

    /**
     * Constructor : Sets the title and the dimension of the main window, and initializes the class loader
     */
    public View() {
        cldr = this.getClass().getClassLoader();
        this.setResizable(false);
        this.setTitle("Ticket to ride");
        this.setPreferredSize(new Dimension(1500, 1000));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    /**
     * Initializes all the buttons, layeredPanes, card buttons and images of the game for both players
     * Postcondition : All the main graphics of the game has been initialized
     * @param p1 , the player 1
     * @param p2 , the player 2
     * @param mainDeck , the main deck of the game
     */
    public void init(Player p1, Player p2, Deck mainDeck) {
        this.mainDeck = mainDeck;

        JLabel jl;
        URL imageURL = cldr.getResource("resources/images/background.jpg");
        Image image = new ImageIcon(imageURL).getImage();
        image = image.getScaledInstance(1500, 1000, java.awt.Image.SCALE_SMOOTH);
        basic = new JLayeredPaneExtension(image);

        imageURL = cldr.getResource("resources/images/bg_white.png");
        image = new ImageIcon(imageURL).getImage();
        image = image.getScaledInstance(1500, 390, java.awt.Image.SCALE_SMOOTH);
        p1_area = new JLayeredPaneExtension(image);
        p2_area = new JLayeredPaneExtension(image);

        p1_rail = new JLayeredPane();
        p2_rail = new JLayeredPane();

        p1_track = new JLayeredPane();
        p2_track = new JLayeredPane();

        p1_tickets = new JLayeredPane();
        p2_tickets = new JLayeredPane();

        p1_scoreboard = new JLayeredPane();
        p2_scoreboard = new JLayeredPane();

        p1_deck = new JLayeredPane();
        p2_deck = new JLayeredPane();

        p1_area.setBounds(0, 560, 1450, 390);
        p2_area.setBounds(0, 20, 1450, 390);

        p1_deck.setBounds(20, 220, 750, 150);
        p2_deck.setBounds(20, 20, 750, 150);

        p1_rail.setBounds(20, 10, 750, 200);
        p2_rail.setBounds(20, 180, 750, 200);

        p1_tickets.setBounds(790, 220, 400, 150);
        p2_tickets.setBounds(790, 20, 400, 150);

        p1_track.setBounds(790, 10, 400, 200);
        p2_track.setBounds(790, 180, 400, 200);

        p1_scoreboard.setBounds(1200, 10, 220, 360);
        p2_scoreboard.setBounds(1200, 20, 220, 360);

        p1_deck.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        p2_deck.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));

        p1_rail.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        p2_rail.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));

        p1_tickets.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        p2_tickets.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));

        p1_track.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        p2_track.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));

        p1_scoreboard.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        p2_scoreboard.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));

        p1_area.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GREEN));
        p2_area.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED));

        Stack<JButton> newStack;
        for (int i = 0; i < 8; i++) {
            newStack = new Stack<>();
            p1RailCards.add(newStack);
            newStack = new Stack<>();
            p2RailCards.add(newStack);
        }

        for (int i = 0; i < 7; i++) {
            JButton card1 = new JButton();
            cards = p1.get_train_cards();
            imageURL = cldr.getResource(cards.get(i).get_image());
            image = new ImageIcon(imageURL).getImage();
            image = image.getScaledInstance(80, 110, java.awt.Image.SCALE_SMOOTH);
            card1.setIcon(new ImageIcon(image));
            card1.setName(cards.get(i).get_color().toString());
            p1TCards.add(card1);
        }

        for (int i = 0; i < 7; i++) {
            p1TCards.get(i).setBounds(50 + (50 * i), 20, 80, 110);
            p1_deck.add(p1TCards.get(i));
        }

        for (int i = 0; i < 7; i++) {
            JButton card1 = new JButton();
            cards = p2.get_train_cards();
            imageURL = cldr.getResource(cards.get(i).get_image());
            image = new ImageIcon(imageURL).getImage();
            image = image.getScaledInstance(80, 110, java.awt.Image.SCALE_SMOOTH);
            card1.setIcon(new ImageIcon(image));
            card1.setName(cards.get(i).get_color().toString());
            p2TCards.add(card1);
        }

        for (int i = 0; i < 7; i++) {
            p2TCards.get(i).setBounds(50 + (50 * i), 20, 80, 110);
            p2_deck.add(p2TCards.get(i));
        }

        bigC_cards = mainDeck.get_bigC_cards();

        for (int i = 0; i < 6; i++) {
            jl = new JLabel();
            imageURL = cldr.getResource(bigC_cards[i].get_image());
            image = new ImageIcon(imageURL).getImage();
            image = image.getScaledInstance(60, 85, java.awt.Image.SCALE_SMOOTH);
            jl.setBounds(780 + (70 * i), 450, 60, 85);
            jl.setIcon(new ImageIcon(image));
            basic.add(jl);
            bigC_labels[i] = jl;
        }

        jl = new JLabel("Available Big Cities Bonus cards");
        jl.setBounds(840, 395, 500, 300);
        jl.setFont(new Font("Serif", Font.BOLD, 21));
        basic.add(jl);

        trainC_deck = new JButton();
        trainC_deck.setName("TDeck");
        imageURL = cldr.getResource("resources/images/trainCards/trainBackCard.jpg");
        image = new ImageIcon(imageURL).getImage();
        image = image.getScaledInstance(80, 110, java.awt.Image.SCALE_SMOOTH);
        trainC_deck.setIcon(new ImageIcon(image));
        trainC_deck.setBounds(30, 420, 80, 110);
        basic.add(trainC_deck);

        Train_deck_label = new JLabel("Train Deck :");
        Train_deck_label.setBounds(20, 395, 500, 300);
        Train_deck_label.setFont(new Font("Serif", Font.BOLD, 16));
        basic.add(Train_deck_label);

        DestC_deck = new JButton();
        DestC_deck.setName("DDeck");
        imageURL = cldr.getResource("resources/images/destination_Tickets/desBackCard.jpg");
        image = new ImageIcon(imageURL).getImage();
        image = image.getScaledInstance(80, 110, java.awt.Image.SCALE_SMOOTH);
        DestC_deck.setIcon(new ImageIcon(image));
        DestC_deck.setBounds(150, 420, 80, 110);
        basic.add(DestC_deck);

        Tickets_deck_label = new JLabel("Destination Deck :");
        Tickets_deck_label.setBounds(140, 395, 500, 300);
        Tickets_deck_label.setFont(new Font("Serif", Font.BOLD, 16));
        basic.add(Tickets_deck_label);

        center_cards = mainDeck.get_center_cards();
        for (int i = 0; i < 5; i++) {
            JButton tmp = new JButton();
            imageURL = cldr.getResource(center_cards[i].get_image());
            image = new ImageIcon(imageURL).getImage();
            image = image.getScaledInstance(80, 110, java.awt.Image.SCALE_SMOOTH);
            tmp.setIcon(new ImageIcon(image));
            tmp.setName(center_cards[i].get_color().toString());
            center_cards_Buttons[i] = tmp;
        }

        for (int i = 0; i < 5; i++) {
            center_cards_Buttons[i].setBounds(300 + (85 * i), 420, 80, 110);
            basic.add(center_cards_Buttons[i]);
        }

        jl = new JLabel("Cards on Hand");
        jl.setBounds(20, 120, 150, 35);
        jl.setFont(new Font("Serif", Font.BOLD, 16));
        p1_deck.add(jl);

        jl = new JLabel("Cards on Hand");
        jl.setBounds(20, 120, 150, 35);
        jl.setFont(new Font("Serif", Font.BOLD, 16));
        p2_deck.add(jl);

        jl = new JLabel("Rail-Yard");
        jl.setBounds(20, 170, 150, 35);
        jl.setFont(new Font("Serif", Font.BOLD, 16));
        p1_rail.add(jl);

        jl = new JLabel("Rail-Yard");
        jl.setBounds(20, 170, 150, 35);
        jl.setFont(new Font("Serif", Font.BOLD, 16));
        p2_rail.add(jl);

        jl = new JLabel("On-The-Track");
        jl.setBounds(20, 170, 150, 35);
        jl.setFont(new Font("Serif", Font.BOLD, 16));
        p1_track.add(jl);

        jl = new JLabel("On-The-Track");
        jl.setBounds(20, 170, 150, 35);
        jl.setFont(new Font("Serif", Font.BOLD, 16));
        p2_track.add(jl);

        jl = new JLabel("Destination Tickets on Hand");
        jl.setBounds(20, 120, 250, 35);
        jl.setFont(new Font("Serif", Font.BOLD, 16));
        p1_tickets.add(jl);

        jl = new JLabel("Destination Tickets on Hand");
        jl.setBounds(20, 120, 250, 35);
        jl.setFont(new Font("Serif", Font.BOLD, 16));
        p2_tickets.add(jl);

        jl = new JLabel("      Scoreboard Player 2");
        jl.setBounds(10, 10, 250, 35);
        jl.setFont(new Font("Serif", Font.BOLD, 16));
        p2_scoreboard.add(jl);

        p2_turn_label = new JLabel("Player Τurn : ");
        p2_turn_label.setBounds(50, 40, 150, 35);
        p2_turn_label.setFont(new Font("Serif", Font.BOLD, 16));
        p2_scoreboard.add(p2_turn_label);

        p2_score_label = new JLabel("Score : 0");
        p2_score_label.setBounds(50, 70, 150, 35);
        p2_score_label.setFont(new Font("Serif", Font.BOLD, 16));
        p2_scoreboard.add(p2_score_label);

        jl = new JLabel("      Scoreboard Player 1");
        jl.setBounds(10, 10, 250, 35);
        jl.setFont(new Font("Serif", Font.BOLD, 16));
        p1_scoreboard.add(jl);

        p1_turn_label = new JLabel("Player Τurn : ");
        p1_turn_label.setBounds(50, 40, 150, 35);
        p1_turn_label.setFont(new Font("Serif", Font.BOLD, 16));
        p1_scoreboard.add(p1_turn_label);

        p1_score_label = new JLabel("Score : 0");
        p1_score_label.setBounds(50, 70, 150, 35);
        p1_score_label.setFont(new Font("Serif", Font.BOLD, 16));
        p1_scoreboard.add(p1_score_label);

        jl = new JLabel("  red               black               blue               green   "
                + "          purple            white           yellow           orange");
        jl.setBounds(5, 1, 750, 35);
        jl.setFont(new Font("Serif", Font.BOLD, 16));
        p1_rail.add(jl);

        jl = new JLabel("  red               black               blue               green   "
                + "          purple            white           yellow           orange");
        jl.setBounds(5, 1, 750, 35);
        jl.setFont(new Font("Serif", Font.BOLD, 16));
        p2_rail.add(jl);

        jl = new JLabel("red: 0");
        jl.setBounds(10, 5, 70, 20);
        jl.setFont(new Font("Serif", Font.BOLD, 16));
        p1_track_cards.add(jl);
        p1_track.add(jl);

        jl = new JLabel("black: 0");
        jl.setBounds(80, 5, 70, 20);
        jl.setFont(new Font("Serif", Font.BOLD, 16));
        p1_track_cards.add(jl);
        p1_track.add(jl);

        jl = new JLabel("blue: 0");
        jl.setBounds(150, 5, 70, 20);
        jl.setFont(new Font("Serif", Font.BOLD, 16));
        p1_track_cards.add(jl);
        p1_track.add(jl);

        jl = new JLabel("green: 0");
        jl.setBounds(220, 5, 70, 20);
        jl.setFont(new Font("Serif", Font.BOLD, 16));
        p1_track_cards.add(jl);
        p1_track.add(jl);

        jl = new JLabel("purple: 0");
        jl.setBounds(10, 80, 70, 20);
        jl.setFont(new Font("Serif", Font.BOLD, 16));
        p1_track_cards.add(jl);
        p1_track.add(jl);

        jl = new JLabel("white: 0");
        jl.setBounds(80, 80, 70, 20);
        jl.setFont(new Font("Serif", Font.BOLD, 16));
        p1_track_cards.add(jl);
        p1_track.add(jl);

        jl = new JLabel("yellow: 0");
        jl.setBounds(150, 80, 70, 20);
        jl.setFont(new Font("Serif", Font.BOLD, 16));
        p1_track_cards.add(jl);
        p1_track.add(jl);

        jl = new JLabel("orange: 0");
        jl.setBounds(220, 80, 90, 20);
        jl.setFont(new Font("Serif", Font.BOLD, 16));
        p1_track_cards.add(jl);
        p1_track.add(jl);

        jl = new JLabel("Locomotive: 0");
        jl.setBounds(290, 55, 100, 20);
        jl.setFont(new Font("Serif", Font.BOLD, 16));
        p1_track_cards.add(jl);
        p1_track.add(jl);

        String[] Rimages = {"resources/images/trainCards/red.jpg", "resources/images/trainCards/black.jpg", "resources/images/trainCards/blue.jpg", "resources/images/trainCards/green.jpg",
            "resources/images/trainCards/purple.jpg", "resources/images/trainCards/white.jpg", "resources/images/trainCards/yellow.jpg", "resources/images/trainCards/orange.jpg", "resources/images/trainCards/locomotive.jpg"};
        int j = 0;
        int width = 12;
        for (int i = 0; i < 8; i++) {
            jl = new JLabel();
            imageURL = cldr.getResource(Rimages[i]);
            image = new ImageIcon(imageURL).getImage();
            image = image.getScaledInstance(40, 60, java.awt.Image.SCALE_SMOOTH);
            jl.setBounds(10 + (j * 70), width, 60, 85);
            jl.setIcon(new ImageIcon(image));
            p1_track.add(jl);
            if (i == 3) {
                width = 86;
                j = -1;
            }
            j++;
        }
        jl = new JLabel();
        imageURL = cldr.getResource(Rimages[8]);
        image = new ImageIcon(imageURL).getImage();
        image = image.getScaledInstance(40, 60, java.awt.Image.SCALE_SMOOTH);
        jl.setBounds(310, 65, 60, 85);
        jl.setIcon(new ImageIcon(image));
        p1_track.add(jl);

        jl = new JLabel("red: 0");
        jl.setBounds(10, 5, 70, 20);
        jl.setFont(new Font("Serif", Font.BOLD, 16));
        p2_track_cards.add(jl);
        p2_track.add(jl);

        jl = new JLabel("black: 0");
        jl.setBounds(80, 5, 70, 20);
        jl.setFont(new Font("Serif", Font.BOLD, 16));
        p2_track_cards.add(jl);
        p2_track.add(jl);

        jl = new JLabel("blue: 0");
        jl.setBounds(150, 5, 70, 20);
        jl.setFont(new Font("Serif", Font.BOLD, 16));
        p2_track_cards.add(jl);
        p2_track.add(jl);

        jl = new JLabel("green: 0");
        jl.setBounds(220, 5, 70, 20);
        jl.setFont(new Font("Serif", Font.BOLD, 16));
        p2_track_cards.add(jl);
        p2_track.add(jl);

        jl = new JLabel("purple: 0");
        jl.setBounds(10, 80, 70, 20);
        jl.setFont(new Font("Serif", Font.BOLD, 16));
        p2_track_cards.add(jl);
        p2_track.add(jl);

        jl = new JLabel("white: 0");
        jl.setBounds(80, 80, 70, 20);
        jl.setFont(new Font("Serif", Font.BOLD, 16));
        p2_track_cards.add(jl);
        p2_track.add(jl);

        jl = new JLabel("yellow: 0");
        jl.setBounds(150, 80, 70, 20);
        jl.setFont(new Font("Serif", Font.BOLD, 16));
        p2_track_cards.add(jl);
        p2_track.add(jl);

        jl = new JLabel("orange: 0");
        jl.setBounds(220, 80, 90, 20);
        jl.setFont(new Font("Serif", Font.BOLD, 16));
        p2_track_cards.add(jl);
        p2_track.add(jl);

        jl = new JLabel("Locomotive: 0");
        jl.setBounds(290, 55, 100, 20);
        jl.setFont(new Font("Serif", Font.BOLD, 16));
        p2_track_cards.add(jl);
        p2_track.add(jl);

        j = 0;
        width = 12;
        for (int i = 0; i < 8; i++) {
            jl = new JLabel();
            imageURL = cldr.getResource(Rimages[i]);
            image = new ImageIcon(imageURL).getImage();
            image = image.getScaledInstance(40, 60, java.awt.Image.SCALE_SMOOTH);
            jl.setBounds(10 + (j * 70), width, 60, 85);
            jl.setIcon(new ImageIcon(image));
            p2_track.add(jl);
            if (i == 3) {
                width = 86;
                j = -1;
            }
            j++;
        }
        jl = new JLabel();
        imageURL = cldr.getResource(Rimages[8]);
        image = new ImageIcon(imageURL).getImage();
        image = image.getScaledInstance(40, 60, java.awt.Image.SCALE_SMOOTH);
        jl.setBounds(310, 65, 60, 85);
        jl.setIcon(new ImageIcon(image));
        p2_track.add(jl);

        p1_moveFromRail.setBounds(520, 170, 200, 20);
        p1_moveFromRail.setName("p1");
        p2_moveFromRail.setBounds(520, 170, 200, 20);
        p2_moveFromRail.setName("p2");

        p1_playButton.setBounds(630, 120, 100, 20);
        p1_playButton.setName("p1");
        p2_playButton.setBounds(630, 120, 100, 20);
        p2_playButton.setName("p2");

        p1_destination_tickets.setBounds(10, 150, 200, 80);
        p1_destination_tickets.setName("p1");
        p2_destination_tickets.setBounds(10, 150, 200, 80);
        p2_destination_tickets.setName("p2");

        p1_bigC_cards.setBounds(10, 250, 200, 80);
        p1_bigC_cards.setName("p1");
        p2_bigC_cards.setBounds(10, 250, 200, 80);
        p2_bigC_cards.setName("p2");

        p1_scoreboard.add(p1_destination_tickets);
        p2_scoreboard.add(p2_destination_tickets);
        p1_scoreboard.add(p1_bigC_cards);
        p2_scoreboard.add(p2_bigC_cards);
        p1_rail.add(p1_moveFromRail);
        p2_rail.add(p2_moveFromRail);
        p1_deck.add(p1_playButton);
        p2_deck.add(p2_playButton);
        p1_area.add(p1_deck);
        p2_area.add(p2_deck);
        p1_area.add(p1_rail);
        p2_area.add(p2_rail);
        p1_area.add(p1_track);
        p2_area.add(p2_track);
        p1_area.add(p1_scoreboard);
        p2_area.add(p2_scoreboard);
        p1_area.add(p1_tickets);
        p2_area.add(p2_tickets);
        basic.add(p1_area);
        basic.add(p2_area);
        this.add(basic);
        pack();
    }

    /**
     * Message for showing the winner of the game
     * @param message , the message for the winner
     */
    public void showWinningMessage(String message) {
        JOptionPane.showMessageDialog(this, message);

    }

    /**
     * Message for showing the invalid move of the player
     * @param message , the message for the invalid move
     */
    public void showInvalidMoveMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Update the graphics for the tickets on players hand
     * Postcondition : The tickets on player's hand has been updated
     * @param p , the player whose tickets will be updated
     */
    public void UpdateTicketsOnHand(Player p) {
        JLayeredPane Tpane = null;
        ArrayList<JButton> buts;
        if (p.toString().equals("p1")) {
            Tpane = p1_tickets;
            buts = p1DCards;
        } else {
            buts = p2DCards;
            Tpane = p2_tickets;
        }
        Tpane.removeAll();
        JLabel jl;
        jl = new JLabel("Destination Tickets on Hand");
        jl.setBounds(20, 120, 250, 35);
        jl.setFont(new Font("Serif", Font.BOLD, 16));
        Tpane.add(jl);
        for (int i = 0; i < buts.size(); i++) {
            buts.get(i).setBounds(20 + 20 * i, 18, 80, 110);
            Tpane.add(buts.get(i));
        }
        Tpane.repaint();

    }

    /**
     * The window that shows the player which destination cards he can draw
     * @param p , the player that draws the tickets
     * @param numOfCardsToChoose , the number of cards that the player will have to choose among
     * @param main_deck , the main deck of the game
     */
    public void ChooseDestinationCardsWindow(Player p, int numOfCardsToChoose, Deck main_deck)  {
        
        ChooseC_Window = new JDialog();
        ArrayList<DestinationCard> destC = new ArrayList<>();
        Stack<DestinationCard> tmp = new Stack<>();
        checkBoxes.clear();

        for (int i = 0; i < numOfCardsToChoose; i++) {
            destC.add(main_deck.get_dest_cards().peek());
            tmp.push(main_deck.get_dest_cards().pop());
        }
        for (int i = 0; i < numOfCardsToChoose; i++) {
            main_deck.get_dest_cards().push(tmp.pop());
        }

        JLabel tmp_jl;
        JCheckBox jb;
        URL imageURL;
        JLayeredPane pane = new JLayeredPane();
        pane.setBounds(0, 0, 150 * destC.size(), 300);
        Image image;
        for (int i = 0; i < destC.size(); i++) {
            tmp_jl = new JLabel();
            imageURL = cldr.getResource("resources/images/destination_Tickets/" + destC.get(i).get_image());
            image = new ImageIcon(imageURL).getImage();
            image = image.getScaledInstance(120, 160, java.awt.Image.SCALE_SMOOTH);
            tmp_jl.setBounds(0 + (140 * i), 0, 120, 160);
            tmp_jl.setIcon(new ImageIcon(image));
            jb = new JCheckBox("Keep Card");
            jb.setBounds(0 + (140 * i), 170, 100, 20);
            checkBoxes.add(jb);
            pane.add(tmp_jl);

        }
        for (int i = 0; i < checkBoxes.size(); i++) {
            pane.add(checkBoxes.get(i));

        }
        Submit_choices.setBounds(20, 210, 300, 20);
        Submit_choices.setName("Submit Choices");
        pane.add(Submit_choices);

        ChooseC_Window.add(pane);
        ChooseC_Window.setResizable(false);
        ChooseC_Window.setTitle("Choose destination Tickets");
        ChooseC_Window.setPreferredSize(new Dimension(150 * destC.size(), 300));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ChooseC_Window.pack();
        if(this.isVisible()) ChooseC_Window.setVisible(true);
        ChooseC_Window.setLocationRelativeTo(basic);
        this.setEnabled(false);

    }

    /**
     * This function is responsible for checking which checkboxes are selected after pressing the submit buttons
     * @param p , the player that draws the tickets
     * @param numOfCardsToChoose , the number of cards that the player will have to choose among
     * @param main_deck , the main deck
     * @return true if the submit was valid according to the rule, false otherwise
     */
    public boolean ChooseDestinationCards(Player p, int numOfCardsToChoose, Deck main_deck) {
        int size = 4;
        if (numOfCardsToChoose == 6) {
            size = 6;
            boolean chk = false;
            for (int i = 0; i < checkBoxes.size(); i++) {
                if (checkBoxes.get(i).isSelected()) {
                    chk = true;
                }
            }
            if (!chk) {
                showInvalidMoveMessage("Please select at least on tickets");
                return false;
            }

        }
        ArrayList<JButton> buts;
        JLayeredPane pane;
        if (p.toString().equals("p1")) {
            buts = p1DCards;
            pane = p1_tickets;
        } else {
            buts = p2DCards;
            pane = p2_tickets;
        }

        ArrayList<DestinationCard> choices = new ArrayList<>();
        for (int i = 0; i < numOfCardsToChoose; i++) {
            choices.add(main_deck.get_dest_cards().pop());
        }
        JButton tmp;
        URL imageURL;
        Image image;
        for (int i = 0; i < size; i++) {
            if (checkBoxes.get(i).isSelected()) {
                p.get_dest_cards().add(choices.get(i));
                tmp = new JButton();
                imageURL = cldr.getResource("resources/images/destination_Tickets/" + choices.get(i).get_image());
                image = new ImageIcon(imageURL).getImage();
                image = image.getScaledInstance(80, 110, java.awt.Image.SCALE_SMOOTH);
                tmp.setIcon(new ImageIcon(image));
                buts.add(tmp);
                pane.add(tmp);
            } else {
                main_deck.get_dest_cards().push(choices.get(i));
            }
        }
        checkBoxes.clear();
        UpdateTicketsOnHand(p);
        ChooseC_Window.dispose();
        this.setEnabled(true);
        this.toFront();
        return true;
    }

    /**
     * This function creates a window , that shows the tickets that the player has already bought
     * Postcondition : A window that shows the tickets bought has been created
     * @param p , the player
     */
    public void ShowTicketsBought(Player p) {
        JDialog Jd = new JDialog();
        ArrayList<DestinationCard> destC = new ArrayList<>();

        destC = p.get_dest_cards_bought();
        JLabel tmp_jl;
        URL imageURL;
        JLayeredPane pane = new JLayeredPane();
        pane.setBounds(0, 0, 120 * destC.size(), 200);
        Image image;
        for (int i = 0; i < destC.size(); i++) {
            tmp_jl = new JLabel();
            imageURL = cldr.getResource("resources/images/destination_Tickets/" + destC.get(i).get_image());
            image = new ImageIcon(imageURL).getImage();
            image = image.getScaledInstance(120, 160, java.awt.Image.SCALE_SMOOTH);
            tmp_jl.setBounds(0 + (140 * i), 0, 120, 160);
            tmp_jl.setIcon(new ImageIcon(image));
            pane.add(tmp_jl);

        }

        Jd.add(pane);

        Jd.setResizable(false);
        Jd.setTitle("Destination Tickets Bought");
        Jd.setPreferredSize(new Dimension(140 * destC.size(), 200));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Jd.pack();
        Jd.setVisible(true);
        Jd.setLocationRelativeTo(basic);
    }

    /**
     * This function creates a window , that shows the big cities bonus cards that the player has
     * Postcondition : A window that shows the big cities bonus cards has been created
     * @param p , the player
     */
    public void BigCitiesCards(Player p) {
        JDialog Jd = new JDialog();
        ArrayList<BigCitiesCard> destC = new ArrayList<>();

        destC = p.get_bigC_cards();
        JLabel tmp_jl;
        URL imageURL;
        JLayeredPane pane = new JLayeredPane();
        pane.setBounds(0, 0, 120 * destC.size(), 200);
        Image image;
        for (int i = 0; i < destC.size(); i++) {
            tmp_jl = new JLabel();
            imageURL = cldr.getResource(destC.get(i).get_image());
            image = new ImageIcon(imageURL).getImage();
            image = image.getScaledInstance(120, 160, java.awt.Image.SCALE_SMOOTH);
            tmp_jl.setBounds(0 + (140 * i), 0, 120, 160);
            tmp_jl.setIcon(new ImageIcon(image));
            pane.add(tmp_jl);

        }

        Jd.add(pane);

        Jd.setResizable(false);
        Jd.setTitle("Big Cities Bonus Cards");
        Jd.setPreferredSize(new Dimension(140 * destC.size(), 200));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Jd.pack();
        Jd.setVisible(true);
        Jd.setLocationRelativeTo(basic);
    }

    /**
     * This function creates a confirmation window, that asks the player if he wants to buy the specific tickets or not
     * Postcondition : A confirmation window has been created
     * @param c , the destination card that ought to be bought
     */
    public void BuyCardWindow(DestinationCard c) {
        URL imageURL = cldr.getResource("resources/images/destination_Tickets/" + c.get_image());
        Image image = new ImageIcon(imageURL).getImage();
        image = image.getScaledInstance(120, 160, java.awt.Image.SCALE_SMOOTH);

        YesNoButton = JOptionPane.showConfirmDialog(this, "Are you sure you want to buy it?", "Buy card", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(image));
    }

    /**
     * This function moves the cards that the player wants to play in the player's rail-yard, and deletes them from the player's hand
     * Postcondition : The cards that are played, has been move to the rail-yard
     * @param p , the player
     * @param color , the cards are different color, color is set to null, else color is set to the color of the cards
     */
    public void MoveToRail(Player p, String color) {
        int x = 10, y = 20;
        ArrayList<Stack<JButton>> RailCards;
        JLayeredPane rail;
        ArrayList<JButton> TCards;
        ArrayList<JButton> Locos = new ArrayList<>();
        
        if (p.toString().equals("p1")) {
            RailCards = p1RailCards;
            rail = p1_rail;
            TCards = p1TCards;
        } else {
            RailCards = p2RailCards;
            rail = p2_rail;
            TCards = p2TCards;
        }

        for (int i = 0; i < PlayedCards.size(); i++) {
            switch (PlayedCards.get(i).getName()) {
                case "RED":
                    x = 5;
                    RailCards.get(0).push(PlayedCards.get(i));
                    y = 25 + 10 * RailCards.get(0).size();
                    break;
                case "BLACK":
                    x = 90;
                    RailCards.get(1).push(PlayedCards.get(i));
                    y = 25 + 10 * RailCards.get(1).size();
                    break;
                case "BLUE":
                    x = 175;
                    RailCards.get(2).push(PlayedCards.get(i));
                    y = 25 + 10 * RailCards.get(2).size();
                    break;
                case "GREEN":
                    x = 260;
                    RailCards.get(3).push(PlayedCards.get(i));
                    y = 25 + 10 * RailCards.get(3).size();
                    break;
                case "PURPLE":
                    x = 345;
                    RailCards.get(4).push(PlayedCards.get(i));
                    y = 25 + 10 * RailCards.get(4).size();
                    break;
                case "WHITE":
                    x = 430;
                    RailCards.get(5).push(PlayedCards.get(i));
                    y = 25 + 10 * RailCards.get(5).size();
                    break;
                case "YELLOW":
                    x = 515;
                    RailCards.get(6).push(PlayedCards.get(i));
                    y = 25 + 10 * RailCards.get(6).size();
                    break;
                case "ORANGE":
                    x = 600;
                    RailCards.get(7).push(PlayedCards.get(i));
                    y = 25 + 10 * RailCards.get(7).size();
                    break;
                case "LOCOMOTIVE":
                    Locos.add(PlayedCards.get(i));
                default:
                    break;
            }
            TCards.remove(PlayedCards.get(i));
            PlayedCards.get(i).setBounds(x, y, 80, 110);
            rail.add(PlayedCards.get(i));
        }
        
        if(color == null){
            ReArrangeCardsOnHand(p);
            return;
        }
        
        for (int i = 0; i < Locos.size(); i++) {
            switch (color) {
                case "RED":
                    x = 5;
                    RailCards.get(0).push(Locos.get(i));
                    y = 25 + 10 * RailCards.get(0).size();
                    break;
                case "BLACK":
                    x = 90;
                    RailCards.get(1).push(Locos.get(i));
                    y = 25 + 10 * RailCards.get(1).size();
                    break;
                case "BLUE":
                    x = 175;
                    RailCards.get(2).push(Locos.get(i));
                    y = 25 + 10 * RailCards.get(2).size();
                    break;
                case "GREEN":
                    x = 260;
                    RailCards.get(3).push(Locos.get(i));
                    y = 25 + 10 * RailCards.get(3).size();
                    break;
                case "PURPLE":
                    x = 345;
                    RailCards.get(4).push(Locos.get(i));
                    y = 25 + 10 * RailCards.get(4).size();
                    break;
                case "WHITE":
                    x = 430;
                    RailCards.get(5).push(Locos.get(i));
                    y = 25 + 10 * RailCards.get(5).size();
                    break;
                case "YELLOW":
                    x = 515;
                    RailCards.get(6).push(Locos.get(i));
                    y = 25 + 10 * RailCards.get(6).size();
                    break;
                case "ORANGE":
                    x = 600;
                    RailCards.get(7).push(Locos.get(i));
                    y = 25 + 10 * RailCards.get(7).size();
                    break;
                case "LOCOMOTIVE":
                    Locos.add(Locos.get(i));
                default:
                    break;
            }
            Locos.get(i).setBounds(x, y, 80, 110);
        }
        
        rail.repaint();
        ReArrangeCardsOnHand(p);

    }

    /**
     * This function deletes all the cards of the player with the specific color, after train robbing has occured
     * Postcondition , all the cards of this color has been deleted from the game
     * @param p , the player
     * @param color , the color
     */
    public void UpdateFromTrainRobbing(Player p, String color) {
        int size;
        if (p.toString().equals("p2")) {
            switch (color) {
                case "RED":
                    size = p2RailCards.get(0).size();
                    for (int i = 0; i < size; i++) {
                        p2_rail.remove(p2RailCards.get(0).pop());
                    }   break;
                case "BLACK":
                    size = p2RailCards.get(1).size();
                    for (int i = 0; i < size; i++) {
                        p2_rail.remove(p2RailCards.get(1).pop());
                    }   break;
                case "BLUE":
                    size = p2RailCards.get(2).size();
                    for (int i = 0; i < size; i++) {
                        p2_rail.remove(p2RailCards.get(2).pop());
                    }   break;
                case "GREEN":
                    size = p2RailCards.get(3).size();
                    for (int i = 0; i < p2RailCards.get(3).size(); i++) {
                        p2_rail.remove(p2RailCards.get(3).pop());
                    }   break;
                case "PURPLE":
                    size = p2RailCards.get(4).size();
                    for (int i = 0; i < p2RailCards.get(4).size(); i++) {
                        p2_rail.remove(p2RailCards.get(4).pop());
                    }   break;
                case "WHITE":
                    size = p2RailCards.get(5).size();
                    for (int i = 0; i < p2RailCards.get(5).size(); i++) {
                        p2_rail.remove(p2RailCards.get(5).pop());
                    }   break;
                case "YELLOW":
                    size = p2RailCards.get(6).size();
                    for (int i = 0; i < p2RailCards.get(6).size(); i++) {
                        p2_rail.remove(p2RailCards.get(6).pop());
                    }   break;
                case "ORANGE":
                    size = p2RailCards.get(7).size();
                    for (int i = 0; i < p2RailCards.get(7).size(); i++) {
                        p2_rail.remove(p2RailCards.get(7).pop());
                    }   break;
                default:
                    break;
            }
            p2_rail.repaint();
        } else if (p.toString().equals("p1")) {
            switch (color) {
                case "RED":
                    size = p1RailCards.get(0).size();
                    for (int i = 0; i < size; i++) {
                        p1_rail.remove(p1RailCards.get(0).pop());
                    }   break;
                case "BLACK":
                    size = p1RailCards.get(1).size();
                    for (int i = 0; i < size; i++) {
                        p1_rail.remove(p1RailCards.get(1).pop());
                    }   break;
                case "BLUE":
                    size = p1RailCards.get(2).size();
                    for (int i = 0; i < size; i++) {
                        p1_rail.remove(p1RailCards.get(2).pop());
                    }   break;
                case "GREEN":
                    size = p1RailCards.get(3).size();
                    for (int i = 0; i < p1RailCards.get(3).size(); i++) {
                        p1_rail.remove(p1RailCards.get(3).pop());
                    }   break;
                case "PURPLE":
                    size = p1RailCards.get(4).size();
                    for (int i = 0; i < p1RailCards.get(4).size(); i++) {
                        p1_rail.remove(p1RailCards.get(4).pop());
                    }   break;
                case "WHITE":
                    size = p1RailCards.get(5).size();
                    for (int i = 0; i < p1RailCards.get(5).size(); i++) {
                        p1_rail.remove(p1RailCards.get(5).pop());
                    }   break;
                case "YELLOW":
                    size = p1RailCards.get(6).size();
                    for (int i = 0; i < p1RailCards.get(6).size(); i++) {
                        p1_rail.remove(p1RailCards.get(6).pop());
                    }   break;
                case "ORANGE":
                    size = p1RailCards.get(7).size();
                    for (int i = 0; i < p1RailCards.get(7).size(); i++) {
                        p1_rail.remove(p1RailCards.get(7).pop());
                    }   break;
                default:
                    break;
            }
            p1_rail.repaint();
        }

    }

    public void ReArrangeCardsOnHand(Player p) {
        if (p.toString().equals("p1")) {
            for (int i = 0; i < p1TCards.size(); i++) {
                p1TCards.get(i).setBounds(50 + (50 * i), 20, 80, 110);
            }
            p1_deck.repaint();
        } else if (p.toString().equals("p2")) {
            for (int i = 0; i < p2TCards.size(); i++) {
                p2TCards.get(i).setBounds(50 + (50 * i), 20, 80, 110);
            }
            p2_deck.repaint();
        }
        PlayedCards.clear();
    }

    /**
     * This function is responsible to raise the card after the player pressed it
     * Postcondition : The card that the player press is higher that the rest
     * @param but , the card that the player pressed
     */
    public void ChooseCard(JButton but) {
        Rectangle rc = but.getBounds();

        if (rc.y == 20) {
            rc.y -= 10;
            PlayedCards.add(but);
        } else if (rc.y == 10) {
            rc.y += 10;
            PlayedCards.remove(but);
        }
        but.setBounds(rc.x, rc.y, 80, 110);
    }

    /**
     * This function, updates the on-the-track area of the player
     * Postcondition : The on-the-track area has been updated
     * @param p , the player
     */
    public void UpdateTrack(Player p) {
        JLayeredPane track, rail;
        ArrayList<JLabel> labels;
        ArrayList<Stack<JButton>> buts;
        if (p.toString().equals("p1")) {
            track = p1_track;
            labels = p1_track_cards;
            buts = p1RailCards;
            rail = p1_rail;
        } else {
            track = p2_track;
            labels = p2_track_cards;
            buts = p2RailCards;
            rail = p2_rail;
        }

        labels.get(0).setText("red: " + p.get_track().get_red_cards().size());
        labels.get(1).setText("black: " + p.get_track().get_black_cards().size());
        labels.get(2).setText("blue: " + p.get_track().get_blue_cards().size());
        labels.get(3).setText("green: " + p.get_track().get_green_cards().size());
        labels.get(4).setText("purple: " + p.get_track().get_purple_cards().size());
        labels.get(5).setText("white: " + p.get_track().get_white_cards().size());
        labels.get(6).setText("yellow: " + p.get_track().get_yellow_cards().size());
        labels.get(7).setText("orange: " + p.get_track().get_orange_cards().size());
        labels.get(8).setText("locomotive: " + p.get_track().get_loco_cards().size());
        track.repaint();
    }

    /**
     * This function deletes the cards that will be moved to on-the-track from the rail-yard, and updates the on-the-track
     * Postcondition : RailYard and On-the-track has been updated
     * @param p , the player
     */
    public void MoveToTrack(Player p) {
        JLayeredPane track, rail;
        ArrayList<JLabel> labels;
        ArrayList<Stack<JButton>> buts;
        if (p.toString().equals("p1")) {
            track = p1_track;
            labels = p1_track_cards;
            buts = p1RailCards;
            rail = p1_rail;
        } else {
            track = p2_track;
            labels = p2_track_cards;
            buts = p2RailCards;
            rail = p2_rail;
        }

        UpdateTrack(p);

        JButton tmp;
        for (int i = 0; i < 8; i++) {
            if (buts.get(i).size() > 0) {
                tmp = buts.get(i).pop();
                rail.remove(tmp);
            }
        }

        rail.repaint();
    }

    /**
     * This function is responsible for creating a new button for the drawn card and updating the player's hand area
     * Postcondition : A new card is drawn and appeared on player's hand
     * @param p , the player
     * @param card , the card
     */
    public void DrawTrainCard(Player p, TrainCard card) {
        ArrayList<JButton> buts;
        JLayeredPane pane;
        if (p.toString().equals("p1")) {
            buts = p1TCards;
            pane = p1_deck;
        } else {
            buts = p2TCards;
            pane = p2_deck;
        }
        URL imageURL;
        Image image;
        JButton tmp = new JButton();
        imageURL = cldr.getResource(card.get_image());
        image = new ImageIcon(imageURL).getImage();
        image = image.getScaledInstance(80, 110, java.awt.Image.SCALE_SMOOTH);
        tmp.setIcon(new ImageIcon(image));
        tmp.setName(card.get_color().toString());
        tmp.setBounds(50 + (50 * buts.size() - 1), 20, 80, 110);
        buts.add(tmp);
        pane.add(tmp);
        pane.repaint();

    }

    /**
     * This function is responsible for replacing the center card that is drawn
     * Postcondition : A new center card has been appeared after one is drawn
     * @param c , the card
     * @param index , the index of the cetner cards that needs to be replaced
     */
    public void UpdateCenterCard(TrainCard c, int index) {
        URL imageURL;
        Image image;

        JButton tmp = new JButton();
        imageURL = cldr.getResource(c.get_image());
        image = new ImageIcon(imageURL).getImage();
        image = image.getScaledInstance(80, 110, java.awt.Image.SCALE_SMOOTH);
        tmp.setIcon(new ImageIcon(image));
        tmp.setName(c.get_color().toString());
        center_cards_Buttons[index] = tmp;
        center_cards_Buttons[index].setBounds(300 + (85 * index), 420, 80, 110);
        basic.add(tmp);
        basic.repaint();

    }

    /**
     * Removes the big city image from the game
     * Postcondition : The big city image has been removed
     * @param index , the index of the image in the array
     */
    public void RemoveBigCity(int index) {
        basic.remove(bigC_labels[index]);
        basic.repaint();
    }

    /**
     * This fucntions, moves the selected center card to the playrer's hand
     * Postcondtion : The card is moved to the player's hand
     * @param but , the card
     * @param p , the player
     */
    public void MoveCenterCardOnHand(JButton but, Player p) {
        JButton butt = new JButton();
        butt.setIcon(but.getIcon());
        butt.setName(but.getName());
        if (p.toString().equals("p1")) {
            p1_deck.add(butt);
            p1TCards.add(butt);
            p1_deck.repaint();
        } else {
            p2_deck.add(butt);
            p2TCards.add(butt);
            p2_deck.repaint();
        }

        basic.remove(but);
        basic.repaint();

    }

    /**
     * This function updates the player turns graphic
     * Postcondition : Player turns graphics has been updated
     * @param p , the player
     */
    public void UpdateTurn(Player p) {
        if (p.toString().equals("p1")) {
            p1_turn_label.setText("Player Turn: YES");
            p2_turn_label.setText("Player Turn: NO");
        } else {
            p1_turn_label.setText("Player Turn: NO");
            p2_turn_label.setText("Player Turn: YES");
        }
    }
    
    /**
     * This function updates the player score graphic
     * Postcondition : Player score graphics has been updated
     * @param p , the player
     */
    public void UpdateScore(Player p) {
        if (p.toString().equals("p1")) {
            p1_score_label.setText("Score: " + p.get_score());
        } else {
            p2_score_label.setText("Score: " + p.get_score());
        }
    }

     /**
     * This function updates the trains deck size graphic
     * Postcondition : Trains deck size graphics has been updated
     */
    public void UpdateTrainDeckSize() {
        Train_deck_label.setText("Train Deck : " + mainDeck.get_trainC_size());
    }

     /**
     * This function updates the tickets deck size graphic
     * Postcondition : Tickets deck size graphics has been updated
     */
    public void UpdateDestDeckSize() {
        Tickets_deck_label.setText("Destination Deck : " + mainDeck.get_destC_size());
    }

    /**
     * Accessor : Get the p1 train cards buttons
     * Postcondition : The p1 train cards buttons has been returned
     * @return the p1 train cards buttons
     */
    public ArrayList<JButton> getP1TCards() {
        return p1TCards;
    }

    /**
     * Accessor : Get the p2 train cards buttons
     * Postcondition : The p2 train cards buttons has been returned
     * @return the p2 train cards buttons
     */
    public ArrayList<JButton> getP2TCards() {
        return p2TCards;
    }

    /**
     * Accessor : Get the p1 ticket cards buttons
     * Postcondition : The p1 ticket cards buttons has been returned
     * @return the p1 ticket cards buttons
     */
    public ArrayList<JButton> getP1DCards() {
        return p1DCards;
    }

    /**
     * Accessor : Get the p2 ticket cards buttons
     * Postcondition : The p2 ticket cards buttons has been returned
     * @return the p2 ticket cards buttons
     */
    public ArrayList<JButton> getP2DCards() {
        return p2DCards;
    }

    /**
     * Accessor : Get the list with all action buttons of the game
     * Postcondition : The action buttons of the game has been returned
     * @return the action buttons of the game
     */
    public JButton[] get_actionButtons() {
        JButton[] tmp = new JButton[9];
        tmp[0] = p1_playButton;
        tmp[1] = p1_moveFromRail;
        tmp[2] = p1_destination_tickets;
        tmp[3] = p1_bigC_cards;
        tmp[4] = p2_playButton;
        tmp[5] = p2_moveFromRail;
        tmp[6] = p2_destination_tickets;
        tmp[7] = p2_bigC_cards;
        tmp[8] = Submit_choices;
        return tmp;
    }

    /**
     * Accessor : Get the train cards deck button
     * Postcondition : The train cards deck button has been returned
     * @return the train cards deck button
     */    
    public JButton get_trainDeckButton() {
        return trainC_deck;
    }

    /**
     * Accessor : Get the ticket cards deck button
     * Postcondition : The ticket cards deck button has been returned
     * @return the ticket cards deck button
     */ 
    public JButton get_destDeckButton() {
        return DestC_deck;
    }

    /**
     * Accessor : Get the center cards buttons
     * Postcondition : The center cards buttons has been returned
     * @return the center cards buttons
     */    
    public JButton[] get_centerCButtons() {
        return center_cards_Buttons;
    }

    /**
     * Accessor : Get the yes or no button from the buyTicketsWindow
     * Postcondition : The yes or no button from the buyTicketsWindow has been returned
     * @return the yes or no button from the buyTicketsWindow
     */ 
    public int get_YesNoButton() {
        return YesNoButton;
    }

}
