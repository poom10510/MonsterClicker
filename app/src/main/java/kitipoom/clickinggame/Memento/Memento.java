package kitipoom.clickinggame.Memento;

import java.io.Serializable;

public abstract class Memento implements Serializable {

    private String name;

    public Memento(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
