package Controller;

import Model.*;
import View.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import java.util.Random;

/**
 *
 * @author Antonis Ntroumpogiannis AM 4014
 */
public class Controller {

    private final Player p1 = new Player("p1");
    private final Player p2 = new Player("p2");
    private final Deck mainDeck = new Deck();
    private final View view = new View();
    private Player PlayerTurn;
    private ArrayList<TrainCard> PlayedCards = new ArrayList<>();
    private boolean moved_on_track;
    private int drawn_cards;
    private boolean start;
    private Player FirstTurn;

    /**
     * Initializes the deck so the game can start
     */
    public void init() {
        mainDeck.initCards();
        p1.init_counters();
        p2.init_counters();
        mainDeck.moirasma(p1, p2);
        view.init(p1, p2, mainDeck);
        
        view.UpdateDestDeckSize();
        view.UpdateTrainDeckSize();
        setListeners();
        if(view.isVisible()) view.showWinningMessage("Start the game");
        SetRandomFirstTurn();
    }

    /**
     * Accesspr : Get the currently played cards
     * Postcondition : the currently played cards has been returned
     * @return , the currently played cards
     */
    public ArrayList<TrainCard> get_played_cards(){
        return PlayedCards;
    }
    
    /**
     * Accesspr : Get the current game window
     * Postcondition : the current game window has been returned
     * @return , the current game window
     */
    public View get_view(){
        return view;
    }
    /**
     * Chooses the player who has the first turn randomly
     */
    public void SetRandomFirstTurn() {
        Random rand = new Random();
        int n = rand.nextInt(2) + 1;
        if (n == 1) {
            PlayerTurn = p1;
        } else {
            PlayerTurn = p2;
        }
        FirstTurn = PlayerTurn;
        start = true;
        moved_on_track = true;
        PlayedCards.clear();
        if(view.isVisible()) view.showWinningMessage(PlayerTurn.toString() + " starts the game!");
        view.ChooseDestinationCardsWindow(PlayerTurn, 6, mainDeck);
        view.UpdateTurn(PlayerTurn);
    }

    /**
     * Move the top cards of each color stack from railyard to on-the-track
     * Postcondition : rail and track are updated
     *
     * @param p , the player that made this move
     */
    public void UpdateRailAndTrack(Player p) {

        p.get_track().collectFromRailYard(p.get_rail());
        moved_on_track = true;
    }

    /**
     * Check if the railyard already contains a color
     *
     * @param p , the player
     * @param color , the color that need to be checked
     * @return , true if it contains the color, false otherwise
     */
    public boolean RailContainsColor(Player p, CardColor color) {
        if (color.equals(CardColor.BLUE)) {
            if (p.get_rail().get_Blue_cards().size() > 0) {
                return true;
            }
        }
        if (color.equals(CardColor.BLACK)) {
            if (p.get_rail().get_Black_cards().size() > 0) {
                return true;
            }
        }
        if (color.equals(CardColor.PURPLE)) {
            if (p.get_rail().get_Purple_cards().size() > 0) {
                return true;
            }
        }
        if (color.equals(CardColor.WHITE)) {
            if (p.get_rail().get_White_cards().size() > 0) {
                return true;
            }
        }
        if (color.equals(CardColor.ORANGE)) {
            if (p.get_rail().get_Orange_cards().size() > 0) {
                return true;
            }
        }
        if (color.equals(CardColor.GREEN)) {
            if (p.get_rail().get_Green_cards().size() > 0) {
                return true;
            }
        }
        if (color.equals(CardColor.YELLOW)) {
            if (p.get_rail().get_Yellow_cards().size() > 0) {
                return true;
            }
        }
        if (color.equals(CardColor.RED)) {
            if (p.get_rail().get_Red_cards().size() > 0) {
                return true;
            }
        }

        return false;
    }

