package jenc.sdl.com.jencerupdate;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.sdl.updates.ConstomDialog;
import com.sdl.updates.CustomPopupWindow;

public class MainActivity extends AppCompatActivity {
    ConstomDialog constomDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.he).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
//                ConstomDialog.Buider buider = new ConstomDialog.Buider(MainActivity.this);
//                constomDialog = buider
//                        .setMessage("内容hasndasdh")
//                        .setFullScreen(false)
//                        .setTouchAble(false)
////                        .setViewId(R.layout.dialog_text)
//                        .setSpecialIdsAndOnclick(new int[]{R.id.tv_num}, new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                constomDialog.dismiss();
//                            }
//                        })
////                        .setwidth(800)
////                        .setheight(200)
//                        .setTitle("标题")
//                        .setSingle(true)
//                        .setshowAnimal(true)
//                        .setSureText("取消",null)
//                        .setCancleText("确定",null)
//                        .setSingleText("确定",null)
//                        .build();
//                        constomDialog.show();


                CustomPopupWindow.Builder builder = new CustomPopupWindow.Builder(MainActivity.this);
                CustomPopupWindow customPopupWindow =  builder.widthdp(200).heightdp(200).animStyle(R.style.pop_animation).cancelTouchout(true).view(R.layout.dialog_text).build();
//                CustomPopupWindow customPopupWindow =  builder.widthdp(200).heightdp(200).cancelTouchout(true).view(R.layout.dialog_text).build();
// customPopupWindow.showAtLocation(findViewById(R.id.he),Gravity.BOTTOM,0,0);
                customPopupWindow.showAsUpRight(findViewById(R.id.he),100,0);
            }
        });
    }
}
