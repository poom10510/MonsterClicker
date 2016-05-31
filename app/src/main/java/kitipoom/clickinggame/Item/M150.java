package kitipoom.clickinggame.Item;

import kitipoom.clickinggame.Game;

/**
 * Created by kitipoom on 27/5/2559.
 */
public class M150 extends Item {
    public M150() {
        name = "PowerUp";
        des = "increase power of Player and Ally";
        price = 2500;
    }

    @Override
    public void Action(Game game) {
        game.boostPower();
    }
}