    /**
     * This function is checks if the cards that we selected can be moved to
     * railyard from the hand of the player if they can, it moves them there 
     * Postcondition : Cards are moved in the rail-yard if they can be played
     */
    public void PlayCards() {
        Player p = getTurn();
        Player opp;
        boolean NotLoco = false;
        boolean Loco = false;
        CardColor currColor;
        int chk = 1;
        CardColor color = null;

        if (p.toString().equals("p1")) {
            opp = p2;
        } else {
            opp = p1;
        }

        if (PlayedCards.size() < 2) {
            view.showInvalidMoveMessage("You must play 2 or more cards!");
            view.ReArrangeCardsOnHand(p);
            PlayedCards.clear();
            return;
        }

        currColor = PlayedCards.get(0).get_color();
        if(currColor.equals(CardColor.LOCOMOTIVE)) Loco = true;
        else NotLoco = true;
        for (int i = 1; i < PlayedCards.size(); i++) {

            if (PlayedCards.get(i).get_color().equals(CardColor.LOCOMOTIVE)) {
                Loco = true;
            } else {
                NotLoco = true;
            }

            if (!currColor.equals(PlayedCards.get(i).get_color())
                    && !currColor.equals(CardColor.LOCOMOTIVE) && !PlayedCards.get(i).get_color().equals(CardColor.LOCOMOTIVE)) {
                chk = 2;
            }

            currColor = PlayedCards.get(i).get_color();
        }

        if (chk == 2 && Loco) {
            view.showInvalidMoveMessage("You cannot play Locomotive train card!");
            view.ReArrangeCardsOnHand(p);
            PlayedCards.clear();
            return;
        } else if (!NotLoco) {
            view.showInvalidMoveMessage("You cannot play only Locomotive train cards!");
            view.ReArrangeCardsOnHand(p);
            PlayedCards.clear();
            return;
        } else if (chk == 2 && PlayedCards.size() != 3) {
            view.showInvalidMoveMessage("Yow can only play exactly 3 cards of different color!");
            view.ReArrangeCardsOnHand(p);
            PlayedCards.clear();
            return;
        } else if (chk == 2 && !Loco && PlayedCards.size() == 3) {

            boolean b = PlayedCards.get(0).get_color().equals(PlayedCards.get(1).get_color());
            if (b) {
                return;
            }
            b = PlayedCards.get(0).get_color().equals(PlayedCards.get(2).get_color());
            if (b) {
                return;
            }
            PlayedCards.get(1).get_color().equals(PlayedCards.get(2).get_color());
            if (b) {
                return;
            }
            for (int i = 0; i < 3; i++) {
                if (RailContainsColor(p, PlayedCards.get(i).get_color()) || RailContainsColor(opp, PlayedCards.get(i).get_color())) {
                    view.showInvalidMoveMessage("Card with color" + PlayedCards.get(i).get_color().toString() + " is already on rail!");
                    view.ReArrangeCardsOnHand(p);
                    PlayedCards.clear();
                    return;
                }
            }
            view.MoveToRail(p, null);
            view.ReArrangeCardsOnHand(p);

        } else if (chk == 1) {
            color = CardColor.LOCOMOTIVE;
            for (int i = 0; i < PlayedCards.size(); i++) {
                if (!PlayedCards.get(i).get_color().equals(CardColor.LOCOMOTIVE)) {
                    color = PlayedCards.get(i).get_color();
                }
            }
            if (!checkTrainRobbing(p, opp, color)) {
                view.showInvalidMoveMessage("There are more card of this color in enemy rail-yard!");
                PlayedCards.clear();
                view.ReArrangeCardsOnHand(p);
                return;
            }
            if (RailContainsColor(p,color)){
                if(view.isVisible()) view.showInvalidMoveMessage("There are cards of this color already in your rail-yard");
                PlayedCards.clear();
                view.ReArrangeCardsOnHand(p);
                return;
            }
            view.UpdateFromTrainRobbing(opp, color.toString());
            view.MoveToRail(p, color.toString());
            view.ReArrangeCardsOnHand(p);
        }
        ArrayList<TrainCard> locos = new ArrayList<>();

        for (int i = 0; i < PlayedCards.size(); i++) {
            switch (PlayedCards.get(i).get_color()) {
                case RED:
                    p.get_rail().get_Red_cards().push(PlayedCards.get(i));
                    opp.get_rail().get_Red_cards().clear();
                    break;
                case BLUE:
                    p.get_rail().get_Blue_cards().push(PlayedCards.get(i));
                    opp.get_rail().get_Blue_cards().clear();
                    break;
                case BLACK:
                    p.get_rail().get_Black_cards().push(PlayedCards.get(i));
                    opp.get_rail().get_Black_cards().clear();
                    break;
                case PURPLE:
                    p.get_rail().get_Purple_cards().push(PlayedCards.get(i));
                    opp.get_rail().get_Purple_cards().clear();
                    break;
                case GREEN:
                    p.get_rail().get_Green_cards().push(PlayedCards.get(i));
                    opp.get_rail().get_Green_cards().clear();
                    break;
                case ORANGE:
                    p.get_rail().get_Orange_cards().push(PlayedCards.get(i));
                    opp.get_rail().get_Orange_cards().clear();
                    break;
                case WHITE:
                    p.get_rail().get_White_cards().push(PlayedCards.get(i));
                    opp.get_rail().get_White_cards().clear();
                    break;
                case YELLOW:
                    p.get_rail().get_Yellow_cards().push(PlayedCards.get(i));
                    opp.get_rail().get_Yellow_cards().clear();
                    break;
                case LOCOMOTIVE:
                    locos.add(PlayedCards.get(i));
                default:
                    break;
            }
            p.get_train_cards().remove(PlayedCards.get(i));
        }
        for (int i = 0; i < locos.size(); i++) {
            switch (color) {
                case RED:
                    p.get_rail().get_Red_cards().push(locos.get(i));
                    break;
                case BLUE:
                    p.get_rail().get_Blue_cards().push(locos.get(i));
                    break;
                case BLACK:
                    p.get_rail().get_Black_cards().push(locos.get(i));
                    break;
                case PURPLE:
                    p.get_rail().get_Purple_cards().push(locos.get(i));
                    break;
                case GREEN:
                    p.get_rail().get_Green_cards().push(locos.get(i));
                    break;
                case ORANGE:
                    p.get_rail().get_Orange_cards().push(locos.get(i));
                    break;
                case WHITE:
                    p.get_rail().get_White_cards().push(locos.get(i));
                    break;
                case YELLOW:
                    p.get_rail().get_Yellow_cards().push(locos.get(i));
                    break;
                default:
                    break;
            }
        }

        setTurn(opp);
        setListeners();
    }

