package jenc.sdl.com.jencerupdate;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusFirstActivity extends AppCompatActivity {
    Button btn_jup;
    TextView tv_begin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        EventBus.getDefault().register(this);
    btn_jup = findViewById(R.id.btn_jup);
    tv_begin = findViewById(R.id.tv_begin);
        btn_jup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky("去你妈后台");
                Intent intent = new Intent(EventBusFirstActivity.this,EventBusSecondActivity.class);
                startActivity(intent);
            }
        });

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String s){
        tv_begin.setText(s);
    }
}
