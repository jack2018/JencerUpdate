package com.sdl.updates;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ConstomDialog extends Dialog {
    private int default_view=R.layout.custom_dialog_layout;
    private Context context;
    private boolean touchable =false;
    private Buider buider;
    TextView title ;
    TextView message ;
    TextView cancel ;
    TextView sure ;
    TextView sincle ;
    LinearLayout lv_double_text;
    private View.OnClickListener dismisClick=new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            ConstomDialog.this.dismiss();
        }
    };
    private ConstomDialog(Buider buider) {
        super(buider.context,R.style.default_dialog_style);
        this.buider=buider;
    }
    private ConstomDialog(Buider buider, int themeResId) {
        super(buider.context,themeResId);
        this.buider=buider;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(buider.viewId==-1){
            setContentView(default_view);
        }else {
            setContentView(buider.viewId);
        }
        setCancelable(touchable);
        initview();
    }
    private void initview(){
           if(buider.viewId==-1){//使用原始界面
               title = findViewById(R.id.title);
                message = findViewById(R.id.message);
                cancel = findViewById(R.id.cancle);
                sure = findViewById(R.id.sure);
                sincle= findViewById(R.id.sincle);
               lv_double_text = findViewById(R.id.lv_double_text);
                if(buider.singletext){
                    lv_double_text.setVisibility(View.GONE);
                    sincle.setVisibility(View.VISIBLE);
                }else {
                    lv_double_text.setVisibility(View.VISIBLE);
                    sincle.setVisibility(View.GONE);
                }
                title.setText(buider.title);
                message.setText(buider.message);
                sure.setText(buider.sureText);
               cancel.setText(buider.cancleText);
               sincle.setText(buider.singleText);
               if(buider.sureClick==null){
                   buider.sureClick=dismisClick;
               }
               if(buider.cancleClick==null){
                   buider.cancleClick=dismisClick;
               }
               if(buider.singleClick==null){
                   buider.singleClick=dismisClick;
               }
               sure.setOnClickListener(buider.sureClick);
               cancel.setOnClickListener(buider.cancleClick);
               sincle.setOnClickListener(buider.singleClick);
           }else {
                         //自定义界面
           }
    }

    public static final class Buider {
        private Context context;
        private boolean touchable =false;
        private boolean singletext = false;
        private int viewId=-1;
        private int resStyle=-1;
        private String title="温馨提示";
        private String message="";
        private String sureText="确定";
        private String singleText="确定";
        private String cancleText="取消";
        private View.OnClickListener sureClick;
        private View.OnClickListener cancleClick;
        private View.OnClickListener singleClick;
        public Buider(Context context) {
            this.context = context;
        }
        public Buider setTouchAble(boolean touchable){
            this.touchable =touchable;
            return this;
        }
        public Buider setSingle(boolean touchable){
            this.singletext =touchable;
            return this;
        }
        public Buider setViewId(int viewId){
            this.viewId =viewId;
            return this;
        }

        public Buider setResStyle(int resStyle){
            this.resStyle =resStyle;
            return this;
        }

        public Buider setTitle(String touchable){
            this.title =touchable;
            return this;
        }
        public Buider setMessage(String touchable){
            this.message =touchable;
            return this;
        }
        public Buider setSingleText(String touchable, View.OnClickListener onClickListener){
            if(TextUtils.isEmpty(touchable)){
                touchable="确定";
            }
            this.singleText =touchable;
            singleClick = onClickListener;
            return this;
        }
        public Buider setSureText(String text,View.OnClickListener onClickListener){
            if(TextUtils.isEmpty(text)){
                text="确定";
            }
            this.sureText =text;
            sureClick=onClickListener;
            return this;
        }
        public Buider setCancleText(String text,View.OnClickListener onClickListener){
            if(TextUtils.isEmpty(text)){
                text="取消";
            }
            this.cancleText =text;
            cancleClick = onClickListener;
            return this;
        }
        public ConstomDialog build(){
           if(resStyle==-1){
               return new ConstomDialog(this);
           }else {
               return new ConstomDialog(this,resStyle);
           }
        }

    }
}
