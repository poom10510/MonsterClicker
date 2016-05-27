package kitipoom.clickinggame.Ally;

import kitipoom.clickinggame.Calculator.Calculator;
import kitipoom.clickinggame.Keyplay.Enermy;
import kitipoom.clickinggame.Keyplay.Player;

/**
 * Created by kitipoom on 11/5/2559.
 */
public class Warrior extends Ally {
    private int power,powerLv;
    private int speed,speedLv;
    private int defend,defendLv;
    private Calculator calculator;
    @Override
    public void Action(Player player, Enermy enermy) {
        enermy.attacked(power);
    }

    public int getPowerLv() {
        return powerLv;
    }

    public void setPowerLv(int powerLv) {
        this.powerLv = powerLv;
    }

    public int getSpeedLv() {
        return speedLv;
    }

    public void setSpeedLv(int speedLv) {
        this.speedLv = speedLv;
    }

    public int getDefendLv() {
        return defendLv;
    }

    public void setDefendLv(int defendLv) {
        this.defendLv = defendLv;
    }

    @Override
    public void calculate() {
        power = calculator.getAtk(powerLv);
        speed = calculator.getSpeed(speedLv);
        defend = calculator.getDefend(defendLv);
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getDefend() {
        return defend;
    }

    public void setDefend(int defend) {
        this.defend = defend;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
