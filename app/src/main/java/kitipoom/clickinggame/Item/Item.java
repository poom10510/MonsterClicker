package kitipoom.clickinggame.Item;

import kitipoom.clickinggame.Game;

/**
 * Created by kitipoom on 27/5/2559.
 */
public abstract class Item {
    protected int price;
    protected String name;
    protected String des;

    public int getPrice() {
        return price;
    }

    public String getDes() {
        return des;
    }

    public String getName() {
        return name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void Action(Game game);
}
