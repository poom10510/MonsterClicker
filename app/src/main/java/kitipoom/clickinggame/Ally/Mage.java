package kitipoom.clickinggame.Ally;

import kitipoom.clickinggame.Calculator.Calculator;
import kitipoom.clickinggame.Keyplay.Enermy;
import kitipoom.clickinggame.Keyplay.Player;

/**
 * Created by kitipoom on 11/5/2559.
 */
public class Mage extends Ally {
    public Mage(){
        powerLv = 1 ;
        speedLv = 1 ;
        defendLv = 1 ;
        calculate();
    }
    public void Action(Player player, Enermy enermy) {
        enermy.attacked(power);
    }

    @Override
    public void calculate() {
        power = calculator.getAtk(powerLv);
        speed = calculator.getSpeed(speedLv);
        heal = calculator.getHeal(healLv);
    }


}
