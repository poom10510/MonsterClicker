package kitipoom.clickinggame.Ally;

import kitipoom.clickinggame.Calculator.Allycalculator;
import kitipoom.clickinggame.Keyplay.Enemy;
import kitipoom.clickinggame.Keyplay.Player;
import kitipoom.clickinggame.Memento.Memento;

public class Warrior extends Ally {
    @Override
    public void Action(Player player, Enemy enemy) {
        enemy.attacked(power);
    }

    public Warrior() {
        powerLv = 1;
        speedLv = 1;
        defendLv = 1;
        calculator = new Allycalculator();
        calculate();
    }

    @Override
    public void calculate() {
        power = calculator.getAtk(powerLv);
        speed = calculator.getSpeed(speedLv);
        defend = calculator.getDefend(defendLv);
    }

    public Memento saveState() {
        return new WarriorMemento(powerLv, speedLv, defendLv);
    }

    public void loadState(Memento memento) {
        if (memento == null) return;
        if (memento.getClass() != WarriorMemento.class) return;
        WarriorMemento warriorMemento = (WarriorMemento) memento;
        this.powerLv = warriorMemento.powerLv;
        this.speedLv = warriorMemento.speedLv;
        this.defendLv = warriorMemento.defendLv;
    }

    public static class WarriorMemento extends Memento {
        private int powerLv, speedLv, defendLv;

        private WarriorMemento(int powerLv, int speedLv, int defendLv) {
            super("Warrior");
            this.powerLv = powerLv;
            this.speedLv = speedLv;
            this.defendLv = defendLv;
        }
    }


}
