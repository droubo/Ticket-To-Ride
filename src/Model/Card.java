package Model;

public class Card {
    
    private Player owner;
    private boolean isActive;
    private String image;
    
    
    /**
     * Constructor : Constructs a new instance of card with the give parameters, owner , isActive and image
     * Postcondition : A new card will be initialized with the given parameters
     * @param owner , the owner of the card
     * @param isActive , is card is active or not
     * @param image , the image of the card
     */
    public Card(Player owner, boolean isActive, String image){
        this.owner = owner;
        this.isActive = isActive;
        this.image = image;
    }
    
    /**
     * Transformer : Set the owner of the card
     * Postcondition : the owner of the card has been set
     * @param owner , the owner of the card
     */
    public void set_owner(Player owner){
        this.owner = owner;
    }
    
    /**
     * Accessor : Get the owner of the card
     * Postcondition : the owner of the card has been returned
     * @return the owner of the card
     */
    public Player get_owner(){
        return this.owner;
    }
    
    /**
     * Transformer : Set the image of the card
     * Postcondition : the image of the card has been set
     * @param image , the image of the card
     */
    public void set_image(String image){
        this.image = image;
    }
    
    /**
     * Accessor : Get the image of the card
     * Postcondition : the image of the card has been returned
     * @return the image of the card
     */
    public String get_image(){
        return image;
    }
    
    
    /**
     * Observer : returns a boolean that indicates of a card is active or not
     * Postcondition : true or false has been returned, depending if card is active or not
     * @return true of card is active or false if it is not
     */
    public boolean is_Active(){
        return this.isActive;
    }
}
