package kitipoom.clickinggame.Ally;

import kitipoom.clickinggame.Calculator.Calculator;
import kitipoom.clickinggame.Keyplay.Enemy;
import kitipoom.clickinggame.Keyplay.Player;
import kitipoom.clickinggame.Memento.Memento;
import kitipoom.clickinggame.State.State;

public abstract class Ally {
    protected int power, powerLv;
    protected int speed, speedLv;
    protected int stun, stunLv;
    protected int heal, healLv;
    protected int defend, defendLv;
    protected Calculator calculator;
    protected State state;

    public abstract void Action(Player player, Enemy enemy);

    public abstract void calculate();


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
        power = calculator.getAtk(this.powerLv);
    }

    public int getSpeed() {
        return speed;
    }

    public int getSpeedLv() {
        return speedLv;

    }

    public void setSpeedLv(int speedLv) {
        this.speedLv = speedLv;
        speed = calculator.getSpeed(this.speedLv);
    }

    public int getHeal() {
        return heal;
    }

    public int getHealLv() {
        return healLv;
    }

    public void setHealLv(int healLv) {
        this.healLv = healLv;
        heal = calculator.getHeal(this.healLv);
    }

    public int getDefendLv() {
        return defendLv;
    }

    public void setDefendLv(int defendLv) {
        this.defendLv = defendLv;
        defend = calculator.getDefend(this.defendLv);
    }

    public int getDefend() {
        return defend;
    }

    public int getStun() {
        return stun;
    }

    public int getStunLv() {
        return stunLv;
    }

    public void setStunLv(int stunLv) {
        this.stunLv = stunLv;
        stun = calculator.getStun(this.stunLv);
    }

    public void setCalculator(Calculator calculator) {
        this.calculator = calculator;
    }

    public Calculator getCalculator() {
        return calculator;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public abstract Memento saveState();

    public abstract void loadState(Memento memento);
}
