package kitipoom.clickinggame.Items;

import kitipoom.clickinggame.Models.Game;

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
