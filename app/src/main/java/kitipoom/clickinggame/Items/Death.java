package kitipoom.clickinggame.Items;

import kitipoom.clickinggame.Models.Game;

public class Death extends Item {
    public Death() {
        name = "Ultimate bomb";
        des = "One Kill to Death";
        price = 10000;
    }

    @Override
    public void Action(Game game) {
        game.getEnemy().attacked(game.getEnemy().getCurrentHp());
    }
}
