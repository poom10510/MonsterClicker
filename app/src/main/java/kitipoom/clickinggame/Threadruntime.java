package kitipoom.clickinggame;

import android.util.Log;

/**
 * Created by kitipoom on 4/5/2559.
 */
public class Threadruntime extends Thread {

    private MainActivity main;
    private int speed;
    private String name;
    private Boolean stop = false;

    public Threadruntime(MainActivity main, int speed, String name) {
        this.main = main;
        this.speed = speed;
        this.name = name;
    }

    public void requestStop() {
        this.stop = !this.stop;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void run() {
        while (true) {
            try {
                //sleep((long)1000); //หยุดการทำงาน 1 วินาที
                sleep(speed);
            } catch (InterruptedException e) {
                Log.e("log_thread", "++++++++++++++++++++++++++++Error Thread : " + e.toString());
            }
            if (this.stop) {
                try {
                    sleep(1000);
                    this.stop = false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                this.main.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        main.updatetime(name);
                    }
                });
            }

        }
    }

    public Boolean getStop() {
        return stop;
    }
}
