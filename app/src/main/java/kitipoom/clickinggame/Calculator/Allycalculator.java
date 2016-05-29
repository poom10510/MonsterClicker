package kitipoom.clickinggame.Calculator;

/**
 * Created by kitipoom on 29/5/2559.
 */
public class Allycalculator extends Calculator {
    @Override
    public int getHp(int level) {
        return level*10;
    }

    @Override
    public int getAtk(int level) {
        return level*10;
    }

    @Override
    public int getHeal(int level) {
        return level*100;
    }

    @Override
    public int getCostlevel(int level) {
        return 0;
    }

    @Override
    public int getSpeed(int level) {
        return 0;
    }

    @Override
    public int getDefend(int level) {
        return level*1;
    }

    @Override
    public int getStun(int level) {
        return 0;
    }

    @Override
    public int getCtritcal(int level) {
        return 0;
    }
}
