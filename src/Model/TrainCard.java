package Model;


public class TrainCard extends Card{
    
    private CardColor color;
    

    /**
     * Constructor : Constructs a new instance of card with the give parameters, owner , isActive , image and color
     * Postcondition : A new train card will be initialized with the given parameters
     * @param owner , the owner of the card
     * @param isActive , is card is active or not
     * @param image , the image of the card
     * @param color , the color of the card
     */
    public TrainCard(Player owner, boolean isActive, String image, CardColor color){
        super(owner,isActive,image);
        this.color = color;
    }
    
    
    /**
     * Accessor : get the color of the train card
     * Postcondition : the color of the train card have been returned
     * @return the color of the train card
     */
    public CardColor get_color(){
        return this.color;
    }
    /**
     * Transformer : set the color of the train card
     * Postcondition : the color of the train card has been set
     * @param color , the color of the train card
     */
    public void set_color(CardColor color){
        this.color = color;
    }
}
