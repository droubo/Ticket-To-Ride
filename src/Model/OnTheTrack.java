package Model;

import java.util.Stack;


public class OnTheTrack {
    
    
    private Stack<TrainCard> BlueC;
    private Stack<TrainCard> BlackC;
    private Stack<TrainCard> GreenC;
    private Stack<TrainCard> OrangeC;
    private Stack<TrainCard> WhiteC;
    private Stack<TrainCard> PurpleC;
    private Stack<TrainCard> RedC;
    private Stack<TrainCard> YellowC;
    private Stack<TrainCard> LocoC;
    
    public OnTheTrack(){
        BlueC = new Stack<>();
        BlackC = new Stack<>();
        GreenC = new Stack<>();
        OrangeC = new Stack<>();
        WhiteC = new Stack<>();
        PurpleC = new Stack<>();
        RedC = new Stack<>();
        YellowC = new Stack<>();
        LocoC = new Stack<>();
    }
    /**
     * Collects the first card of each color stack from the railyard
     * Postcondition : the first element of each stack has been added on the track and removed from railyard
     * @param rail , the railyard of the player
     */
    public void collectFromRailYard(RailYard rail){
        if(!rail.get_Black_cards().isEmpty()){
            if(rail.get_Black_cards().peek().get_color().equals(CardColor.BLACK)) BlackC.push(rail.get_Black_cards().pop());
            else LocoC.push(rail.get_Black_cards().pop());
        }
        if(!rail.get_Blue_cards().isEmpty()){
            if(rail.get_Blue_cards().peek().get_color().equals(CardColor.BLUE)) BlueC.push(rail.get_Blue_cards().pop());
            else LocoC.push(rail.get_Blue_cards().pop());
        }
        if(!rail.get_Orange_cards().isEmpty()){
            if(rail.get_Orange_cards().peek().get_color().equals(CardColor.ORANGE)) OrangeC.push(rail.get_Orange_cards().pop());
            else LocoC.push(rail.get_Orange_cards().pop());
        }
        if(!rail.get_White_cards().isEmpty()){
            if(rail.get_White_cards().peek().get_color().equals(CardColor.WHITE)) WhiteC.push(rail.get_White_cards().pop());
            else LocoC.push(rail.get_White_cards().pop());
        }
        if(!rail.get_Yellow_cards().isEmpty()){
            if(rail.get_Yellow_cards().peek().get_color().equals(CardColor.YELLOW)) YellowC.push(rail.get_Yellow_cards().pop());
            else LocoC.push(rail.get_Yellow_cards().pop());
        }
        if(!rail.get_Red_cards().isEmpty()){
            if(rail.get_Red_cards().peek().get_color().equals(CardColor.RED)) RedC.push(rail.get_Red_cards().pop());
            else LocoC.push(rail.get_Red_cards().pop());
        }
        if(!rail.get_Green_cards().isEmpty()){
            if(rail.get_Green_cards().peek().get_color().equals(CardColor.GREEN)) GreenC.push(rail.get_Green_cards().pop());
            else LocoC.push(rail.get_Green_cards().pop());
        }
        if(!rail.get_Purple_cards().isEmpty()){
            if(rail.get_Purple_cards().peek().get_color().equals(CardColor.PURPLE)) PurpleC.push(rail.get_Purple_cards().pop());
            else LocoC.push(rail.get_Purple_cards().pop());
        }    
    }
    
    
    /**
     * ACCESSORS FOR THE STACKS OF EACH COLOR
     */

    
    public Stack<TrainCard> get_black_cards(){
        return this.BlackC;
    }
    public Stack<TrainCard> get_blue_cards(){
        return this.BlueC;
    }
    public Stack<TrainCard> get_orange_cards(){
        return this.OrangeC;
    }
    public Stack<TrainCard> get_white_cards(){
        return this.WhiteC;
    }
    public Stack<TrainCard> get_purple_cards(){
        return this.PurpleC;
    }
    public Stack<TrainCard> get_green_cards(){
        return this.GreenC;
    }
    public Stack<TrainCard> get_yellow_cards(){
        return this.YellowC;
    }
    public Stack<TrainCard> get_red_cards(){
        return this.RedC;
    }
    public Stack<TrainCard> get_loco_cards(){
        return this.LocoC;
    }        
}
