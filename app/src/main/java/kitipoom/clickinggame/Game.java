package kitipoom.clickinggame;
import java.util.Observable;
import java.util.Random;

import kitipoom.clickinggame.Ally.Ally;
import kitipoom.clickinggame.Ally.Archer;
import kitipoom.clickinggame.Ally.Caster;
import kitipoom.clickinggame.Ally.Warrior;
import kitipoom.clickinggame.Calculator.Calculator;
import kitipoom.clickinggame.Calculator.Enemycalculator;
import kitipoom.clickinggame.Keyplay.Enemy;
import kitipoom.clickinggame.Keyplay.Player;
import kitipoom.clickinggame.Memento.Memento;

/**
 * Created by kitipoom on 11/5/2559.
 */
public class Game extends Observable{

    private static Game instance;
    private Player player;
    private Enemy enemy;
    private LevelUp levelup;

    private Ally warrior,caster,archer;

    private Random random = new Random();

    private boolean stun = false;
    private boolean isPowerBoost = false;
    private int floor, count, boost_count;

    private Calculator enemyCalculataor;

    private Game() {
        levelup = new LevelUp();
        floor = 1;
        count = 1;
        enemyCalculataor = new Enemycalculator();
    }

    public static Game getInstance() {
        if (instance == null) instance = new Game();
        return instance;
    }

    public void newGame() {
        startGame(floor);
    }

    public void checkEnemyDead() {
        if (enemyIsDead()) {
            player.receiveMoney(enemyCalculataor.getCost(enemy.getLevel()));
            if (count == 6) {
                floor++;
                count = 0;
            }
            count++;
            setEnemy();
        }
    }

    private void setEnemy() {
        enemy.setLevel(floor);
    }

    public void setEnemyDecrease() {
        if (floor > 1) {
            floor--;
        }
        player.setCurrentHp(player.getMaxHp());
        enemy.setLevel(floor);
        enemy.setCurrentHp(enemy.getCurrentHp());

        count = 1;
    }

    public boolean enemyIsDead() {
        return enemy.getCurrentHp() <= 0;

    }

    public boolean playerIsDead() {
        return player.getCurrentHp() <= 0;

    }

    public int getCount() {
        return count;
    }

    public void startGame(int level_enemy) {
        player = new Player();
        enemy = new Enemy(level_enemy);
        warrior = new Warrior();
        caster = new Caster();
        archer = new Archer();
    }

    public void setEnemyDamage() {
        enemy.attacked(player.getAtkpower());
        setChanged();
        notifyObservers();
        checkBoostPower();
    }

    public void checkBoostPower() {
        if (isPowerBoost) {
            boost_count++;
        }
        if (boost_count > 100) {
            isPowerBoost = false;
            toNormalPower();
            boost_count = 0;
        }
    }

    public void playerHeal() {
        player.Healyourself(player.getHealpower());
    }

    public void setStun(boolean stun) {
        this.stun = stun;
    }

    public boolean getStun() {
        return stun;
    }

    public void archerAttack() {
        archer.Action(player, enemy);
        stun = checkStun();
    }

    public void casterAttack() {
        caster.Action(player, enemy);
    }

    public void warriorAttack() {
        warrior.Action(player, enemy);
    }

    public void enemyTurn() {
        player.attacked(enemy.getAtkpower() - (int) (enemy.getAtkpower() * (warrior.getDefend() / 100.0)));
        setChanged();
        notifyObservers();
    }

    public boolean checkStun() {
        int index = random.nextInt(100) + 1;
        return index <= archer.getStun();
    }

    public void boostPower() {
        isPowerBoost = true;
        archer.setPower(archer.getPower() * 2);
        warrior.setPower(warrior.getPower() * 2);
        caster.setPower(caster.getPower() * 2);
        player.setAtkpower(player.getAtkpower() * 2);
        player.setHealpower(player.getHealpower() * 2);
    }

    public void toNormalPower() {
        archer.calculate();
        warrior.calculate();
        caster.calculate();
        player.calculate();
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public Player getPlayer() {
        return player;
    }

    public LevelUp getLvu() {
        return levelup;
    }

    public Ally getWarrior() {
        return warrior;
    }

    public Ally getArcher() {
        return archer;
    }

    public Ally getCaster() {
        return caster;
    }

    public Memento saveState(){
        return  new GameMemento(floor,count);
    }

    public void loadState(Memento memento){
        if(memento == null)return;
        if(memento.getClass() != GameMemento.class)return;
        GameMemento gameMemento = (GameMemento)memento;
        this.floor = gameMemento.floor;
        this.count = gameMemento.count;
        setEnemy();
    }

    public static class GameMemento extends Memento{
        private int floor,count;

        private GameMemento(int floor,int count) {
            super("Game");
            this.floor = floor;
            this.count = count;
        }

    }
}
