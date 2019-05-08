package com.sdl.updates;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

public class CustomPopupWindow extends PopupWindow {
    Context context;
    Builder builder;
    private CustomPopupWindow(Builder builder) {
        super(builder.context);
        this.context = builder.context;
        this.builder =builder;
        builder.view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        setContentView(builder.view);
        setHeight(builder.height == 0?ViewGroup.LayoutParams.WRAP_CONTENT:builder.height);
        setWidth(builder.width == 0?ViewGroup.LayoutParams.WRAP_CONTENT:builder.width);
        if (builder.cancelTouchout) {
            setBackgroundDrawable(new ColorDrawable(0x00000000));//设置透明背景
            setOutsideTouchable(builder.cancelTouchout);//设置outside可点击
        }
        setFocusable(builder.isFocusable);
        setTouchable(builder.isTouchable);

        if(builder.animStyle != 0){
            setAnimationStyle(builder.animStyle);
        }
    }

    public static final class Builder {

        private Context context;
        private int height, width;
        private boolean cancelTouchout;
        private boolean isFocusable = true;
        private boolean isTouchable = true;
        private View view;
        private int animStyle;
//        private boolean isshadow=false;
        public Builder(Context context) {
            this.context = context;
        }

        public Builder view(int resView) {
            view = LayoutInflater.from(context).inflate(resView, null);
            return this;
        }
//        public Builder view(boolean isshadows) {
//            isshadow =isshadows;
//            return this;
//        }
        public Builder view(View resVew){
            view = resVew;
            return this;
        }

        public Builder heightpx(int val) {
            height = val;
            return this;
        }

        public Builder widthpx(int val) {
            width = val;
            return this;
        }

        public Builder heightdp(int val) {
            height = dip2px(context, val);
            return this;
        }

        public Builder widthdp(int val) {
            width = dip2px(context, val);
            return this;
        }

        public Builder heightDimenRes(int dimenRes) {
            height = context.getResources().getDimensionPixelOffset(dimenRes);
            return this;
        }

        public Builder widthDimenRes(int dimenRes) {
            width = context.getResources().getDimensionPixelOffset(dimenRes);
            return this;
        }

        public Builder cancelTouchout(boolean val) {
            cancelTouchout = val;
            return this;
        }

        public Builder isFocusable(boolean val) {
            isFocusable = val;
            return this;
        }

        public Builder isTouchable(boolean val) {
            isTouchable = val;
            return this;
        }

        public Builder animStyle(int val){
            animStyle = val;
            return this;
        }

        public Builder addViewOnclick(int viewRes, View.OnClickListener listener) {
            view.findViewById(viewRes).setOnClickListener(listener);
            return this;
        }


        public CustomPopupWindow build() {

            return new CustomPopupWindow(this);
        }
    }

    @Override
    public int getWidth() {
        return getContentView().getMeasuredWidth();
    }

    /**
     * PopuWindow在安卓7布局大小兼容问题的处理
     *  在android7.0上，如果不主动约束PopuWindow的大小，比如，设置布局大小为 MATCH_PARENT,那么PopuWindow会变得尽可能大，
     *  以至于 view下方无空间完全显示PopuWindow，而且view又无法向上滚动，此时PopuWindow会主动上移位置，直到可以显示完全。
     *　解决办法：主动约束PopuWindow的内容大小，重写showAsDropDown方法：
     * @param anchor
     */
    @Override
    public void showAsDropDown(View anchor) {
//        int[] location = new int[2];
//        anchor.getLocationOnScreen(location);
//        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        Display display = wm.getDefaultDisplay();
//        int screen_height = display.getHeight();//屏幕高度
//        int view_height = anchor.getHeight();
//         if(screen_height<location[1]+view_height+builder.height){
//             int dis = location[1]+view_height+builder.height-screen_height;
//             super.showAtLocation(anchor,Gravity.TOP,0,location[1]+view_height);
//             return;
//         }

        if (Build.VERSION.SDK_INT >= 24){
            Rect visibleFrame = new Rect();
            anchor.getGlobalVisibleRect(visibleFrame);
            int height = anchor.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
            setHeight(height);
        }
        super.showAsDropDown(anchor);
    }
    /*注意点Android 4.0导航栏和状态栏是都算进去的，6.0没算进去*/
    public void showAsDropDownleft(View anchor) {
        int[] location = new int[2];
        anchor.getLocationOnScreen(location);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int screen_height = display.getHeight();//屏幕高度
        int view_height = anchor.getHeight();
        int ls = (display.getWidth()-builder.width)/2-location[0];
        if(screen_height<location[1]+view_height+builder.height){
//            int dis = location[1]+view_height+builder.height-screen_height;
            super.showAtLocation(anchor,Gravity.TOP,-ls,location[1]+view_height);
            return;
        }
        if (Build.VERSION.SDK_INT >= 24){
            Rect visibleFrame = new Rect();
            anchor.getGlobalVisibleRect(visibleFrame);
            int height = anchor.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
            setHeight(height);
        }
        super.showAsDropDown(anchor);
    }
    /*注意点Android 4.0导航栏和状态栏是都算进去的，6.0没算进去*/
    public void showAsDropDownRight(View anchor,int offx,int offy) {
        int[] location = new int[2];
        anchor.getLocationOnScreen(location);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int screen_height = display.getHeight();//屏幕高度
        int view_height = anchor.getHeight();
        int view_width = anchor.getWidth();
        int lsx =(location[0]+view_width)-(display.getWidth()-builder.width)/2-builder.width+offx;
        int lsy = location[1]+view_height+offy;
        super.showAtLocation(anchor,Gravity.TOP,lsx,lsy);
    }
    /*注意点Android 4.0导航栏和状态栏是都算进去的，6.0没算进去*/
    public void showAsUpleft(View anchor,int offx,int offy) {
        int[] location = new int[2];
        anchor.getLocationOnScreen(location);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int screen_height = display.getHeight();//屏幕高度
        int view_height = anchor.getHeight();
        int ls = (display.getWidth()-builder.width)/2-location[0]+offx;
        super.showAtLocation(anchor,Gravity.TOP,-ls,location[1]-builder.height+offy);
    }

    /*注意点Android 4.0导航栏和状态栏是都算进去的，6.0没算进去*/
    public void showAsUpRight(View anchor,int offx,int offy) {
        int[] location = new int[2];
        anchor.getLocationOnScreen(location);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int screen_height = display.getHeight();//屏幕高度
        int view_height = anchor.getHeight();
        int view_width = anchor.getWidth();
        int lsx =(location[0]+view_width)-(display.getWidth()-builder.width)/2-builder.width+offx;
        int lsy = location[1]-builder.height+offy;
        super.showAtLocation(anchor,Gravity.TOP,lsx,lsy);
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect visibleFrame = new Rect();
            anchor.getGlobalVisibleRect(visibleFrame);
            int height = anchor.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
            setHeight(height);
        }
        super.showAsDropDown(anchor,xoff,yoff);
    }


    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


}
