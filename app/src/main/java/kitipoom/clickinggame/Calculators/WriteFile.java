package kitipoom.clickinggame.Calculators;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import kitipoom.clickinggame.Memento.Memento;

public class WriteFile {
    public static void write(Memento memento, Context context) {

        try {
            FileOutputStream fos = context.openFileOutput(memento.getName() + ".txt", context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(memento);
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
