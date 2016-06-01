package kitipoom.clickinggame.Calculator;

public class Allycalculator extends Calculator {
    @Override
    public int getHp(int level) {
        return level * 10;
    }

    @Override
    public int getAtk(int level) {
        return level * 10;
    }

    @Override
    public int getHeal(int level) {
        return level * 10;
    }

    @Override
    public int getSpeed(int level) {
        return 2000 - (level - 1) * 10;
    }

    @Override
    public int getDefend(int level) {
        return level;
    }

    @Override
    public int getStun(int level) {
        return level;
    }

    @Override
    public int getCost(int level) {
        return 0;
    }

}
