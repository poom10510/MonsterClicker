package kitipoom.clickinggame.Ally;

import kitipoom.clickinggame.Calculator.Calculator;
import kitipoom.clickinggame.Keyplay.Enermy;
import kitipoom.clickinggame.Keyplay.Player;

/**
 * Created by kitipoom on 27/5/2559.
 */
public class Archer extends Ally {



    public Archer(){
        powerLv = 1 ;
        speedLv = 1 ;
        defendLv = 1 ;
        calculate();
    }
    @Override
    public void Action(Player player, Enermy enermy) {
        enermy.attacked(power);
    }
    @Override
    public void calculate() {
        power = calculator.getAtk(powerLv);
        speed = calculator.getSpeed(speedLv);
        stun = calculator.getStun(stunLv);

    }
}
