package kitipoom.clickinggame.Keyplay;

import kitipoom.clickinggame.Calculator.Playercalculator;

/**
 * Created by kitipoom on 11/5/2559.
 */
public class Player extends Keyplay{
    private int money;
    private int cost;
    public Player(int level){
       this.level=level;
        this.cal = new Playercalculator();
        calculate();
        currentHp=getMaxHp();
        cost = cal.getCostlevel(this.level);
        money=0;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money += money;
    }

    @Override
    public void Action() {

    }
}
