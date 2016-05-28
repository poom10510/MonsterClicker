package kitipoom.clickinggame.Item;

import kitipoom.clickinggame.Game;

/**
 * Created by kitipoom on 27/5/2559.
 */
public class Nuky extends Item {
    public Nuky(){
        name= "Ultimate bomb";
        des = "Kill enermy with 1,000,000 damage";
        price = 10000;
    }


    @Override
    public void Action(Game game) {
        game.getEnermy().attacked(1000000);
    }
}
