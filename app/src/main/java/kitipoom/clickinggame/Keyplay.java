package kitipoom.clickinggame;

/**
 * Created by kitipoom on 19/5/2559.
 */
public abstract class Keyplay {
    protected int level;
    private int maxHp;
    protected int currentHp;
    private int atkpower;
    private int healpower;
    protected Calculator cal;
    public void levelUp(){
        level++;
        calculate();
    }

    public void setValue(int level,Calculator cal) {
        this.level=level;
        this.cal = cal;
        calculate();
        currentHp=getMaxHp();
    }

    public void calculate(){
        maxHp=cal.getHp(level);
        atkpower=cal.getAtk(level);
        healpower=cal.getHeal(level);


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
}
