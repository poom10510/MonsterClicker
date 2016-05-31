package kitipoom.clickinggame.Memento;

import java.io.Serializable;

/**
 * Created by พศิน on 1/6/2559.
 */
public abstract class Memento implements Serializable {

    private String name;

    public Memento(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

}
