package jenc.sdl.com.jencerupdate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusSecondActivity extends AppCompatActivity {
    Button btn_jup;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
//        EventBus.getDefault().register(this);
        btn_jup = findViewById(R.id.btn_jup);

        EventBus.getDefault().register(EventBusSecondActivity.this);
        btn_jup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post("去你妈的后台");
                finish();
            }
        });
    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void asd(String s){
//        String s =EventBus.getDefault().getStickyEvent(String.class);
        btn_jup.setText(s);
    }
}
