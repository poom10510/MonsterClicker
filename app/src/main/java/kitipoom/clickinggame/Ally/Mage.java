package kitipoom.clickinggame.Ally;

import kitipoom.clickinggame.Calculator.Calculator;
import kitipoom.clickinggame.Keyplay.Enermy;
import kitipoom.clickinggame.Keyplay.Player;

/**
 * Created by kitipoom on 11/5/2559.
 */
public class Mage extends Ally {
    private int power,powerLv;
    private int speed,speedLv;
    private int heal,healLv;
    Calculator calculator;
    @Override
    public void Action(Player player, Enermy enermy) {
        power = calculator.getAtk(powerLv);
        speed = calculator.getSpeed(speedLv);
        heal = calculator.getHeal(healLv);
    }

    @Override
    public void calculate() {

    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getPowerLv() {
        return powerLv;
    }

    public void setPowerLv(int powerLv) {
        this.powerLv = powerLv;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeedLv() {
        return speedLv;
    }

    public void setSpeedLv(int speedLv) {
        this.speedLv = speedLv;
    }

    public int getHeal() {
        return heal;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }

    public int getHealLv() {
        return healLv;
    }

    public void setHealLv(int healLv) {
        this.healLv = healLv;
    }
}
