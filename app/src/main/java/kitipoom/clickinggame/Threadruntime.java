package kitipoom.clickinggame;

import android.util.Log;

/**
 * Created by kitipoom on 4/5/2559.
 */
public class Threadruntime extends Thread{

    private MainActivity main;
    private Boolean stop=false;

    public Threadruntime(MainActivity main){
        this.main=main;
    }
    public void requestStop() {
        this.stop = !this.stop;
    }

    @Override
    public void run() {
        while(true) {
            try {
                //sleep((long)1000); //หยุดการทำงาน 1 วินาที
                sleep((long)1000);
            }
            catch(InterruptedException e) {
                Log.e("log_thread", "++++++++++++++++++++++++++++Error Thread : " + e.toString());
            }
            if(this.stop) {

            }
            else {
                this.main.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        main.updatetime();
                    }
                });
            }

        }
    }

    public Boolean getStop() {
        return stop;
    }
}
