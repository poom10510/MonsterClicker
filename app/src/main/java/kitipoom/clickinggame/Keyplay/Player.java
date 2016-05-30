package kitipoom.clickinggame.Keyplay;

import kitipoom.clickinggame.Calculator.Playercalculator;

/**
 * Created by kitipoom on 11/5/2559.
 */
public class Player extends Keyplay{
    private int money;
    public Player(int level){
        super();
        this.cal = new Playercalculator();
        //setLevel(level);
        setMaxHplv(1);
        setAtkpowerlv(1);
        setHealpowerlv(1);

        //calculate();
        currentHp=getMaxHp();
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
