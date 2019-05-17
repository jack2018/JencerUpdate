package jenc.sdl.com.jencerupdate;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.blankj.utilcode.util.ScreenUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.sdl.updates.ConstomDialog;
import com.sdl.updates.CustomPopupWindow;

public class MainActivity extends AppCompatActivity {
    ConstomDialog constomDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
//        localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS );
        setContentView(R.layout.activity_main);

        ImmersionBar.with(this)
                .transparentStatusBar()
                .statusBarColor(R.color.colorTransgray)

//                .autoDarkModeEnable(false)
//                .flymeOSStatusBarFontColor(R.color.colorPrimary)
//                .statusBarDarkFont(true, 1.0f)
//                .statusBarAlpha(0.5f)
                .init();

        findViewById(R.id.he).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                ConstomDialog.Buider buider = new ConstomDialog.Buider(MainActivity.this);
                constomDialog = buider
                        .setMessage("内容hasndasdh")
                        .setFullScreen(false)
                        .setTouchAble(true)
                        .setViewId(R.layout.se)
                        .setSpecialIdsAndOnclick(new int[]{R.id.tv_num}, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                constomDialog.dismiss();
                            }
                        })
                        .setwidth(ScreenUtils.getScreenWidth())
                        .setheight(ScreenUtils.getScreenHeight())
                        .setTitle("标题")
                        .setAnimalId(R.style.pop_animation1)
                        .setSingle(true)
                        .setshowAnimal(false)
                        .setSureText("取消",null)
                        .setCancleText("确定",null)
                        .setSingleText("确定",null)
                        .build();
                        constomDialog.show();


//                CustomPopupWindow.Builder builder = new CustomPopupWindow.Builder(MainActivity.this);
//                CustomPopupWindow customPopupWindow =  builder.widthdp(200).heightdp(200).animStyle(R.style.pop_animation1).cancelTouchout(true).view(R.layout.dialog_text).build();
////                CustomPopupWindow customPopupWindow =  builder.widthdp(200).heightdp(200).cancelTouchout(true).view(R.layout.dialog_text).build();
//// customPopupWindow.showAtLocation(findViewById(R.id.he),Gravity.BOTTOM,0,0);
//                customPopupWindow.showAsDropDown(findViewById(R.id.he),000,0);
            }
        });
    }
}
