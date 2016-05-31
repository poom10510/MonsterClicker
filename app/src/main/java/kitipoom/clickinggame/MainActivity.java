package kitipoom.clickinggame;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private TextView moneybar, enhp, plhp, enermyLv, enemynum, attackdamage, healdamage;
    private TextView warriordamge,archerdamage,casterdamage,casterhealdamage;
    private RelativeLayout attackLayout;
    private RelativeLayout healLayout;
    protected ImageView enpic, hero1pic, hero2pic, hero3pic;
    private Threadruntime threadmonster, threadarcher, threadcaster, threadwarrior;
    private RoundCornerProgressBar playerHPbar, enemyHPbar;
    Game game;
    Random random;
    boolean checkenermydead = false;
    int ar = 0, ca = 0, wa = 0, mo = 0;
    int[] picAlly;
    int[] picMons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        init();
    }

    public void init() {
        random = new Random();
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        game = Game.getInstance();
        game.newGame();
        //Game.getInstance().newGame();
        enpic = (ImageView) findViewById(R.id.Enermyimage);
        //enpic.setImageResource(R.drawable.dragon1);
        hero1pic = (ImageView) findViewById(R.id.Hero1image);
        hero1pic.setImageResource(R.drawable.warrior1);
        hero2pic = (ImageView) findViewById(R.id.Hero2image);
        hero2pic.setImageResource(R.drawable.caster1);
        hero3pic = (ImageView) findViewById(R.id.Hero3image);
        hero3pic.setImageResource(R.drawable.archer1);
        moneybar = (TextView) findViewById(R.id.moneytab);
        moneybar.setText(game.getMoney().getCash() + "");
        enhp = (TextView) findViewById(R.id.enermyhp);
        enhp.setText(game.getEnermy().getCurrentHp() + "/" + game.getEnermy().getMaxHp());
        plhp = (TextView) findViewById(R.id.playerhp);
        plhp.setText(game.getPlayer().getCurrentHp() + "/" + game.getPlayer().getMaxHp());
        enermyLv = (TextView) findViewById(R.id.enermylevel);
        enermyLv.setText("LV " + game.getEnermy().getLevel());

        healLayout = (RelativeLayout) findViewById(R.id.HealLayout);
        healLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.playerheal();
                plhp.setText(game.getPlayer().getCurrentHp() + "/" + game.getPlayer().getMaxHp());
                setHPBar();
                healdamage.setText("+ "+game.getPlayer().getHealpower());
            }
        });
        attackLayout = (RelativeLayout) findViewById(R.id.AttackLayout);
        attackLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkenermydead) {
                    game.setEnermydamage();
                    moneybar.setText(game.getMoney().getCash() + "");
                    enhp.setText(game.getEnermy().getCurrentHp() + "/" + game.getEnermy().getMaxHp());
                    setHPBar();
                    attackdamage.setText("- "+game.getPlayer().getAtkpower()+"");
                }
            }
        });

        enemynum = (TextView)findViewById(R.id.enemynum);
        enemynum.setText(game.getCount()+"/6");

        attackdamage = (TextView)findViewById(R.id.attackdamage);
        healdamage = (TextView)findViewById(R.id.healdamage);
        warriordamge = (TextView)findViewById(R.id.warriordamage);
        archerdamage = (TextView)findViewById(R.id.archerdamage);
        casterdamage = (TextView)findViewById(R.id.casterdamage);
        casterhealdamage = (TextView)findViewById(R.id.casterhealdamage);

        attackdamage.setText("");
        healdamage.setText("");
        archerdamage.setText("");
        warriordamge.setText("");
        casterdamage.setText("");
        casterhealdamage.setText("");

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

        picAlly = new int[12];
        picMons = new int[29];
        /*Create picture*/
        picMons[1] = R.drawable.dragon1;
        picMons[2] = R.drawable.dragon2;
        picMons[3] = R.drawable.dragon3;

        picMons[4] = R.drawable.ball1;
        picMons[5] = R.drawable.ball2;
        picMons[6] = R.drawable.ball3;

        picMons[7] = R.drawable.electri1;
        picMons[8] = R.drawable.electri2;
        picMons[9] = R.drawable.electri3;

        picMons[10] = R.drawable.rabbit1;
        picMons[11] = R.drawable.rabbit2;
        picMons[12] = R.drawable.rabbit3;

        picMons[13] = R.drawable.fish1;
        picMons[14] = R.drawable.fish2;
        picMons[15] = R.drawable.fish3;

        picMons[16] = R.drawable.ice1;
        picMons[17] = R.drawable.ice2;
        picMons[18] = R.drawable.ice3;

        picAlly[1] = R.drawable.warrior1;
        picAlly[2] = R.drawable.warrior2;
        picAlly[3] = R.drawable.warrior3;

        picAlly[4] = R.drawable.caster1;
        picAlly[5] = R.drawable.caster2;
        picAlly[6] = R.drawable.caster3;

        picAlly[7] = R.drawable.archer1;
        picAlly[8] = R.drawable.archer2;
        picAlly[9] = R.drawable.archer3;

    }

    public void updatetime(String name) {

        if (name == "monster") {
            chpic(name);
            game.Enermyturn();
            if (game.playerIsDead()) {
                threadmonster.requestStop();
                threadarcher.requestStop();
                threadcaster.requestStop();
                threadwarrior.requestStop();
                game.setEnermyDecrease();
                enpic.setImageResource(picMons[1]);
            }
        } else if (name == "archer") {
            chpic(name);
            game.archerAttack();
        } else if (name == "caster") {
            chpic(name);
            game.mageAttack();

        } else if (name == "warrior") {
            chpic(name);
            game.warriorAttack();
        }
        if (game.EnisDead()) {
            int index = game.getCount();
//            Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
//            enpic.startAnimation(animation1);
            switch (index) {
                case 1:
                    enpic.setImageResource(picMons[1]);
                    break;
                case 2:
                    enpic.setImageResource(picMons[4]);
                    break;
                case 3:
                    enpic.setImageResource(picMons[7]);
                    break;
                case 4:
                    enpic.setImageResource(picMons[10]);
                    break;
                case 5:
                    enpic.setImageResource(picMons[13]);
                    break;
                case 6:
                    enpic.setImageResource(picMons[16]);
                    break;
                default:
                    break;
            }
        }
        if (game.getStun()) {
            threadmonster.requestStop();
            game.setStun(false);
        }

        enermyLv.setText("LV " + game.getEnermy().getLevel());
        game.checkEnermydead();
        this.setHPBar();
        moneybar.setText(game.getMoney().getCash() + "");
        enhp.setText(game.getEnermy().getCurrentHp() + "/" + game.getEnermy().getMaxHp());
        plhp.setText(game.getPlayer().getCurrentHp() + "/" + game.getPlayer().getMaxHp());
        enemynum.setText(game.getCount()+"/6");
        attackdamage.setText("");
        healdamage.setText("");
    }

    public void setHPBar() {
        playerHPbar.setMax(game.getPlayer().getMaxHp());
        playerHPbar.setProgress(game.getPlayer().getCurrentHp());
        enemyHPbar.setMax(game.getEnermy().getMaxHp());
        enemyHPbar.setProgress(game.getEnermy().getCurrentHp());
    }

    public void chpic(String name) {
        if (name == "caster") {
            if (ca % 2 == 0) {
                if (game.getCaster().getState().getSt() == 0) {
                    hero2pic.setImageResource(picAlly[5]);
                    casterdamage.setText("- "+game.getCaster().getPower());
                } else {
                    hero2pic.setImageResource(picAlly[6]);
                    casterhealdamage.setText("+ "+game.getCaster().getHeal());
                }


            } else {
                hero2pic.setImageResource(picAlly[4]);
                casterdamage.setText("");
                casterhealdamage.setText("");
            }
            ca++;
        } else if (name == "archer") {
            if (ar % 2 == 0) {
                hero3pic.setImageResource(picAlly[8]);
                archerdamage.setText("");
            } else if (ar % 3 == 0) {
                hero3pic.setImageResource(picAlly[9]);
                archerdamage.setText("- "+game.getArcher().getPower());
                ar = 0;
            } else {
                archerdamage.setText("");
                hero3pic.setImageResource(picAlly[7]);
            }
            ar++;

        } else if (name == "warrior") {
            if (wa % 2 == 0) {
                hero1pic.setImageResource(picAlly[2]);
                warriordamge.setText("");
            } else if (wa % 3 == 0) {
                hero1pic.setImageResource(picAlly[3]);
                wa = 0;
                warriordamge.setText("- "+game.getWarrior().getPower());

            } else {
                warriordamge.setText("");
                hero1pic.setImageResource(picAlly[1]);
            }
            wa++;
        } else if (name == "monster") {
            if (game.getCount() == 1) {
                if (mo % 2 == 0) {
                    enpic.setImageResource(picMons[2]);
                } else if (mo % 3 == 0) {
                    enpic.setImageResource(picMons[3]);
                    mo = 0;
                } else {
                    enpic.setImageResource(picMons[1]);
                }

            } else if (game.getCount() == 2) {
                if (mo % 2 == 0) {
                    enpic.setImageResource(picMons[5]);
                } else if (mo % 3 == 0) {
                    enpic.setImageResource(picMons[6]);
                    mo = 0;
                } else {
                    enpic.setImageResource(picMons[4]);
                }


            }
            if (game.getCount() == 3) {
                if (mo % 2 == 0) {
                    enpic.setImageResource(picMons[8]);
                } else if (mo % 3 == 0) {
                    enpic.setImageResource(picMons[9]);
                    mo = 0;
                } else {
                    enpic.setImageResource(picMons[7]);
                }

            }
            if (game.getCount() == 4) {
                if (mo % 2 == 0) {
                    enpic.setImageResource(picMons[11]);
                } else if (mo % 3 == 0) {
                    enpic.setImageResource(picMons[12]);
                    mo = 0;
                } else {
                    enpic.setImageResource(picMons[10]);
                }

            }
            if (game.getCount() == 5) {
                if (mo % 2 == 0) {
                    enpic.setImageResource(picMons[14]);
                } else if (mo % 3 == 0) {
                    enpic.setImageResource(picMons[15]);
                    mo = 0;
                } else {
                    enpic.setImageResource(picMons[13]);
                }

            }
            if (game.getCount() == 6) {
                if (mo % 2 == 0) {
                    enpic.setImageResource(picMons[17]);
                } else if (mo % 3 == 0) {
                    enpic.setImageResource(picMons[18]);
                    mo = 0;
                } else {
                    enpic.setImageResource(picMons[16]);
                }

            }
            mo++;
        }
    }
}
