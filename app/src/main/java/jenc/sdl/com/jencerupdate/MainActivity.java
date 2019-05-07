package jenc.sdl.com.jencerupdate;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sdl.updates.ConstomDialog;

public class MainActivity extends AppCompatActivity {
    ConstomDialog constomDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.he).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConstomDialog.Buider buider = new ConstomDialog.Buider(MainActivity.this);
                constomDialog = buider
                        .setMessage("内容")
                        .setTitle("标题")
                        .setSingle(false)
                        .setSureText("",null)
                        .setCancleText("",null)
                        .setSingleText("确定",null)
                        .build();
                constomDialog.show();
            }
        });
    }
}
