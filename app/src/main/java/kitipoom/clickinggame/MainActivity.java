package kitipoom.clickinggame;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import kitipoom.clickinggame.Adapter.PagerAdapter;


public class MainActivity extends AppCompatActivity {

    private TextView moneybar, enhp,plhp;
    private AbsoluteLayout attackLayout;
    private RelativeLayout healLayout;
    protected ImageView enpic,hero1pic,hero2pic,hero3pic;
    private Threadruntime aa;
    Game game;
    boolean o=false;
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
                plhp.setText("Lv. " + game.getPlayer().getLevel() + " HP: " + game.getPlayer().getCurrentHp() + "");
            }
        });
        attackLayout = (AbsoluteLayout) findViewById(R.id.AttackLayout);
        attackLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.setEnermydamage();
                moneybar.setText("M: " + game.getPlayer().getMoney() + "");
                enhp.setText("Lv. " + game.getEnermy().getLevel() + " HP: " + game.getEnermy().getCurrentHp() + "");
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

        aa= new Threadruntime(this);
        aa.start();

    }
    public void updatetime(){
        game.Allyturn();
        moneybar.setText("M: " + game.getPlayer().getMoney() + "");
        enhp.setText("Lv. "+game.getEnermy().getLevel()+" HP: " + game.getEnermy().getCurrentHp() + "/"+game.getEnermy().getMaxHp() );
        game.Enermyturn();
        plhp.setText("Lv. "+game.getPlayer().getLevel()+" HP: "+game.getPlayer().getCurrentHp()+"/"+game.getPlayer().getMaxHp());
    }
}
