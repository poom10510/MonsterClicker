package kitipoom.clickinggame.Ally;

import kitipoom.clickinggame.Calculator.Allycalculator;
import kitipoom.clickinggame.Keyplay.Enemy;
import kitipoom.clickinggame.Keyplay.Player;
import kitipoom.clickinggame.Memento.Memento;
import kitipoom.clickinggame.State.StateCasterAttack;

/**
 * Created by kitipoom on 11/5/2559.
 */
public class Caster extends Ally {
    public Caster() {
        powerLv = 1;
        speedLv = 1;
        healLv = 1;
        state = new StateCasterAttack(this);
        calculator = new Allycalculator();
        calculate();
    }

    @Override
    public void Action(Player player, Enemy enemy) {
        state.Action(player, enemy);

    }


    @Override
    public void calculate() {
        power = calculator.getAtk(powerLv);
        speed = calculator.getSpeed(speedLv);
        heal = calculator.getHeal(healLv);
    }

    public Memento saveState(){
        return  new CasterMemento(powerLv,speedLv,healLv);
    }

    public void loadState(Memento memento){
        if(memento == null)return;
        if(memento.getClass() != CasterMemento.class)return;
        CasterMemento casterMemento = (CasterMemento)memento;
        this.powerLv = casterMemento.powerLv;
        this.speedLv = casterMemento.speedLv;
        this.healLv = casterMemento.healLv;
    }

    public static class CasterMemento extends Memento{
        private int powerLv,speedLv,healLv;

        private CasterMemento(int powerLv,int speedLv,int healLv){
            super("Caster");
            this.powerLv = powerLv;
            this.speedLv = speedLv;
            this.healLv = healLv;
        }
    }


}
