package Model;

import java.util.ArrayList;

public class Player {
    
    private String name;
    private ArrayList<TrainCard> train_cards;
    private ArrayList<DestinationCard> dest_cards  = new ArrayList<>();
    private ArrayList<BigCitiesCard> bigC_cards  = new ArrayList<>();;
    private ArrayList<DestinationCard> dest_cards_bought  = new ArrayList<>();;
    private RailYard rail = new RailYard();
    private OnTheTrack track = new OnTheTrack();
    private int score;
    private int Counters[] = new int[6]; //Array of counters for the 6 big cities. Index 0 is Chicago, 1 is Dallas, 2 is LosAngeles, 3 is Miami, 4 is NewYork and 5 is Seattle
    /**
     * Constructor : sets the name of the player
     * Postcondition : the name of the player has been set
     * @param name , the name of the player
     */
    public Player(String name){
        this.name = name;
    }
    
    /**
     * Accessor : get the train cards of the player
     * Postcondition : a list with the train cards of the player has been returned
     * @return a list with the train cards
     */
    public ArrayList<TrainCard> get_train_cards(){
        return this.train_cards;
    }

    /**
     * Transformer : set the train cards of the player
     * Postcondition : the train cards of the player has been set
     * @param train_cards , the train cards of the player
     */
    public void set_train_cards(ArrayList<TrainCard> train_cards){
        this.train_cards = train_cards;
    }
 
    /**
     * Accessor : get the destination cards of the player
     * Postcondition : a list with the destination cards of the player has been returned
     * @return a list with the destination cards
     */
    public ArrayList<DestinationCard> get_dest_cards(){
        return this.dest_cards;
    }

    /**
     * Transformer : set the destination cards of the player
     * Postcondition : the destination cards of the player has been set
     * @param dest_cards , the destination cards of the player
     */
    public void set_dest_cards(ArrayList<DestinationCard> dest_cards){
        this.dest_cards = dest_cards;
    }
    
   /**
     * Accessor : get the destination cards bought by the player
     * Postcondition : a list with the destination cards bought by the player has been returned
     * @return a list with the destination cards bought
     */
    public ArrayList<DestinationCard> get_dest_cards_bought(){
        return this.dest_cards_bought;
    }

    /**
     * Transformer : set the destination cards bought by the player
     * Postcondition : the destination cards bought by the player has been set
     * @param dest_cards_bought , the destination cards bought by the player
     */
    public void set_dest_cards_bought(ArrayList<DestinationCard> dest_cards_bought){
        this.dest_cards_bought = dest_cards_bought;
    }
    
    /**
     * Accessor : get the big city cards of the player
     * Postcondition : a list with the big city cards of the player has been returned
     * @return a list with the big city cards
     */
    public ArrayList<BigCitiesCard> get_bigC_cards(){
        return this.bigC_cards;
    }

    /**
     * Transformer : set the big city cards of the player
     * Postcondition : the big city cards of the player has been set
     * @param bigC_cards , the big city cards of the player
     */
    public void set_bigC_cards(ArrayList<BigCitiesCard> bigC_cards){
        this.bigC_cards = bigC_cards;
    }
    
    /**
     * Accessor : get the score of the player
     * Postcondition : the score of the player has been returned
     * @return the score of the player
     */
    public int get_score(){
        return this.score;
    }
    
    /**
     * Transformer : set the score of the player
     * Postcondition : the score of the player has been set
     * @param score , the score of the player
     */
    public void set_score(int score){
        this.score = score;
    }

    /**
     * Accessor : get the railyard of the player
     * Postcondition : the railyard of the player has been returned
     * @return the railyard of the player
     */
    public RailYard get_rail(){
        return this.rail;
    }
    
    /**
     * Transformer : set the railyard of the player
     * Postcondition : the railyard of the player has been set
     * @param rail , the railyard of the player
     */
    public void set_rail(RailYard rail){
        this.rail = rail;
    }
    /**
     * Accessor : get the track of the player
     * Postcondition : the track of the player has been returned
     * @return the track of the player
     */
    public OnTheTrack get_track(){
        return this.track;
    }
    /**
     * Transformer : set the track of the player
     * Postcondition : the track of the player has been set
     * @param track , the track of the player
     */
    public void set_track(OnTheTrack track){
        this.track = track;
    }
    
    /**
     * Transformer : inizializes all the city counters as 0
     * Postcondition : all city counters has been initialized
     */
    public void init_counters(){
        for(int i = 0; i < 6; i++){
            Counters[i] = 0;
        }
    }
    
    /**
     * Accessor : get the array for the counters
     * Postcondition: the array of the counters has been returned
     * @return the array of the counters
     */
    public int[] get_counters(){
        return Counters;
    }
    
    /**
     * Accessor : get the name of the player
     * Postcondition : the name of the player has been returned
     * @return the name of the player
     */
    @Override
    public String toString(){
        return this.name;
    }
}