    /**
     * Checks if the player can buy a specific tickets, if it can it buys it
     *
     * @param c , the tickets that was requested to be bought
     * @param p , the player that wants to buy it
     * @return , true if he can buy it, false otherwise
     */
    public boolean BuyTicket(DestinationCard c, Player p) {
        int cnt = 0;
        ArrayList<TrainCard> cards = new ArrayList<>();
        for (int i = 0; i < c.get_colors().size(); i++) {
            switch (c.get_colors().get(i)) {
                case "Blue":
                    if (p.get_track().get_blue_cards().isEmpty()) {
                        cnt++;
                    } else {
                        cards.add(p.get_track().get_blue_cards().pop());
                    }
                    break;
                case "Black":
                    if (p.get_track().get_black_cards().isEmpty()) {
                        cnt++;
                    } else {
                        cards.add(p.get_track().get_black_cards().pop());
                    }
                    break;
                case "White":
                    if (p.get_track().get_white_cards().isEmpty()) {
                        cnt++;
                    } else {
                        cards.add(p.get_track().get_white_cards().pop());
                    }
                    break;
                case "Orange":
                    if (p.get_track().get_orange_cards().isEmpty()) {
                        cnt++;
                    } else {
                        cards.add(p.get_track().get_orange_cards().pop());
                    }
                    break;
                case "Purple":
                    if (p.get_track().get_purple_cards().isEmpty()) {
                        cnt++;
                    } else {
                        cards.add(p.get_track().get_purple_cards().pop());
                    }
                    break;
                case "Green":
                    if (p.get_track().get_green_cards().isEmpty()) {
                        cnt++;
                    } else {
                        cards.add(p.get_track().get_green_cards().pop());
                    }
                    break;
                case "Yellow":
                    if (p.get_track().get_yellow_cards().isEmpty()) {
                        cnt++;
                    } else {
                        cards.add(p.get_track().get_yellow_cards().pop());
                    }
                    break;
                case "Red":
                    if (p.get_track().get_red_cards().isEmpty()) {
                        cnt++;
                    } else {
                        cards.add(p.get_track().get_red_cards().pop());
                    }
                    break;
                default:
                    break;
            }
        }
        if (cnt > p.get_track().get_loco_cards().size()) {
            for (int i = 0; i < c.get_colors().size(); i++) {
                switch (c.get_colors().get(i)) {
                    case "BLUE":
                        p.get_track().get_blue_cards().push(cards.get(i));
                        break;
                    case "BLACK":
                        p.get_track().get_black_cards().push(cards.get(i));
                        break;
                    case "WHITE":
                        p.get_track().get_white_cards().push(cards.get(i));
                        break;
                    case "ORANGE":
                        p.get_track().get_orange_cards().push(cards.get(i));
                        break;
                    case "PURPLE":
                        p.get_track().get_purple_cards().push(cards.get(i));
                        break;
                    case "GREEN":
                        p.get_track().get_green_cards().push(cards.get(i));
                        break;
                    case "YELLOW":
                        p.get_track().get_yellow_cards().push(cards.get(i));
                        break;
                    case "RED":
                        p.get_track().get_red_cards().push(cards.get(i));
                        break;
                    default:
                        break;
                }
            }
            return false;
        } else {
            for (int i = 0; i < cnt; i++) {
                p.get_track().get_loco_cards().pop();
            }
        }
        addBigCityCounter(p, c.get_departure());
        addBigCityCounter(p, c.get_destination());
        CheckBigCityCards(p);
        p.get_dest_cards_bought().add(c);
        p.get_dest_cards().remove(c);
        CalculateScores();
        return true;

    }

