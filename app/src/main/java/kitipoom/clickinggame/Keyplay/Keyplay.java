package kitipoom.clickinggame.Keyplay;

import kitipoom.clickinggame.Calculator.Calculator;

public abstract class Keyplay {
    protected int level;
    private int maxHp, maxHplv;
    protected int currentHp;
    private int atkpower, atkpowerlv;
    private int healpower, healpowerlv;

    protected Calculator cal;

    public Keyplay() {

    }

    public void calculate() {
        maxHp = cal.getHp(maxHplv);
        atkpower = cal.getAtk(atkpowerlv);
        healpower = cal.getHeal(healpowerlv);
    }

    public int getAtkpowerlv() {
        return atkpowerlv;
    }

    public int getHealpowerlv() {
        return healpowerlv;
    }

    public int getMaxHplv() {
        return maxHplv;
    }

    public void Healyourself(int heal) {
        currentHp += heal;
        if (currentHp > maxHp) {
            currentHp = maxHp;
        }
    }

    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
    }

    public int getHealpower() {
        return healpower;
    }

    public int getAtkpower() {
        return atkpower;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public int getLevel() {
        return level;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void attacked(int atk) {
        if (currentHp > 0) {
            currentHp -= atk;
        }
        if (currentHp < 0) {
            currentHp = 0;
        }
    }

    public void setAtkpower(int atkpower) {
        this.atkpower = atkpower;

    }

    public void setAtkpowerlv(int atkpowerlv) {
        this.atkpowerlv = atkpowerlv;
        atkpower = cal.getAtk(this.atkpowerlv);
    }

    public void setHealpower(int healpower) {
        this.healpower = healpower;
    }

    public void setHealpowerlv(int healpowerlv) {
        this.healpowerlv = healpowerlv;
        healpower = cal.getHeal(this.healpowerlv);
    }

    public void setLevel(int level) {
        this.level = level;
        setMaxHplv(this.level);
        currentHp = getMaxHp();
        setAtkpowerlv(this.level);
        setHealpowerlv(this.level);

    }

    public void setMaxHplv(int maxHplv) {
        this.maxHplv = maxHplv;
        maxHp = cal.getHp(this.maxHplv);
    }

    public abstract void Action();
}
