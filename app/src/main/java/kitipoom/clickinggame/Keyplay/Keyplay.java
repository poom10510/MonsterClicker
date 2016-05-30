package kitipoom.clickinggame.Keyplay;

import kitipoom.clickinggame.Calculator.Calculator;

/**
 * Created by kitipoom on 19/5/2559.
 */
public abstract class Keyplay {
    protected int level;
    private int maxHp,maxHplv;
    protected int currentHp;
    private int atkpower,atkpowerlv;
    private int healpower,healpowerlv;
    private int criticalpower,criticalpowerlv;

    protected Calculator cal;

    public void levelUp(){
        level++;
        setCriticalpowerlv(level);
        setMaxHplv(level);
        setAtkpowerlv(level);
        setHealpowerlv(level);
        calculate();
    }

    public void setValue(int level,Calculator cal) {
        this.level=level;
        this.cal = cal;
        calculate();
        currentHp=getMaxHp();
    }

    public void calculate(){
        maxHp=cal.getHp(maxHplv);
        atkpower=cal.getAtk(atkpowerlv);
        healpower=cal.getHeal(healpowerlv);
        criticalpower=cal.getCtritcal(criticalpowerlv);
    }

    public Calculator getCal() {
        return cal;
    }

    public int getAtkpowerlv() {
        return atkpowerlv;
    }

    public int getCriticalpower() {
        return criticalpower;
    }

    public int getCriticalpowerlv() {
        return criticalpowerlv;
    }

    public int getHealpowerlv() {
        return healpowerlv;
    }

    public int getMaxHplv() {
        return maxHplv;
    }
    public void Healyourself(int heal){
        currentHp+=heal;
        if(currentHp>maxHp){
            currentHp=maxHp;
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
    public void attacked(int atk){
        if(currentHp>0) {
            currentHp -= atk;
        }
        if(currentHp<0){
            currentHp=0;
        }
    }

    public void setCal(Calculator cal) {
        this.cal = cal;
    }

    public void setAtkpower(int atkpower) {
        this.atkpower = atkpower;

    }

    public void setAtkpowerlv(int atkpowerlv) {
        this.atkpowerlv = atkpowerlv;
        atkpower=cal.getAtk(this.atkpowerlv);
    }

    public void setCriticalpower(int criticalpower) {
        this.criticalpower = criticalpower;
    }

    public void setCriticalpowerlv(int criticalpowerlv) {
        this.criticalpowerlv = criticalpowerlv;
        criticalpower=cal.getCtritcal(this.criticalpowerlv);
    }

    public void setHealpower(int healpower) {
        this.healpower = healpower;
    }

    public void setHealpowerlv(int healpowerlv) {
        this.healpowerlv = healpowerlv;
        healpower=cal.getHeal(this.healpowerlv);
    }

    public void setLevel(int level) {
        this.level = level;
        setCriticalpowerlv(this.level);
        setMaxHplv(this.level);
        currentHp = getMaxHp();
        setAtkpowerlv(this.level);
        setHealpowerlv(this.level);

    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public void setMaxHplv(int maxHplv) {
        this.maxHplv = maxHplv;
        maxHp=cal.getHp(this.maxHplv);
    }

    public abstract void Action();
}
