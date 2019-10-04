/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Controller.Controller;
import Model.*;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Antonis Ntroumpogiannis AM 4014
 */
public class ProjectTests {
    
    public ProjectTests() {
    }
    
    @Before
    public void setUp() {
        Controller cntrl = new Controller();
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

    @Test
    public void InitCardsTest(){
        Deck mainD = new Deck();
        Player p1 = new Player("p1");
        Player p2 = new Player("p2");
        mainD.initCards();
        assertEquals("Big city Cards must be 6",6,mainD.get_bigC_cards().length);
        assertEquals("Destination cards on deck must be 46",46,mainD.get_destC_size());
        assertEquals("Train Cards on deck must be 94",94,mainD.get_trainC_size());
        
        mainD.moirasma(p1, p2);
        
        assertEquals("Train Cards of the player must be 7",7,p1.get_train_cards().size());
        assertEquals("Train Cards of the player must be 7",7,p2.get_train_cards().size());
        
        assertEquals("Train Cards on deck must be 75",75,mainD.get_trainC_size());
        
    }
    
    @Test
    public void DrawCardsTest(){
        Player p;
        Controller cntrl = new Controller();
        cntrl.init();
        
        p = cntrl.getTurn();
        cntrl.DrawTrainCard();
        cntrl.DrawTrainCard();
        
        assertEquals("Trains Cards of p must be 9",9,p.get_train_cards().size());

        p = cntrl.getTurn();
        cntrl.DrawCenterCard(p, 0);
        cntrl.DrawCenterCard(p, 1);
        
        assertEquals("Train cards of p must be 9",9,p.get_train_cards().size());
        
    }
    
    @Test
    public void TrainRobbingTest(){
        Player p1,p2;
        Controller cntrl = new Controller();
        cntrl.init();
        
        p1 = cntrl.getTurn();
        TrainCard tmp = new TrainCard(p1,true,"",CardColor.BLACK);
        cntrl.get_played_cards().add(tmp);
        
        cntrl.get_played_cards().add(tmp);
        cntrl.PlayCards();
        
        p2 = cntrl.getTurn();
        cntrl.get_played_cards().add(tmp);
        cntrl.get_played_cards().add(tmp);
        cntrl.get_played_cards().add(tmp);
        
        cntrl.PlayCards();
        
        assertEquals("P1 should lose all his cards",0,p1.get_rail().get_Black_cards().size());
        assertEquals("P2 should have 3 cards",3,p2.get_rail().get_Black_cards().size());
    }
    
    @Test
    public void PlayCardsTest(){
        Player p1,p2;
        Controller cntrl = new Controller();
        cntrl.init();
        
        p1 = cntrl.getTurn();
        TrainCard tmp = new TrainCard(p1,true,"",CardColor.BLACK);
        p1.get_rail().get_Black_cards().add(tmp);
        cntrl.get_played_cards().add(tmp);
        
        cntrl.get_played_cards().add(tmp);
        cntrl.PlayCards();
        
        
        assertEquals("Card should not have been played",1,p1.get_rail().get_Black_cards().size());
    }
    
    @Test
    public void RailToTrackTest(){
        Player p1;
        Controller cntrl = new Controller();
        cntrl.init();
        
        p1 = cntrl.getTurn();
        TrainCard tmp = new TrainCard(p1,true,"",CardColor.BLACK);
        TrainCard tmp2 = new TrainCard(p1,true,"",CardColor.LOCOMOTIVE);
        TrainCard tmp3 = new TrainCard(p1,true,"",CardColor.WHITE);

        cntrl.get_played_cards().add(tmp);
        cntrl.get_played_cards().add(tmp);
        cntrl.PlayCards();
        p1.get_rail().get_White_cards().add(tmp3);
        p1.get_rail().get_White_cards().add(tmp2);
        
        cntrl.UpdateRailAndTrack(p1);
        
        assertEquals("Black cards must be 1",1,p1.get_track().get_black_cards().size());
        assertEquals("White cards must be 0",0,p1.get_track().get_white_cards().size());
        assertEquals("Locomotive cards must be 1",1,p1.get_track().get_loco_cards().size());
    }
    
    @Test
    public void BuyTicketTest(){
        Player p1;
        Controller cntrl = new Controller();
        cntrl.init();
        
        p1 = cntrl.getTurn();
        TrainCard tmp = new TrainCard(p1,true,"",CardColor.BLACK);
        ArrayList<String> colors = new ArrayList<>();
        colors.add("Black");
        DestinationCard Dc_tmp = new DestinationCard(p1,true,"",5,"","",colors);
        cntrl.get_played_cards().add(tmp);
        cntrl.get_played_cards().add(tmp);
        cntrl.PlayCards();
        cntrl.UpdateRailAndTrack(p1);
        cntrl.BuyTicket(Dc_tmp, p1);
        
        assertEquals("Player has 1 ticket bought",1,p1.get_dest_cards_bought().size());
    }
    
    @Test
    public void TurnTest(){
        Player p1,p2;
        Controller cntrl = new Controller();
        cntrl.init();
        
        p1 = cntrl.getTurn();
        TrainCard tmp = new TrainCard(p1,true,"",CardColor.BLACK);
        ArrayList<String> colors = new ArrayList<>();
        colors.add("Black");
        DestinationCard Dc_tmp = new DestinationCard(p1,true,"",5,"","",colors);
        cntrl.get_played_cards().add(tmp);
        cntrl.get_played_cards().add(tmp);
        cntrl.PlayCards();
        cntrl.UpdateRailAndTrack(p1);
        
        assertNotEquals("Should not be turn of p1",p1,cntrl.getTurn());
        
        p2 = cntrl.getTurn();
        cntrl.DrawTrainCard();
        cntrl.DrawTrainCard();
        
        assertEquals("Should be turn of p1",p1,cntrl.getTurn());
        
        cntrl.setTurn(p2);
        
        assertNotEquals("Should not be turn of p1",p1,cntrl.getTurn());

    }
}
