package kitipoom.clickinggame;

/**
 * Created by kitipoom on 11/5/2559.
 */
public class Player {
    private int level;
    private int maxHp;
    private int currentHp;
    private int atkpower;
    private int money;
    public Player(int level){
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
