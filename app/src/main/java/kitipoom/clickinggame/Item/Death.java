package kitipoom.clickinggame.Item;

import kitipoom.clickinggame.Game;

/**
 * Created by kitipoom on 27/5/2559.
 */
public class Death extends Item {
    public Death() {
        name = "Ultimate bomb";
        des = "One Kill to Death";
        price = 10000;
    }


    @Override
    public void Action(Game game) {
        game.getEnermy().attacked(game.getEnermy().getCurrentHp());
    }
}