    /**
     * This function draws a train card from the train card deck
     * Postcondition : A train card is drawn and the deck has one less card
     */
    public void DrawTrainCard() {
        Player p = getTurn();
        TrainCard c = mainDeck.draw_train_card();
        p.get_train_cards().add(c);
        view.DrawTrainCard(p, c);
        drawn_cards += 1;
        if (drawn_cards == 2) {
            if (getTurn().equals(p1)) {
                setTurn(p2);
            } else {
                setTurn(p1);
            }
        }
    }
    
    /**
     * This function draws a train card from the center of the deck
     * @param p, the player
     * @param index , the index in the array of center_cards
     */
    public void DrawCenterCard(Player p, int index){
            p.get_train_cards().add(mainDeck.get_center_cards()[index]);

            mainDeck.get_center_cards()[index] = mainDeck.draw_train_card();
            setListeners();
            drawn_cards += 1;
            if (drawn_cards == 2) {
                if (getTurn().equals(p1)) {
                    setTurn(p2);
                } else {
                    setTurn(p1);
                }
            }
    }

    /**
     * Checks of an event of train robbing is possible
     *
     * @param p , the player that wants to play the card
     * @param opp , the enemy of the player
     * @param color , the color that wants to be played
     * @return true if there is an event of trainrobbing, false otherwise
     */
    public boolean checkTrainRobbing(Player p, Player opp, CardColor color) {
        switch (color) {
            case BLUE:
                if (opp.get_rail().get_Blue_cards().size() < PlayedCards.size()) {
                    return true;
                }
                break;
            case BLACK:
                if (opp.get_rail().get_Black_cards().size() < PlayedCards.size()) {
                    return true;
                }
                break;
            case WHITE:
                if (opp.get_rail().get_White_cards().size() < PlayedCards.size()) {
                    return true;
                }
                break;
            case ORANGE:
                if (opp.get_rail().get_Orange_cards().size() < PlayedCards.size()) {
                    return true;
                }
                break;
            case GREEN:
                if (opp.get_rail().get_Green_cards().size() < PlayedCards.size()) {
                    return true;
                }
                break;
            case PURPLE:
                if (opp.get_rail().get_Purple_cards().size() < PlayedCards.size()) {
                    return true;
                }
                break;
            case YELLOW:
                if (opp.get_rail().get_Yellow_cards().size() < PlayedCards.size()) {
                    return true;
                }
                break;
            case RED:
                if (opp.get_rail().get_Red_cards().size() < PlayedCards.size()) {
                    return true;
                }
                break;
            default:
                break;
        }
        return false;
    }

