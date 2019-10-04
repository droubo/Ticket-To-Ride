package Model;


public class PointsCard extends Card{
    
    private final int points;
    
    
    /**
     * Constructor : Constructs a new instance of card with the give parameters, owner , isActive , image and points
     * Postcondition : A new train card will be initialized with the given parameters
     * @param owner , the owner of the card
     * @param isActive , is card is active or not
     * @param image , the image of the card
     * @param points , the points of the card
     */
    public PointsCard(Player owner, boolean isActive, String image, int points){
        super(owner,isActive,image);
        this.points = points;
    }
    
    
    /**
     * Accessor : get the points of the card
     * Postcondition : the points of the card have been returned
     * @return the points of the card
     */
    public int get_points(){
        return this.points;
    }
    
}
