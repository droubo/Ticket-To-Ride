package Model;


public class BigCitiesCard extends PointsCard {
    
    private final String city;

    /**
     * Constructor : Constructs a new instance of card with the give parameters, owner , isActive , image, points and city
     * Postcondition : A new train card will be initialized with the given parameters
     * @param owner , the owner of the card
     * @param isActive , is card is active or not
     * @param image , the image of the card
     * @param points , the points of the card
     * @param city , the city of the card
     */
    public BigCitiesCard(Player owner, boolean isActive, String image, int points, String city){
        super(owner,isActive,image,points);
        this.city = city;
    }
    
    /**
     * Accessor : get the city of the card
     * Postcondition : the city of the card has been returned
     * @return the city of the card
     */
    public String get_city(){
        return this.city;
    }
   
    
}
