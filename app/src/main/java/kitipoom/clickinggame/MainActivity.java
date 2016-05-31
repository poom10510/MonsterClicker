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

    private TextView moneybar, enhp,plhp,enermyLv;
    private RelativeLayout attackLayout;
    private RelativeLayout healLayout;
    protected ImageView enpic,hero1pic,hero2pic,hero3pic;
    private Threadruntime threadmonster,threadarcher,threadcaster,threadwarrior;
    private RoundCornerProgressBar playerHPbar,enemyHPbar;
    Game game;
    Random random ;
    boolean checkenermydead=false;
    int ar = 0,ca=0,wa=0,mo=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        init();
    }
    public void init(){
        random = new Random();
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        game = Game.getInstance();
        game.newGame();
        //Game.getInstance().newGame();
        enpic =(ImageView)findViewById(R.id.Enermyimage);
        //enpic.setImageResource(R.drawable.dragon1);
        hero1pic =(ImageView)findViewById(R.id.Hero1image);
        hero1pic.setImageResource(R.drawable.warrior1);
        hero2pic =(ImageView)findViewById(R.id.Hero2image);
        hero2pic.setImageResource(R.drawable.caster1);
        hero3pic =(ImageView)findViewById(R.id.Hero3image);
        hero3pic.setImageResource(R.drawable.archer1);
        moneybar = (TextView)findViewById(R.id.moneytab);
        moneybar.setText(game.getMoney().getCash() + "");
        enhp = (TextView)findViewById(R.id.enermyhp);
        enhp.setText(game.getEnermy().getCurrentHp() + "/"+game.getEnermy().getMaxHp() );
        plhp = (TextView)findViewById(R.id.playerhp);
        plhp.setText(game.getPlayer().getCurrentHp()+"/"+game.getPlayer().getMaxHp());
        enermyLv = (TextView)findViewById(R.id.enermylevel);
        enermyLv.setText("LV "+game.getEnermy().getLevel());

        healLayout = (RelativeLayout) findViewById(R.id.HealLayout);
        healLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.playerheal();
                plhp.setText(game.getPlayer().getCurrentHp()+"/"+game.getPlayer().getMaxHp());
                setHPBar();
            }
        });
        attackLayout = (RelativeLayout) findViewById(R.id.AttackLayout);
        attackLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkenermydead) {
                    game.setEnermydamage();
                    moneybar.setText(game.getMoney().getCash() + "");
                    enhp.setText(game.getEnermy().getCurrentHp() + "/" + game.getEnermy().getMaxHp());
                    setHPBar();
                }
            }
        });

        //Upgrade Tab
        TabLayout tabLayout = (TabLayout)findViewById(R.id.upgradeTab);
        tabLayout.addTab(tabLayout.newTab().setText("Hero"));
        tabLayout.addTab(tabLayout.newTab().setText("Warrior"));
        tabLayout.addTab(tabLayout.newTab().setText("Caster"));
        tabLayout.addTab(tabLayout.newTab().setText("Archer"));
        tabLayout.addTab(tabLayout.newTab().setText("Item"));

        final ViewPager viewPager = (ViewPager)findViewById(R.id.upgradePager);
        final PagerAdapter pagerAdapter = new kitipoom.clickinggame.Adapter.PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
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
        playerHPbar = (RoundCornerProgressBar)findViewById(R.id.playerHP_bar);
        playerHPbar.setProgressColor(Color.parseColor("#FF0000"));
        playerHPbar.setProgressBackgroundColor(Color.parseColor("#33000000"));
        //Enemy_HP
        enemyHPbar = (RoundCornerProgressBar)findViewById(R.id.enemyHP_bar);
        enemyHPbar.setProgressBackgroundColor(Color.parseColor("#33000000"));
        enemyHPbar.setProgressColor(Color.parseColor("#FF0000"));


        /* THREAD */
        threadmonster = new Threadruntime(this,game.getArcher().getStun(),"monster");
        threadarcher = new Threadruntime(this,game.getArcher().getSpeed(),"archer");
        threadcaster = new Threadruntime(this,game.getCaster().getSpeed(),"caster");
        threadwarrior = new Threadruntime(this,game.getWarrior().getSpeed(),"warrior");
        threadmonster.start();
        threadwarrior.start();
        threadcaster.start();
        threadarcher.start();


    }
    public void updatetime(String name){

        if(name=="monster") {
            chpic(name);
            game.Enermyturn();
            if(game.playerIsDead()){
                threadmonster.requestStop();
                threadarcher.requestStop();
                threadcaster.requestStop();
                threadwarrior.requestStop();
                game.setEnermyDecrease();
                enpic.setImageResource(R.drawable.dragon1);
            }
        }
        else if(name=="archer"){
            chpic(name);
            game.archerAttack();
        }
        else if(name=="caster"){
           chpic(name);
            game.mageAttack();

        }
        else if(name=="warrior") {
            chpic(name);
            game.warriorAttack();
        }
        if(game.EnisDead()){
            int index = game.getCount();
            /*Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
            enpic.startAnimation(animation1);*/
            switch (index){
                case 1 : enpic.setImageResource(R.drawable.dragon1); break;
                case 2 : enpic.setImageResource(R.drawable.ball1); break;
                case 3 : enpic.setImageResource(R.drawable.electri1); break;
                case 4 : enpic.setImageResource(R.drawable.rabbit1); break;
                case 5 : enpic.setImageResource(R.drawable.fish1); break;
                case 6 : enpic.setImageResource(R.drawable.ice1); break;
                default: break;
            }
        }
        if (game.getStun()) {
            threadmonster.requestStop();
            game.setStun(false);
        }

        enermyLv.setText("LV "+game.getEnermy().getLevel());
        game.checkEnermydead();
        this.setHPBar();
        moneybar.setText(game.getMoney().getCash() + "");
        enhp.setText(game.getEnermy().getCurrentHp() + "/" + game.getEnermy().getMaxHp());
        plhp.setText(game.getPlayer().getCurrentHp() + "/" + game.getPlayer().getMaxHp());
    }

    public  void setHPBar(){
        playerHPbar.setMax(game.getPlayer().getMaxHp());
        playerHPbar.setProgress(game.getPlayer().getCurrentHp());
        enemyHPbar.setMax(game.getEnermy().getMaxHp());
        enemyHPbar.setProgress(game.getEnermy().getCurrentHp());
    }
    public void chpic(String name) {
        if(name=="caster"){
            if (ca % 2 == 0) {
                if(game.getCaster().getState().getSt()==0) {
                    hero2pic.setImageResource(R.drawable.caster2);
                }
                else{
                    hero2pic.setImageResource(R.drawable.caster3);
                }


            } else {
                hero2pic.setImageResource(R.drawable.caster1);
            }
            ca++;
        }
        else if(name=="archer"){
            if (ar % 2 == 0) {
                hero3pic.setImageResource(R.drawable.archer2);
            }
            else if(ar%3==0){
                hero3pic.setImageResource(R.drawable.archer3);
                ar=0;
            }
            else {
                hero3pic.setImageResource(R.drawable.archer1);
            }
            ar++;

        }
        else if(name=="warrior") {
            if (wa % 2 == 0) {
                hero1pic.setImageResource(R.drawable.warrior2);
            }
            else if(wa%3==0){
                hero1pic.setImageResource(R.drawable.warrior3);
                wa=0;
            }
            else {
                hero1pic.setImageResource(R.drawable.warrior1);
            }
            wa++;
        }
        else if(name=="monster"){
            if(game.getCount()==1){
                if (mo % 2 == 0) {
                    enpic.setImageResource(R.drawable.dragon1);
                }
                else if(mo%3==0){
                    enpic.setImageResource(R.drawable.dragon2);
                    mo=0;
                }
                else {
                    enpic.setImageResource(R.drawable.dragon3);
                }

            }
            else if(game.getCount()==2){
                if (mo % 2 == 0) {
                    enpic.setImageResource(R.drawable.ball1);
                }
                else if(mo%3==0){
                    enpic.setImageResource(R.drawable.ball2);
                    mo=0;
                }
                else {
                    enpic.setImageResource(R.drawable.ball3);
                }

            }
            mo++;
        }
    }
}
