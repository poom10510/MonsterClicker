package kitipoom.clickinggame.Ally;

import kitipoom.clickinggame.Calculator.Allycalculator;
import kitipoom.clickinggame.Keyplay.Enemy;
import kitipoom.clickinggame.Keyplay.Player;
import kitipoom.clickinggame.Memento.Memento;

/**
 * Created by kitipoom on 27/5/2559.
 */
public class Archer extends Ally {


    public Archer() {
        powerLv = 1;
        speedLv = 1;
        stunLv = 1;
        calculator = new Allycalculator();
        calculate();
    }

    @Override
    public void Action(Player player, Enemy enemy) {
        enemy.attacked(power);
    }

    @Override
    public void calculate() {
        power = calculator.getAtk(powerLv);
        speed = calculator.getSpeed(speedLv);
        stun = calculator.getStun(stunLv);
    }

    public Memento saveState(){
        return  new ArcherMemento(powerLv,speedLv,stunLv);
    }

    public void loadState(Memento memento){
        if(memento == null)return;
        if(memento.getClass() != ArcherMemento.class)return;
        ArcherMemento archerMemento = (ArcherMemento)memento;
        this.powerLv = archerMemento.powerLv;
        this.speedLv = archerMemento.speedLv;
        this.stunLv = archerMemento.stunLv;
    }

    public static class ArcherMemento extends Memento{
        private int powerLv,speedLv,stunLv;

        private ArcherMemento(int powerLv,int speedLv,int stunLv){
            super("Archer");
            this.powerLv = powerLv;
            this.speedLv = speedLv;
            this.stunLv = stunLv;
        }
    }
}
