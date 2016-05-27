package kitipoom.clickinggame.Ally;

import kitipoom.clickinggame.Calculator.Calculator;
import kitipoom.clickinggame.Keyplay.Enermy;
import kitipoom.clickinggame.Keyplay.Player;

/**
 * Created by kitipoom on 27/5/2559.
 */
public class Archer extends Ally {
    private int power,powerLv;
    private int speed,speedLv;
    private int stun,stunLv;
    Calculator calculator;
    @Override
    public void Action(Player player, Enermy enermy) {

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

    public int getStun() {
        return stun;
    }

    public void setStun(int stun) {
        this.stun = stun;
    }

    public int getStunLv() {
        return stunLv;
    }

    public void setStunLv(int stunLv) {
        this.stunLv = stunLv;
    }

    @Override
    public void calculate() {
        power = calculator.getAtk(powerLv);
        speed = calculator.getSpeed(speedLv);
        stun = calculator.getStun(stunLv);

    }
}
