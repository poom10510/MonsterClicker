package kitipoom.clickinggame.Calculator;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;

import kitipoom.clickinggame.Memento.Memento;

/**
 * Created by พศิน on 1/6/2559.
 */
public class ReadFile {

    public static Memento read(Context context,String name){
        Memento memento;

        try {
            FileInputStream fis = context.openFileInput(name+".txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            memento = (Memento)ois.readObject();
            return memento;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
