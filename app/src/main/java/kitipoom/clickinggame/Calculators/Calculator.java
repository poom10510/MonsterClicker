package kitipoom.clickinggame.Calculators;

public abstract class Calculator {
    public abstract int getHp(int level);

    public abstract int getAtk(int level);

    public abstract int getHeal(int level);

    public abstract int getSpeed(int level);

    public abstract int getDefend(int level);

    public abstract int getStun(int level);

    public abstract int getCost(int level);
}
