package kitipoom.clickinggame.Items;

import kitipoom.clickinggame.Models.Game;

public abstract class Item {
    protected int price;
    protected String name;
    protected String des;

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void Action(Game game);
}
