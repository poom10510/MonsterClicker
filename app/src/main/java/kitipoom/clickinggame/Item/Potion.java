package kitipoom.clickinggame.Item;

import kitipoom.clickinggame.Game;

/**
 * Created by kitipoom on 27/5/2559.
 */
public class Potion extends Item {

    public Potion() {
        name = "Potion";
        des = "Heal Player to full Hp";
        price = 1000;
    }

    @Override
    public void Action(Game game) {
        game.getPlayer().setCurrentHp(game.getPlayer().getMaxHp());
    }
}
