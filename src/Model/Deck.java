package Model;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public class Deck {
    
    Stack<TrainCard> train_cards = new Stack<>();
    Stack<DestinationCard> dest_cards = new Stack<>();
    TrainCard center_cards[] = new TrainCard[5];
    BigCitiesCard bigC_cards[] = new BigCitiesCard[6];
    Player deck = new Player("deck");
    
    /**
     * Transformer : Initializes and shuffle all the cards according to the rules of the game
     * Postcondition : All the cards are initialized and shuffled
     */
    public void initCards(){
        TrainCard tmp;
        
        for(int i=0; i<10; i++){
            tmp = new TrainCard(deck,true,"resources/images/trainCards/black.jpg", CardColor.BLACK);
            train_cards.push(tmp);
        }
        for(int i=0; i<10; i++){
            tmp = new TrainCard(deck,true,"resources/images/trainCards/white.jpg", CardColor.WHITE);
            train_cards.push(tmp);
        }
        for(int i=0; i<10; i++){
            tmp = new TrainCard(deck,true,"resources/images/trainCards/green.jpg", CardColor.GREEN);
            train_cards.push(tmp);
        }
        for(int i=0; i<10; i++){
            tmp = new TrainCard(deck,true,"resources/images/trainCards/purple.jpg", CardColor.PURPLE);
            train_cards.push(tmp);
        }
        for(int i=0; i<10; i++){
            tmp = new TrainCard(deck,true,"resources/images/trainCards/orange.jpg", CardColor.ORANGE);
            train_cards.push(tmp);
        }
        for(int i=0; i<10; i++){
            tmp = new TrainCard(deck,true,"resources/images/trainCards/red.jpg", CardColor.RED);
            train_cards.push(tmp);
        }
        for(int i=0; i<10; i++){
            tmp = new TrainCard(deck,true,"resources/images/trainCards/blue.jpg", CardColor.BLUE);
            train_cards.push(tmp);
        }
        for(int i=0; i<10; i++){
            tmp = new TrainCard(deck,true,"resources/images/trainCards/yellow.jpg", CardColor.YELLOW);
            train_cards.push(tmp);
        }
        for(int i=0; i<14; i++){
            tmp = new TrainCard(deck,true,"resources/images/trainCards/locomotive.jpg", CardColor.LOCOMOTIVE);
            train_cards.push(tmp);
        }
        
        bigC_cards[0] = new BigCitiesCard(deck,true,"resources/images/bigCitiesCards/Chicago.jpg",12,"Chicago");
        bigC_cards[1] = new BigCitiesCard(deck,true,"resources/images/bigCitiesCards/Dallas.jpg",10,"Dallas");
        bigC_cards[2] = new BigCitiesCard(deck,true,"resources/images/bigCitiesCards/LosAngeles.jpg",12,"LosAngeles");
        bigC_cards[3] = new BigCitiesCard(deck,true,"resources/images/bigCitiesCards/Miami.jpg",8,"Miami");
        bigC_cards[4] = new BigCitiesCard(deck,true,"resources/images/bigCitiesCards/NewYork.jpg",15,"NewYork");
        bigC_cards[5] = new BigCitiesCard(deck,true,"resources/images/bigCitiesCards/Seattle.jpg",8,"Seattle");
     
        try{
        String path = "resources/files/destinationCards.csv";
        
        BufferedReader br = new BufferedReader( new InputStreamReader( this.getClass().getResourceAsStream("destinationCards.csv")));
        String sCurrentLine = "";
        int i = -1;
        while ((sCurrentLine = br.readLine()) != null) {
            if (i == -1) {
                i = 0;
                continue;
            }
            String[] splitLine = sCurrentLine.split(",");
            String id = splitLine[0];
            String from = splitLine[1];
            String to = splitLine[2];
            int score = Integer.parseInt(splitLine[3]);
            String colorsList = splitLine[4];
            String[] splitColors = colorsList.split("-");
            ArrayList<String> colors = new ArrayList<String>();
            colors.addAll(Arrays.asList(splitColors));
            String imagePath = splitLine[5];
            
            DestinationCard tmpDC = new DestinationCard(deck,true,imagePath,score,from,to,colors);
            dest_cards.add(tmpDC);
        }
        } catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void moirasma(Player p1, Player p2){
        ArrayList<TrainCard> P1_cards = new ArrayList<>();
        ArrayList<TrainCard> P2_cards = new ArrayList<>();
        

        P1_cards.add(train_cards.pop());
        P2_cards.add(train_cards.pop());
        
        Collections.shuffle(dest_cards);
        Collections.shuffle(train_cards);
        
        for(int i=0; i<6; i++){
            train_cards.get(train_cards.size() - 1).set_owner(p1);
            P1_cards.add(train_cards.pop());
        }
        for(int i=0; i<6; i++){
            train_cards.get(train_cards.size() - 1).set_owner(p2);
            P2_cards.add(train_cards.pop());
        }   
        
        p1.set_train_cards(P1_cards);
        p2.set_train_cards(P2_cards);

        
        center_cards[0] = train_cards.pop();
        center_cards[1] = train_cards.pop();
        center_cards[2] = train_cards.pop();
        center_cards[3] = train_cards.pop();
        center_cards[4] = train_cards.pop();
    }
    
    
    /**
     * Accessor : get the card that are in the center
     * Postcondition : the card in the center of the deck have been returned
     * @return  the cards in the center
     */
    public TrainCard[] get_center_cards(){
        return this.center_cards;
    }
    
    /**
     * Transformer : set the cards that are in the center of the deck
     * Postcondition : the cards in the center of the deck has been set
     */
    public void set_center_cards(){
        
    }
    
    /**
     * Accessor : get the big cities cards
     * Postcondition : the big cities cards has been returned
     * @return the big cities card
     */
    public BigCitiesCard[] get_bigC_cards(){
        return this.bigC_cards;
    }
    
    /**
     * Transformer : the player draw the first card of the train card stack
     * Postcondition : player gets the first card of the train card stack and then it its removed
     * @return the top card of the stack
     */
    public TrainCard draw_train_card(){
        if(!train_cards.isEmpty()) return train_cards.pop();
        
        return null;
    }
    
    
    /**
     * Observer : Get the size of the train cards stack
     * Postcondition : the size of the train cards stack has been returned
     * @return the size of the train cards
     */
    public int get_trainC_size(){
        return train_cards.size();
    }
   
    /**
     * Observer : Get the size of the destination cards stack
     * Postcondition : the size of the destination cards stack has been returned
     * @return the size of the destination cards
     */
    public int get_destC_size(){
        return dest_cards.size();
    }
    
    /**
     * Accessor : Get the train cards in the deck
     * Postcondition : the train cards in the deck have been returned
     * @return , a stack of the train card deck
     */
    public Stack<TrainCard> get_train_cards(){
        return this.train_cards;
    }
    
    /**
     * Accessor : Get the destination cards in the deck
     * Postcondition : the destination cards in the deck have been returned
     * @return , a stack of the destination card deck
     */
    public Stack<DestinationCard> get_dest_cards(){
        return this.dest_cards;
    }   
}
