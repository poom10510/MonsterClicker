package kitipoom.clickinggame.Animation;

import java.util.Iterator;

public class AnimationIterator implements Iterator<Integer> {
    private int[] array = new int[3];
    private int count;

    public AnimationIterator(int[] array) {
        this.array = array;
        count = 0;
    }

    @Override
    public boolean hasNext() {
        if (array[count] == 0) return false;
        else return true;
    }

    @Override
    public Integer next() {
        return array[(count++) % array.length];
    }

    @Override
    public void remove() {

    }


}
