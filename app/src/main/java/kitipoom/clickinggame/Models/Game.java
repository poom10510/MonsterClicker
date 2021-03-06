package kitipoom.clickinggame.Models;

import java.util.Observable;
import java.util.Random;

import kitipoom.clickinggame.Allies.Ally;
import kitipoom.clickinggame.Allies.Archer;
import kitipoom.clickinggame.Allies.Caster;
import kitipoom.clickinggame.Allies.Warrior;
import kitipoom.clickinggame.Calculators.Calculator;
import kitipoom.clickinggame.Calculators.Enemycalculator;
import kitipoom.clickinggame.Keyplay.Enemy;
import kitipoom.clickinggame.Keyplay.Player;
import kitipoom.clickinggame.Memento.Memento;

public class Game extends Observable {

    private static Game instance;
    private Player player;
    private Enemy enemy;
    private UpLevel upLevel;

    private Ally warrior, caster, archer;

    private Random random = new Random();

    private boolean stun = false;
    private boolean isPowerBoost = false;
    private int floor, count, boost_count;

    private Calculator enemyCalculataor;

    private Game() {
        upLevel = new UpLevel();
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
            setChanged();
            notifyObservers("monsterDead");
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

    public void playerLossMoney(int money){
        this.player.lossMoney(money);
        setChanged();
        notifyObservers("lossMoney");
    }

    public int getPlayerMoney(){
        return this.player.getMoney();
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
        return (random.nextInt(100) + 1) <= archer.getStun();
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

    public UpLevel getLvu() {
        return upLevel;
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

    public Memento saveState() {
        return new GameMemento(floor, count);
    }

    public void loadState(Memento memento) {
        if (memento == null) return;
        if (memento.getClass() != GameMemento.class) return;
        GameMemento gameMemento = (GameMemento) memento;
        this.floor = gameMemento.floor;
        this.count = gameMemento.count;
        setEnemy();
    }

    public static class GameMemento extends Memento {
        private int floor, count;

        private GameMemento(int floor, int count) {
            super("Game");
            this.floor = floor;
            this.count = count;
        }

    }
}
