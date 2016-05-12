package kitipoom.clickinggame;

/**
 * Created by kitipoom on 11/5/2559.
 */
public class Enermy {
    private int level;
    private int maxHp;
    private int currentHp;
    private int atkpower;
    public Enermy(int level){
        this.level=level;
    }
    public void levelUp(){

    }
    public void calculate(){

    }

    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
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
}
