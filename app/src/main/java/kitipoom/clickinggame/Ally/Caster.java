package kitipoom.clickinggame.Ally;

import kitipoom.clickinggame.Calculator.Allycalculator;
import kitipoom.clickinggame.Keyplay.Enermy;
import kitipoom.clickinggame.Keyplay.Player;
import kitipoom.clickinggame.State.StateCasterAttack;

/**
 * Created by kitipoom on 11/5/2559.
 */
public class Caster extends Ally {
    boolean isheal=false;

    public Caster(){
        powerLv = 1 ;
        speedLv = 1 ;
        healLv = 1 ;
        state = new StateCasterAttack(this);
        calculator = new Allycalculator();
        calculate();
    }
    public void Action(Player player, Enermy enermy) {
            state.Action(player,enermy);

    }



    @Override
    public void calculate() {
        power = calculator.getAtk(powerLv);
        speed = calculator.getSpeed(speedLv);
        heal = calculator.getHeal(healLv);
    }



}
