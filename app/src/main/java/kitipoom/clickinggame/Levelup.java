package kitipoom.clickinggame;

import kitipoom.clickinggame.Ally.Ally;
import kitipoom.clickinggame.Keyplay.Keyplay;
import kitipoom.clickinggame.Keyplay.Player;

/**
 * Created by kitipoom on 27/5/2559.
 */
public class Levelup {
    public void levelup(Ally unit){

    }
    public void powerUp(Keyplay unit){
        unit.setAtkpowerlv(unit.getAtkpowerlv()+1);
    }
    public void hpUp(Keyplay unit){
        unit.setMaxHplv(unit.getMaxHplv() + 1);
    }
    public void healUp(Keyplay unit){
        unit.setHealpowerlv(unit.getHealpowerlv()+1);
    }
    public void criUp(Keyplay unit){
        unit.setCriticalpowerlv(unit.getCriticalpowerlv()+1);
    }

    public void powerUp(Ally unit){
        unit.setPowerLv(unit.getPowerLv()+1);
    }
    public void speedUp(Ally unit){
        unit.setSpeedLv(unit.getSpeedLv()+1);
    }
    public void healUp(Ally unit){
        unit.setHealLv(unit.getHealLv() + 1);
    }
    public void stunUp(Ally unit){
        unit.setStunLv(unit.getStunLv() + 1);
    }
    public void defendUp(Ally unit){
        unit.setDefendLv(unit.getDefendLv() + 1);
    }
}