    /**
     * Sets the turn of the player that must play now and initializes all the
     * necessary variables
     * Postcondition : The turn is set properly and all the necessary variables are initialized
     *
     * @param turn , the player that is his turn
     */
    public void setTurn(Player turn) {

        if (checkIfGameFinished()) {
            getWinner();
        }

        moved_on_track = false;
        if (start) {
            moved_on_track = true;
            view.ChooseDestinationCardsWindow(turn, 6, mainDeck);
        }
        PlayerTurn = turn;
        PlayedCards.clear();
        view.UpdateTurn(turn);
        drawn_cards = 0;
        setListeners();
    }

    /**
     * Returns the player that has currently turn Postcondition : the player
     * that is his turn is returned
     *
     * @return , the player that is his turn
     */
    public Player getTurn() {
        return PlayerTurn;
    }

    /**
     * Calculate the scores for both players
     * Postcondition : The scores all calculated and updated
     */
    public void CalculateScores() {
        int scoreP1 = 0, scoreP2 = 0;
        for (int i = 0; i < p1.get_dest_cards().size(); i++) {
            scoreP1 = scoreP1 - p1.get_dest_cards().get(i).get_points();
        }
        for (int i = 0; i < p1.get_dest_cards_bought().size(); i++) {
            scoreP1 += p1.get_dest_cards_bought().get(i).get_points();
        }
        for (int i = 0; i < p1.get_bigC_cards().size(); i++) {
            scoreP1 += p1.get_bigC_cards().get(i).get_points();
        }
        for (int i = 0; i < p2.get_dest_cards().size(); i++) {
            scoreP2 -= p2.get_dest_cards().get(i).get_points();
        }
        for (int i = 0; i < p2.get_dest_cards_bought().size(); i++) {
            scoreP2 += p2.get_dest_cards_bought().get(i).get_points();
        }
        for (int i = 0; i < p2.get_bigC_cards().size(); i++) {
            scoreP2 += p2.get_bigC_cards().get(i).get_points();
        }

        p1.set_score(scoreP1);
        p2.set_score(scoreP2);
    }

    /**
     * Gets the winner of the game and ends the game
     */
    public void getWinner() {
        Player winner;
        if (p1.get_dest_cards_bought().size() > p2.get_dest_cards_bought().size()) {
            winner = p1;
        } else if (p2.get_dest_cards_bought().size() > p1.get_dest_cards_bought().size()) {
            winner = p2;
        } else if (p1.get_bigC_cards().size() > p2.get_bigC_cards().size()) {
            winner = p1;
        } else if (p2.get_bigC_cards().size() > p1.get_bigC_cards().size()) {
            winner = p2;
        } else {
            winner = null;
        }

        if (winner == null) {
            view.showWinningMessage("There was a tie!!");
        } else {
            view.showWinningMessage(winner.toString() + " won the game!!");
        }
        
        System.exit(0);
    }

    /**
     * Checks if game is finished
     *
     * @return true if game is finished, false otherwise
     */
    public boolean checkIfGameFinished() {
        if (mainDeck.get_trainC_size() == 0 || p1.get_score() > 100 || p2.get_score() > 100) {
            return true;
        }

        return false;
    }

