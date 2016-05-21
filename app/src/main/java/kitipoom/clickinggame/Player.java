package kitipoom.clickinggame;

/**
 * Created by kitipoom on 11/5/2559.
 */
public class Player extends Keyplay{
    private int money;
    public Player(int level){
       this.level=level;
        this.cal = new Playercalculator();
        calculate();
        currentHp=getMaxHp();
        money=0;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money += money;
    }
}
