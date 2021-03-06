package kitipoom.clickinggame.Activity;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

import java.util.Observable;
import java.util.Observer;

import kitipoom.clickinggame.Animation.AnimationIterator;
import kitipoom.clickinggame.Calculators.ReadFile;
import kitipoom.clickinggame.Calculators.WriteFile;
import kitipoom.clickinggame.Models.Game;
import kitipoom.clickinggame.R;
import kitipoom.clickinggame.Thread.Threadruntime;


public class MainActivity extends AppCompatActivity implements Observer {

    private TextView moneybar, enhp, plhp, enemyLv, enemynum, attackdamage, healdamage;
    private RelativeLayout attackLayout;
    private RelativeLayout healLayout;
    protected ImageView enpic, hero1pic, hero2pic, hero3pic;
    private Threadruntime threadmonster, threadarcher, threadcaster, threadwarrior;
    private RoundCornerProgressBar playerHPbar, enemyHPbar;
    Game game;
    boolean checkenemydead = false;
    int monster = 0;
    int[] picAlly;
    int[] picMons;
    private AnimationIterator iteratorWarrior, iteratorCasterAttack, iteratorCasterHeal, iteratorArcher;
    private AnimationIterator iteratorDragon, iteratorBall, iteratorElectri, iteratorRabbit, iteratorFish, iteratorIce;
    private MediaPlayer tabSound,healSound,bgSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
    }

    public void init() {
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        game = Game.getInstance();
        game.newGame();
        loadGame();
        game.addObserver(this);

        enpic = (ImageView) findViewById(R.id.Enermyimage);
        hero1pic = (ImageView) findViewById(R.id.Hero1image);
        hero1pic.setImageResource(R.drawable.warrior1);
        hero2pic = (ImageView) findViewById(R.id.Hero2image);
        hero2pic.setImageResource(R.drawable.caster1);
        hero3pic = (ImageView) findViewById(R.id.Hero3image);
        hero3pic.setImageResource(R.drawable.archer1);

        moneybar = (TextView) findViewById(R.id.moneytab);
        moneybar.setText(game.getPlayer().getMoney() + "");
        enhp = (TextView) findViewById(R.id.enermyhp);
        enhp.setText(game.getEnemy().getCurrentHp() + "/" + game.getEnemy().getMaxHp());
        plhp = (TextView) findViewById(R.id.playerhp);
        plhp.setText(game.getPlayer().getCurrentHp() + "/" + game.getPlayer().getMaxHp());
        enemyLv = (TextView) findViewById(R.id.enermylevel);
        enemyLv.setText("LV " + game.getEnemy().getLevel());

        healLayout = (RelativeLayout) findViewById(R.id.HealLayout);
        healLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.playerHeal();
                healSound.start();
                plhp.setText(game.getPlayer().getCurrentHp() + "/" + game.getPlayer().getMaxHp());
                setHPBar();
                healdamage.setText("+ " + game.getPlayer().getHealpower());
            }
        });
        attackLayout = (RelativeLayout) findViewById(R.id.AttackLayout);
        attackLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkenemydead) {
                    game.setEnemyDamage();
                    tabSound.start();
                    moneybar.setText(game.getPlayer().getMoney() + "");
                    enhp.setText(game.getEnemy().getCurrentHp() + "/" + game.getEnemy().getMaxHp());
                    setHPBar();
                    attackdamage.setText("- " + game.getPlayer().getAtkpower() + "");
                }
            }
        });

        enemynum = (TextView) findViewById(R.id.enemynum);
        enemynum.setText(game.getCount() + "/6");

        attackdamage = (TextView) findViewById(R.id.attackdamage);
        healdamage = (TextView) findViewById(R.id.healdamage);

        attackdamage.setText("");
        healdamage.setText("");

        //Upgrade Tab
        TabLayout tabLayout = (TabLayout) findViewById(R.id.upgradeTab);
        tabLayout.addTab(tabLayout.newTab().setText("Hero"));
        tabLayout.addTab(tabLayout.newTab().setText("Warrior"));
        tabLayout.addTab(tabLayout.newTab().setText("Caster"));
        tabLayout.addTab(tabLayout.newTab().setText("Archer"));
        tabLayout.addTab(tabLayout.newTab().setText("Item"));

        final ViewPager viewPager = (ViewPager) findViewById(R.id.upgradePager);
        final PagerAdapter pagerAdapter = new kitipoom.clickinggame.Adapter.PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        /* HP_Bar*/
        //Player_HP
        playerHPbar = (RoundCornerProgressBar) findViewById(R.id.playerHP_bar);
        playerHPbar.setProgressColor(Color.parseColor("#FF0000"));
        playerHPbar.setProgressBackgroundColor(Color.parseColor("#33000000"));
        //Enemy_HP
        enemyHPbar = (RoundCornerProgressBar) findViewById(R.id.enemyHP_bar);
        enemyHPbar.setProgressBackgroundColor(Color.parseColor("#33000000"));
        enemyHPbar.setProgressColor(Color.parseColor("#FF0000"));


        /* THREAD */
        threadmonster = new Threadruntime(this, 1500, "monster");
        threadarcher = new Threadruntime(this, game.getArcher().getSpeed(), "archer");
        threadcaster = new Threadruntime(this, game.getCaster().getSpeed(), "caster");
        threadwarrior = new Threadruntime(this, game.getWarrior().getSpeed(), "warrior");
        threadmonster.start();
        threadwarrior.start();
        threadcaster.start();
        threadarcher.start();

        /*Create picture*/
        picAlly = new int[12];
        picMons = new int[29];
        int[] arrWarrior = new int[3];
        int[] arrCasterAttack = new int[2];
        int[] arrCasterHeal = new int[2];
        int[] arrArcher = new int[3];
        int[] arrDragon = new int[3];
        int[] arrBall = new int[3];
        int[] arrElectric = new int[3];
        int[] arrRabbit = new int[3];
        int[] arrFish = new int[3];
        int[] arrIce = new int[3];

        arrWarrior[0] = R.drawable.warrior1;
        arrWarrior[1] = R.drawable.warrior2;
        arrWarrior[2] = R.drawable.warrior3;

        arrCasterAttack[0] = R.drawable.caster1;
        arrCasterAttack[1] = R.drawable.caster2;

        arrCasterHeal[0] = R.drawable.caster1;
        arrCasterHeal[1] = R.drawable.caster3;

        arrArcher[0] = R.drawable.archer1;
        arrArcher[1] = R.drawable.archer2;
        arrArcher[2] = R.drawable.archer3;

        arrDragon[0] = R.drawable.dragon1;
        arrDragon[1] = R.drawable.dragon2;
        arrDragon[2] = R.drawable.dragon3;

        arrBall[0] = R.drawable.ball1;
        arrBall[1] = R.drawable.ball2;
        arrBall[2] = R.drawable.ball3;

        arrElectric[0] = R.drawable.electri1;
        arrElectric[1] = R.drawable.electri2;
        arrElectric[2] = R.drawable.electri3;

        arrRabbit[0] = R.drawable.rabbit1;
        arrRabbit[1] = R.drawable.rabbit2;
        arrRabbit[2] = R.drawable.rabbit3;

        arrFish[0] = R.drawable.fish1;
        arrFish[1] = R.drawable.fish2;
        arrFish[2] = R.drawable.fish3;

        arrIce[0] = R.drawable.ice1;
        arrIce[1] = R.drawable.ice2;
        arrIce[2] = R.drawable.ice3;


        iteratorWarrior = new AnimationIterator(arrWarrior);
        iteratorCasterAttack = new AnimationIterator(arrCasterAttack);
        iteratorCasterHeal = new AnimationIterator(arrCasterHeal);
        iteratorArcher = new AnimationIterator(arrArcher);
        iteratorDragon = new AnimationIterator(arrDragon);
        iteratorBall = new AnimationIterator(arrBall);
        iteratorElectri = new AnimationIterator(arrElectric);
        iteratorRabbit = new AnimationIterator(arrRabbit);
        iteratorFish = new AnimationIterator(arrFish);
        iteratorIce = new AnimationIterator(arrIce);

        /*Sound Effect*/
        tabSound = MediaPlayer.create(this,R.raw.attack);
        healSound = MediaPlayer.create(this,R.raw.heal);
        bgSound = MediaPlayer.create(this,R.raw.gamebg);
        bgSound.setLooping(true); // Set looping
        bgSound.setVolume(100,100);
        bgSound.start();

    }

    public void updatetime(String name) {

        if (name == "monster") {
            changePic(name);
            game.enemyTurn();
            if (game.playerIsDead()) {
                threadmonster.requestStop();
                threadarcher.requestStop();
                threadcaster.requestStop();
                threadwarrior.requestStop();
                game.setEnemyDecrease();
            }
        } else if (name == "archer") {
            changePic(name);
            game.archerAttack();
        } else if (name == "caster") {
            changePic(name);
            game.casterAttack();

        } else if (name == "warrior") {
            changePic(name);
            game.warriorAttack();
        }

        if (game.getStun()) {
            threadmonster.requestStop();
            game.setStun(false);
        }
        game.checkEnemyDead();
        attackdamage.setText("");
        healdamage.setText("");
    }

    public void setHPBar() {
        playerHPbar.setMax(game.getPlayer().getMaxHp());
        playerHPbar.setProgress(game.getPlayer().getCurrentHp());
        enemyHPbar.setMax(game.getEnemy().getMaxHp());
        enemyHPbar.setProgress(game.getEnemy().getCurrentHp());
        enhp.setText(game.getEnemy().getCurrentHp() + "/" + game.getEnemy().getMaxHp());
        plhp.setText(game.getPlayer().getCurrentHp() + "/" + game.getPlayer().getMaxHp());
    }

    public void changePic(String name) {
        if (name == "caster") {
            if (game.getCaster().getState().getSt() == 0) {
                hero2pic.setImageResource(iteratorCasterAttack.next());
            } else {
                hero2pic.setImageResource(iteratorCasterHeal.next());
            }
        } else if (name == "archer") {
            hero3pic.setImageResource(iteratorArcher.next());
        } else if (name == "warrior") {
            hero1pic.setImageResource(iteratorWarrior.next());
        } else if (name == "monster") {
            if (game.getCount() == 1) {
                enpic.setImageResource(iteratorDragon.next());
            } else if (game.getCount() == 2) {
                enpic.setImageResource(iteratorBall.next());
            } else if (game.getCount() == 3) {
                enpic.setImageResource(iteratorElectri.next());
            } else if (game.getCount() == 4) {
                enpic.setImageResource(iteratorRabbit.next());
            } else if (game.getCount() == 5) {
                enpic.setImageResource(iteratorFish.next());
            } else if (game.getCount() == 6) {
                enpic.setImageResource(iteratorIce.next());
            }
            monster++;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        WriteFile.write(game.saveState(), getApplicationContext());
        WriteFile.write(game.getPlayer().saveState(), getApplicationContext());
        WriteFile.write(game.getEnemy().saveState(), getApplicationContext());
        WriteFile.write(game.getArcher().saveState(), getApplicationContext());
        WriteFile.write(game.getCaster().saveState(), getApplicationContext());
        WriteFile.write(game.getWarrior().saveState(), getApplicationContext());
        bgSound.stop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        init();
    }

    private void loadGame() {
        game.loadState(ReadFile.read(getApplicationContext(), "Game"));
        game.getPlayer().loadState(ReadFile.read(getApplicationContext(), "Player"));
        game.getEnemy().loadState(ReadFile.read(getApplicationContext(), "Enemy"));
        game.getArcher().loadState(ReadFile.read(getApplicationContext(), "Archer"));
        game.getWarrior().loadState(ReadFile.read(getApplicationContext(), "Warrior"));
        game.getCaster().loadState(ReadFile.read(getApplicationContext(), "Caster"));
    }

    @Override
    public void update(Observable observable, Object data) {
        if(data == "monsterDead"){
            enemyLv.setText("LV " + game.getEnemy().getLevel());
            moneybar.setText(game.getPlayer().getMoney() + "");
            enemynum.setText(game.getCount() + "/6");
        }
        else if(data == "lossMoney"){
            moneybar.setText(game.getPlayer().getMoney() + "");
        }
        else{
            this.setHPBar();
        }

    }
}
