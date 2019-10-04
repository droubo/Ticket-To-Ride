package Model;

import java.util.*;

public class RailYard {
    
    private Stack<TrainCard> BlueC = new Stack<TrainCard>();;
    private Stack<TrainCard> BlackC = new Stack<TrainCard>();
    private Stack<TrainCard> GreenC = new Stack<TrainCard>();
    private Stack<TrainCard> OrangeC = new Stack<TrainCard>();
    private Stack<TrainCard> WhiteC = new Stack<TrainCard>();
    private Stack<TrainCard> PurpleC = new Stack<TrainCard>();
    private Stack<TrainCard> RedC = new Stack<TrainCard>();
    private Stack<TrainCard> YellowC = new Stack<TrainCard>();
    
    

    /**
     * Accessor : Get the stack of the blue cards
     * Postcondition : the stack of blue cards has been returned
     * @return the stack of blue cards
     */
    public Stack<TrainCard> get_Blue_cards(){
        return this.BlueC;
    }

    /**
     * Accessor : Get the stack of the Black cards
     * Postcondition : the stack of Black cards has been returned
     * @return the stack of Black cards
     */
    public Stack<TrainCard> get_Black_cards(){
        return this.BlackC;
    }

    /**
     * Accessor : Get the stack of the Green cards
     * Postcondition : the stack of Green cards has been returned
     * @return the stack of Green cards
     */
    public Stack<TrainCard> get_Green_cards(){
        return this.GreenC;
    }
    
    /**
     * Accessor : Get the stack of the Orange cards
     * Postcondition : the stack of Orange cards has been returned
     * @return the stack of Orange cards
     */
    public Stack<TrainCard> get_Orange_cards(){
        return this.OrangeC;
    }
    
    /**
     * Accessor : Get the stack of the White cards
     * Postcondition : the stack of White cards has been returned
     * @return the stack of White cards
     */
    public Stack<TrainCard> get_White_cards(){
        return this.WhiteC;
    }
    
    /**
     * Accessor : Get the stack of the Purple cards
     * Postcondition : the stack of Purple cards has been returned
     * @return the stack of Purple cards
     */
    public Stack<TrainCard> get_Purple_cards(){
        return this.PurpleC;
    }
    
    /**
     * Accessor : Get the stack of the Red cards
     * Postcondition : the stack of Red cards has been returned
     * @return the stack of Red cards
     */
    public Stack<TrainCard> get_Red_cards(){
        return this.RedC;
    }
     
    
    /**
     * Accessor : Get the stack of the Yellow cards
     * Postcondition : the stack of Yellow cards has been returned
     * @return the stack of Yellow cards
     */
    public Stack<TrainCard> get_Yellow_cards(){
        return this.YellowC;
    }
    
}
