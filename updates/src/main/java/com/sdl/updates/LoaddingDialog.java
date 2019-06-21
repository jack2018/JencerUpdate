package com.sdl.updates;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

/**
 * com.sdl.updates
 * 作者：she on 13:06
 * 邮箱：610184089@qq.com
 **/
public class LoaddingDialog extends Dialog {
    Context context;
    LoadingView loadview;
    int dialogwidth=200;//对话框宽度
    int dialogheight =200;//对话框高度
    int jupheight =-1;//物体跳起高度
    int rade=15;//物体半径
    int buttonLength=15;//底部影子长度
    int textSize =20;//字体大小

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void setRade(int rade) {
        this.rade = rade;
    }

    public void setButtonLength(int buttonLength) {
        this.buttonLength = buttonLength;
    }

    public int getDialogwidth() {
        return dialogwidth;
    }

    public void setDialogwidth(int dialogwidth) {
        this.dialogwidth = dialogwidth;
    }

    public int getDialogheight() {
        return dialogheight;
    }

    public void setDialogheight(int dialogheight) {
        this.dialogheight = dialogheight;
    }

    public int getJupheight() {
        return jupheight;
    }

    public void setJupheight(int jupheight) {
        this.jupheight = jupheight;
    }

    public LoaddingDialog(Context context) {
        super(context,R.style.default_dialog_style);
        this.context=context;
    }

    public LoaddingDialog(Context context, int themeResId) {
        super(context, R.style.default_dialog_style);
        this.context=context;
    }

    protected LoaddingDialog( Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setBackgroundResource(R.drawable.bg_custom_dialog);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dialogwidth,dialogheight);
        loadview = new LoadingView(context);
        linearLayout.addView(loadview,layoutParams);
        setContentView(linearLayout);
    }

    @Override
    public void show() {
        super.show();
        if(loadview!=null){
loadview.setJumpheight(jupheight);
loadview.setButtonlenth(buttonLength);
            loadview.setRad(rade);
            loadview.start();
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        loadview.onstop();
    }
}
