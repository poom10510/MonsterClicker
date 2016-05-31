package kitipoom.clickinggame.Keyplay;

import kitipoom.clickinggame.Calculator.Playercalculator;
import kitipoom.clickinggame.Memento.Memento;

/**
 * Created by kitipoom on 11/5/2559.
 */
public class Player extends Keyplay {
    private int money;

    public Player() {
        super();
        this.cal = new Playercalculator();
        setMaxHplv(1);
        setAtkpowerlv(1);
        setHealpowerlv(1);
        money = 0;
        //calculate();
        currentHp = getMaxHp();
    }

    @Override
    public void Action() {

    }

    public int getMoney(){
        return money;
    }

    public void receiveMoney(int cost){
        this.money += cost;
    }

    public void lossMoney(int cost){
        this.money -= cost;
    }

    public void setMoney(int money){
        this.money = money;
    }

    public Memento saveState(){
        return  new PlayerMemento(getAtkpowerlv(),getHealpowerlv(),getMaxHplv(),this.money);
    }

    public void loadState(Memento memento){
        if(memento == null)return;
        if(memento.getClass() != PlayerMemento.class)return;
        PlayerMemento playerMemento = (PlayerMemento)memento;
        setAtkpowerlv(playerMemento.atkPowerLv);
        setHealpowerlv(playerMemento.healPowerLv);
        setMaxHplv(playerMemento.healthLV);
        this.money = playerMemento.money;
    }

    public static class PlayerMemento extends Memento{
        private int atkPowerLv,healPowerLv,healthLV,money;

        private PlayerMemento(int atkPowerLv,int healPowerLv,int healthLV,int money){
            super("Player");
            this.atkPowerLv = atkPowerLv;
            this.healPowerLv = healPowerLv;
            this.healthLV = healthLV;
            this.money = money;
        }
    }
}
