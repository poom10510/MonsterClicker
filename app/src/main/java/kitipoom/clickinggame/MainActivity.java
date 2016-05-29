package kitipoom.clickinggame;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private TextView moneybar, enhp,plhp;
    private AbsoluteLayout attackLayout;
    private RelativeLayout healLayout;
    protected ImageView enpic,hero1pic,hero2pic,hero3pic;
    private Threadruntime threadmonster,threadarcher,threadcaster,threadwarrior;
    Game game;
    Random random ;
    boolean checkenermydead=false;
    int i = 0;
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
        game = new Game(this);
        game.newGame();
        enpic =(ImageView)findViewById(R.id.Enermyimage);
        enpic.setImageResource(R.drawable.dragon1);
        hero1pic =(ImageView)findViewById(R.id.Hero1image);
        hero1pic.setImageResource(R.drawable.warrior1);
        hero2pic =(ImageView)findViewById(R.id.Hero2image);
        hero2pic.setImageResource(R.drawable.caster1);
        hero3pic =(ImageView)findViewById(R.id.Hero3image);
        hero3pic.setImageResource(R.drawable.archer1);
        moneybar = (TextView)findViewById(R.id.moneytab);
        moneybar.setText("M: " + game.getPlayer().getMoney() + "");
        enhp = (TextView)findViewById(R.id.enermyhp);
        enhp.setText("Lv. "+game.getEnermy().getLevel()+" HP: " + game.getEnermy().getCurrentHp() + "/"+game.getEnermy().getMaxHp() );
        plhp = (TextView)findViewById(R.id.playerhp);
        plhp.setText("Lv. "+game.getPlayer().getLevel()+" HP: "+game.getPlayer().getCurrentHp()+"/"+game.getPlayer().getMaxHp());

        healLayout = (RelativeLayout) findViewById(R.id.HealLayout);
        healLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.playerheal();
                plhp.setText("Lv. "+game.getPlayer().getLevel()+" HP: "+game.getPlayer().getCurrentHp()+"/"+game.getPlayer().getMaxHp());
            }
        });
        attackLayout = (AbsoluteLayout) findViewById(R.id.AttackLayout);
        attackLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkenermydead) {
                    game.setEnermydamage();
                    moneybar.setText("M: " + game.getPlayer().getMoney() + "");
                    enhp.setText("Lv. " + game.getEnermy().getLevel() + " HP: " + game.getEnermy().getCurrentHp() + "/" + game.getEnermy().getMaxHp());
                }
            }
        });

        //Upgrade Tab
        TabLayout tabLayout = (TabLayout)findViewById(R.id.upgradeTab);
        tabLayout.addTab(tabLayout.newTab().setText("Hero"));
        tabLayout.addTab(tabLayout.newTab().setText("Warrior"));
        tabLayout.addTab(tabLayout.newTab().setText("Caster"));
        tabLayout.addTab(tabLayout.newTab().setText("Archer"));

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

        threadmonster = new Threadruntime(this,1000,"monster");
        threadarcher = new Threadruntime(this,1000,"archer");
        threadcaster = new Threadruntime(this,1000,"caster");
        threadwarrior = new Threadruntime(this,1000,"warrior");
        threadmonster.start();
        threadwarrior.start();
        threadcaster.start();
        threadarcher.start();

    }
    public void updatetime(String name){
        if(name=="monster") {
            game.Allyturn();
            moneybar.setText("M: " + game.getPlayer().getMoney() + "");
            enhp.setText("Lv. " + game.getEnermy().getLevel() + " HP: " + game.getEnermy().getCurrentHp() + "/" + game.getEnermy().getMaxHp());
            game.Enermyturn();
            plhp.setText("Lv. " + game.getPlayer().getLevel() + " HP: " + game.getPlayer().getCurrentHp() + "/" + game.getPlayer().getMaxHp());
        }
        else if(name=="archer"){
            enhp.setText("Lv. " + game.getEnermy().getLevel() + " HP: " + game.getEnermy().getCurrentHp() + "/" + game.getEnermy().getMaxHp());
            plhp.setText("Lv. " + game.getPlayer().getLevel() + " HP: " + game.getPlayer().getCurrentHp() + "/" + game.getPlayer().getMaxHp());
            game.archerAttack();
            enhp.setText("Lv. " + game.getEnermy().getLevel() + " HP: " + game.getEnermy().getCurrentHp() + "/" + game.getEnermy().getMaxHp());
            plhp.setText("Lv. " + game.getPlayer().getLevel() + " HP: " + game.getPlayer().getCurrentHp() + "/" + game.getPlayer().getMaxHp());
        }
        else if(name=="caster"){
            enhp.setText("Lv. " + game.getEnermy().getLevel() + " HP: " + game.getEnermy().getCurrentHp() + "/" + game.getEnermy().getMaxHp());
            plhp.setText("Lv. " + game.getPlayer().getLevel() + " HP: " + game.getPlayer().getCurrentHp() + "/" + game.getPlayer().getMaxHp());
            game.mageAttack();
            enhp.setText("Lv. " + game.getEnermy().getLevel() + " HP: " + game.getEnermy().getCurrentHp() + "/" + game.getEnermy().getMaxHp());
            plhp.setText("Lv. " + game.getPlayer().getLevel() + " HP: " + game.getPlayer().getCurrentHp() + "/" + game.getPlayer().getMaxHp());
        }
        else if(name=="warrior"){
            enhp.setText("Lv. " + game.getEnermy().getLevel() + " HP: " + game.getEnermy().getCurrentHp() + "/" + game.getEnermy().getMaxHp());
            plhp.setText("Lv. " + game.getPlayer().getLevel() + " HP: " + game.getPlayer().getCurrentHp() + "/" + game.getPlayer().getMaxHp());
            game.warriorAttack();
            enhp.setText("Lv. " + game.getEnermy().getLevel() + " HP: " + game.getEnermy().getCurrentHp() + "/" + game.getEnermy().getMaxHp());
            plhp.setText("Lv. " + game.getPlayer().getLevel() + " HP: " + game.getPlayer().getCurrentHp() + "/" + game.getPlayer().getMaxHp());
        }
        if(game.EnisDead()){
            int index = game.getCount();
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
        game.checkEnermydead();

    }
}