    /**
     * Check if the string reference to a big city, and if so it adds 1 to the
     * counter of this big city
     * Postcondition : 1 is added to the counter of the specific big city
     *
     * @param p , the player
     * @param city , the big city
     */
    public void addBigCityCounter(Player p, String city) {
        switch (city) {
            case "Chicago":
                p.get_counters()[0] += 1;
                break;
            case "Dallas":
                p.get_counters()[1] += 1;
                break;
            case "Los Angeles":
                p.get_counters()[2] += 1;
                break;
            case "Miami":
                p.get_counters()[3] += 1;
                break;
            case "New York":
                p.get_counters()[4] += 1;
                break;
            case "Seattle":
                p.get_counters()[5] += 1;
                break;
            default:
                break;
        }
    }

    /**
     * Check if the player gets a big city bonus card 
     * Postcondition : the player gets the big city bonus card if his eligible
     *
     * @param p , the player is his turn
     */
    public void CheckBigCityCards(Player p) {
        for (int i = 0; i < 6; i++) {
            if (p.get_counters()[i] == 3 &&  mainDeck.get_bigC_cards()[i] != null) {
                p.get_bigC_cards().add(mainDeck.get_bigC_cards()[i]);
                mainDeck.get_bigC_cards()[i] = null;
                view.RemoveBigCity(i);
            }
        }
    }

    /**
     * Sets the listeners for all the buttons of the GUI
     * Postcondition : all listeners are set
     */
    private void setListeners() {
        for (int i = 0; i < view.getP1TCards().size(); i++) {
            if (view.getP1TCards().get(i).getActionListeners().length == 0) {
                view.getP1TCards().get(i).addActionListener(new CardListener());
            }
        }
        for (int i = 0; i < view.getP2TCards().size(); i++) {
            if (view.getP2TCards().get(i).getActionListeners().length == 0) {
                view.getP2TCards().get(i).addActionListener(new CardListener());
            }
        }
        for (int i = 0; i < 9; i++) {
            if (view.get_actionButtons()[i].getActionListeners().length == 0) {
                view.get_actionButtons()[i].addActionListener(new ActionButtonsListener());
            }
        }
        if (view.get_trainDeckButton().getActionListeners().length == 0) {
            view.get_trainDeckButton().addActionListener(new DrawCardListener());
        }
        if (view.get_destDeckButton().getActionListeners().length == 0) {
            view.get_destDeckButton().addActionListener(new DrawCardListener());
        }
        for (int i = 0; i < 5; i++) {
            if (view.get_centerCButtons()[i].getActionListeners().length == 0) {
                view.get_centerCButtons()[i].addActionListener(new CenterCardsListener());
            }
        }
        for (int i = 0; i < view.getP1DCards().size(); i++) {
            if (view.getP1DCards().get(i).getActionListeners().length == 0) {
                view.getP1DCards().get(i).addActionListener(new TicketListener());
            }
        }
        for (int i = 0; i < view.getP2DCards().size(); i++) {
            if (view.getP2DCards().get(i).getActionListeners().length == 0) {
                view.getP2DCards().get(i).addActionListener(new TicketListener());
            }
        }
    }

