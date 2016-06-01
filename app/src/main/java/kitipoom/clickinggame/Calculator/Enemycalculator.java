package kitipoom.clickinggame.Calculator;

public class Enemycalculator extends Calculator {
    @Override
    public int getHp(int level) {
        return level * 200;
    }

    @Override
    public int getAtk(int level) {
        return level * 10;
    }

    @Override
    public int getHeal(int level) {
        return 0;
    }

    @Override
    public int getSpeed(int level) {
        return 0;
    }

    @Override
    public int getDefend(int level) {
        return 0;
    }

    @Override
    public int getStun(int level) {
        return 0;
    }

    @Override
    public int getCost(int level) {
        return level * 50;
    }
}
