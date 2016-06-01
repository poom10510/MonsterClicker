package kitipoom.clickinggame;

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

    @Override
    public void run() {
        while (true) {
            try {
                sleep(speed);
            } catch (InterruptedException e) {
               e.printStackTrace();
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

}