    /**
     * The listener that is responsible for the train cards of the game
     */
    private class CardListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!moved_on_track) {
                view.showInvalidMoveMessage("Move trains to on-the-track first!");
                return;
            } else if (drawn_cards == 1) {
                view.showInvalidMoveMessage("You must draw one more card");
            }

            Player p;
            ArrayList<JButton> TCards;
            if (getTurn().equals(p1)) {
                TCards = view.getP1TCards();
                p = p1;
            } else {
                TCards = view.getP2TCards();
                p = p2;
            }
            JButton but = ((JButton) e.getSource());
            TrainCard c;
            int x = TCards.indexOf(but);
            if (x != -1) {
                c = p.get_train_cards().get(x);
                if (!PlayedCards.contains(c)) {
                    PlayedCards.add(c);
                } else {
                    PlayedCards.remove(c);
                }
                view.ChooseCard(but);
            } else {
                view.showInvalidMoveMessage("Not your turn!!");
            }
        }
    }

    /**
     * The listener that is responsible for the ticket cards of the game
     */
    private class TicketListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!moved_on_track) {
                view.showInvalidMoveMessage("Move trains to on-the-track first!");
                return;
            } else if (drawn_cards == 1) {
                view.showInvalidMoveMessage("You must draw one more card");
            }
            JButton but = ((JButton) e.getSource());
            DestinationCard c;
            ArrayList<JButton> Dcards;
            Player p;
            int x = view.getP1DCards().indexOf(but);
            if (x != -1 && getTurn().equals(p1)) {
                p = p1;
                Dcards = view.getP1DCards();
            } else {
                x = view.getP2DCards().indexOf(but);
                if (x != -1 && getTurn().equals(p2)) {
                    p = p2;
                    Dcards = view.getP2DCards();
                } else {
                    return;
                }
            }
            c = p.get_dest_cards().get(x);
            view.BuyCardWindow(c);
            if (view.get_YesNoButton() == 0) {
                if (BuyTicket(c, p)) {
                    Dcards.remove(but);
                    view.UpdateTicketsOnHand(p);
                    view.UpdateTrack(p);
                    view.UpdateScore(p);
                    if(p.equals(p1)) setTurn(p2);
                    else setTurn(p1);
                } else {
                    view.showInvalidMoveMessage("You cannot buy this card");
                }
            }
        }
    }

    /**
     * The listener that is responsible for the center cards of the game
     */
    private class CenterCardsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int index = 0;
            JButton but = ((JButton) e.getSource());
            for (int i = 0; i < 5; i++) {
                if (but.equals(view.get_centerCButtons()[i])) {
                    index = i;
                }
            }
            Player p = getTurn();
            DrawCenterCard(p, index);
            view.UpdateCenterCard(mainDeck.get_center_cards()[index], index);
            view.MoveCenterCardOnHand(but, p);
            view.ReArrangeCardsOnHand(p);
            setListeners();
        }
    }

    /**
     * The listener that is responsible for the all the buttons of the game
     */
    private class ActionButtonsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton but = ((JButton) e.getSource());
            if (but.getName().equals(getTurn().toString())) {
                if (but.getText().equals("Move cards to On-The-Track") && !moved_on_track) {
                    UpdateRailAndTrack(getTurn());
                    view.MoveToTrack(getTurn());
                } else if (but.getText().equals("Play Cards") && moved_on_track) {
                    PlayCards();
                } else if (but.getText().equals("My destination tickets") && moved_on_track) {
                    view.ShowTicketsBought(getTurn());
                } else if (but.getText().equals("My big cities bonus cards") && moved_on_track) {
                    view.BigCitiesCards(getTurn());
                } else if (moved_on_track) {
                    view.showInvalidMoveMessage("You already moved trains to on-the-track!");
                } else {
                    view.showInvalidMoveMessage("Move trains to on-the-track first!");
                }

            } else if (but.getText().equals("Submit Choices")) {
                int numOfCards;
                if (start) {
                    numOfCards = 6;
                } else {
                    numOfCards = 4;
                }
                if (view.ChooseDestinationCards(getTurn(), numOfCards, mainDeck)) {
                    if (start) {
                        if (!FirstTurn.equals(getTurn())) {
                            start = false;
                        }
                    } else {
                        if (getTurn().equals(p1)) {
                            setTurn(p2);
                        } else {
                            setTurn(p1);
                        }
                    }
                }
                CalculateScores();
                view.UpdateScore(p1);
                view.UpdateScore(p2);
                setListeners();
                view.UpdateDestDeckSize();
            } else {
                view.showInvalidMoveMessage("Not your turn!");
            }

        }

    }

    /**
     * The listener that is responsible for the decks of the game
     */
    private class DrawCardListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!moved_on_track) {
                view.showInvalidMoveMessage("Move trains to on-the-track first!");
                return;
            }
            JButton but = ((JButton) e.getSource());
            if (but.getName().equals("DDeck")) {
                view.ChooseDestinationCardsWindow(getTurn(), 4, mainDeck);
            } else {
                DrawTrainCard();
                view.UpdateTrainDeckSize();
                if (checkIfGameFinished()) {
                    getWinner();
                }
                setListeners();
            }
        }

    }

    public static void main(String args[]) {
        Controller c = new Controller();
        c.get_view().setVisible(true);
        c.init();
        
    }
}
