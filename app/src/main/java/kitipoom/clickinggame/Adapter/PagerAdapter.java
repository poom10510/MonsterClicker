package kitipoom.clickinggame.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import kitipoom.clickinggame.Item.ItemFragment;
import kitipoom.clickinggame.UpgradeFragment.ArcherUpgrade;
import kitipoom.clickinggame.UpgradeFragment.CasterUpgrade;
import kitipoom.clickinggame.UpgradeFragment.HeroUpgrade;
import kitipoom.clickinggame.UpgradeFragment.WarriorUpgrade;

/**
 * Created by พศิน on 28/5/2559.
 */
public class PagerAdapter extends FragmentStatePagerAdapter{
    int numTab;

    public PagerAdapter(FragmentManager fm,int numTab) {
        super(fm);
        this.numTab = numTab;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                HeroUpgrade tabhero_upgrade = new HeroUpgrade();
                return tabhero_upgrade;
            case 1:
                WarriorUpgrade tabwarrior_upgrade = new WarriorUpgrade();
                return tabwarrior_upgrade;
            case 2:
                CasterUpgrade tabcaster_upgrade = new CasterUpgrade();
                return tabcaster_upgrade;
            case 3:
                ArcherUpgrade tabarcher_upgrade = new ArcherUpgrade();
                return tabarcher_upgrade;
            case 4:
                ItemFragment itemFragment = new ItemFragment();
                return itemFragment;

            default:return null;
        }
    }

    @Override
    public int getCount() {
        return numTab;
    }
}
