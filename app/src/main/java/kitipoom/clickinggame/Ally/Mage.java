package kitipoom.clickinggame.Ally;

import kitipoom.clickinggame.Calculator.Calculator;
import kitipoom.clickinggame.Keyplay.Enermy;
import kitipoom.clickinggame.Keyplay.Player;

/**
 * Created by kitipoom on 11/5/2559.
 */
public class Mage extends Ally {
    @Override
    public void Action(Player player, Enermy enermy) {
        power = calculator.getAtk(powerLv);
        speed = calculator.getSpeed(speedLv);
        heal = calculator.getHeal(healLv);
    }

    @Override
    public void calculate() {

    }


}
