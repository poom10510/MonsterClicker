package kitipoom.clickinggame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private Button sharebtn,autobtn;
    Threadruntime aa;
    boolean o=false;
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    public void init(){
        tv = (TextView)findViewById(R.id.tv);
        tv.setText(i+"");
        sharebtn = (Button)findViewById(R.id.share);
        sharebtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                i++;
                tv.setText(i + "");
            }
        });
        autobtn=(Button)findViewById(R.id.auto);
        autobtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                aa.requestStop();
            }
        });
        aa= new Threadruntime(this);
        aa.start();
    }
    public void updatetime(){
        i+=2;
        tv.setText(i + "");
    }
}
