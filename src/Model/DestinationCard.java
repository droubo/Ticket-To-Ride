package Model;

import java.util.ArrayList;


public class DestinationCard extends PointsCard {
    
    private ArrayList<String> colors= new ArrayList<>();
    private final String destination;
    private final String departure;

    /**
     * Constructor : Constructs a new instance of card with the give parameters, owner , isActive , image , destination and departure city
     * Postcondition : A new train card will be initialized with the given parameters
     * @param owner , the owner of the card
     * @param isActive , is card is active or not
     * @param image , the image of the card
     * @param points , the points of the card
     * @param destination , the destination city of the card
     * @param departure , the departure city of the card
     * @param colors, the colors that are required to buy the card
     */
    public DestinationCard(Player owner, boolean isActive, String image, int points, String destination, String departure, ArrayList<String> colors){
        super(owner,isActive,image,points);
        this.destination = destination;
        this.departure = departure;
        this.colors = colors;
    }
    
    /**
     * Accessor : get the destination city of the card
     * Postcondition : the destination city of the card has been returned
     * @return the destination city
     */
    public String get_destination(){
        return this.destination;
    }
    
    /**
     * Accessor : get the departure city of the card
     * Postcondition : the departure city of the card has been returned
     * @return the departure city
     */
    public String get_departure(){
        return this.departure;
    }
    
    /**
     * Accessor : get the colors of the card
     * Postcondition : the colors of the card has been returned
     * @return the colors of the card
     */  
    public ArrayList<String> get_colors(){
        return this.colors;
    }
    
}
