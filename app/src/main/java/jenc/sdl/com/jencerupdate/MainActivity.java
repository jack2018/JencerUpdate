package jenc.sdl.com.jencerupdate;

import android.Manifest;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Xml;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ScreenUtils;
import com.google.gson.JsonArray;
import com.gyf.immersionbar.ImmersionBar;
import com.sdl.updates.ConstomDialog;
import com.sdl.updates.CustomPopupWindow;
import com.sdl.updates.LoaddingDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import jenc.sdl.com.jencerupdate.GetDa.AppGetDataManager;
import jenc.sdl.com.jencerupdate.GetDa.GetDateAPI;
import jenc.sdl.com.jencerupdate.GetDa.KeyPoint;

public class MainActivity extends AppCompatActivity implements GetDateAPI {
    ConstomDialog constomDialog;
    TextView te;
    StringBuilder stringBuilder;
    List<KeyPoint> keyPoints;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
//        localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS );
        setContentView(R.layout.activity_main);
        te = findViewById(R.id.he);
        stringBuilder = new StringBuilder();
        try {
            keyPoints = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(stringBuilder.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
               JSONObject jsonObject = jsonArray.getJSONObject(i);
              int x= jsonObject.getInt("x");
                int y=  jsonObject.getInt("y");
              String value =  jsonObject.getString("value");
                keyPoints.add(new KeyPoint(x,y,value));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
//                ConstomDialog.Buider buider = new ConstomDialog.Buider(MainActivity.this);
//                constomDialog = buider
//                        .setMessage("内容hasndasdh")
//                        .setFullScreen(false)
//                        .setTouchAble(true)
////                        .setViewId(R.layout.se)
//                        .setSpecialIdsAndOnclick(new int[]{R.id.tv_num}, new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                constomDialog.dismiss();
//                            }
//                        })
////                        .setwidth(ScreenUtils.getScreenWidth())
////                        .setheight(ScreenUtils.getScreenHeight())
//                        .setTitle("标题")
//                        .setAnimalId(R.style.pop_animation1)
//                        .setSingle(true)
//                        .setshowAnimal(false)
//                        .setSureText("取消",null)
//                        .setCancleText("确定",null)
//                        .setSingleText("确定",null)
//                        .build();
//                        constomDialog.show();
                LoaddingDialog loaddingDialog = new LoaddingDialog(MainActivity.this);
                loaddingDialog.setJupheight(80);
                loaddingDialog.setButtonLength(10);
                loaddingDialog.setRade(10);
                loaddingDialog.show();
            }
        });
    }

    //异或校验
    public String checkcode_0007(int[] sl){
//        int[] a = new int[] { (int) 0XAA, (int) 0X04, (int) 0X31,
//                (int) 0X01};
        int ChkSum = 0;
        for (int i = 0; i <sl.length ; i++) {
            ChkSum = (int) (ChkSum ^ sl[i]);
        }
      String s =  Integer.toHexString(ChkSum);
        return s;
    }

    @Override
    public void getWeight(final String scaleWeight, String weight, boolean isStale) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int x = Integer.parseInt(scaleWeight.substring(4,5),16) ;
                int y = Integer.parseInt(scaleWeight.substring(5,6),16) ;
                for (int i = 0; i < keyPoints.size(); i++) {
                    if(keyPoints.get(i).getX()==x&&keyPoints.get(i).getY()==y){
                        te.setText(keyPoints.get(i).getValue());
                        break;
                    }
                }
            }
        });
    }

    @Override
    public void DivOpenSuccess() {

    }

    @Override
    public void zeroSetingSuc() {

    }

    @Override
    public void zeroSetingFail() {

    }

    @Override
    public void ScaleSuc() {

    }

    @Override
    public void ScaleFail() {

    }

    @Override
    public void DivOpenFail() {

    }

    @Override
    public void getOtherdate(byte[] tys, int size) {

    }
}
