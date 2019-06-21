package com.sdl.updates;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * com.sdl.updates
 * 作者：she on 16:38
 * 邮箱：610184089@qq.com
 **/
public class LoadingView extends View {
    int type=0;
    int buttonlenth=50;
    int deg =0;
    int rad=15;
    int jumpheight=-1;
    boolean showAnimal =true;
    int textSize=20;
    private static final int recType = 0;
    private static final int  cirleType =1;
    private static final int  tranceType=2;

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void setRad(int rad) {
        this.rad = rad;
    }

    public void setButtonlenth(int buttonlenth) {
        this.buttonlenth = buttonlenth;
    }

    public void setJumpheight(int jumpheight) {
        this.jumpheight = jumpheight;
    }

    public LoadingView(Context context) {
        super(context);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void start(){
        if(thread!=null)
        thread.start();
    }
    public void onstop(){
        showAnimal=false;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(jumpheight==-1)
        jumpheight= canvas.getHeight()/2-rad;
        canvas.drawColor(Color.TRANSPARENT);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        int wid = canvas.getWidth()/2;
//        canvas.drawLine(wid,0,wid,200,paint);
        Rect rect = new Rect(wid-rad,wid-rad,wid+rad,wid+rad);
        double b=(180-deg)/180.0;
            int is = -Math.abs((int) (b*jumpheight));
        canvas.save();
//        canvas.drawLine(0,wid+50, (float) Math.abs(200*b),wid+50,paint);
        drawButttomLine(canvas,b,rect);
        drawText(canvas,b,rect);
        canvas.translate(0,is);
        canvas.rotate(deg,rect.exactCenterX(),rect.centerY());
        switch (type){
            case recType:
                paintDrawRec(canvas,rect,paint);
                break;
            case cirleType:
                paintDrawCirle(canvas,rect,paint);
                break;
            case tranceType:
                paintDrawTrc(canvas,rect,paint);
                break;
        }

        canvas.restore();
    }
public void paintDrawRec(Canvas canvas,Rect rect,Paint paint){
        paint.setColor(Color.BLUE);
    canvas.drawRect(rect,paint);
}
public void paintDrawCirle(Canvas canvas,Rect rect,Paint paint){
        paint.setColor(Color.GREEN);
        canvas.drawCircle(rect.centerX(),rect.centerY(),rad,paint);
}
    public void paintDrawTrc(Canvas canvas,Rect rect,Paint paint){
        Path path = new Path();
        paint.setColor(Color.RED);
        path.moveTo(rect.centerX(),rect.top);
        path.lineTo(rect.left,rect.bottom);
        path.lineTo(rect.right,rect.bottom);
        path.close();
        canvas.drawPath(path,paint);
    }
    public void drawButttomLine(Canvas canvas,double rare,Rect rect){
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(2);
        paint.setColor(Color.GRAY);
        canvas.drawLine((float) (rect.centerX()-buttonlenth*rare/2.0),rect.bottom, (float) (rect.centerX()+buttonlenth*rare/2.0),rect.bottom,paint);
    }
    public void drawText(Canvas canvas,double rare,Rect rect){
        Rect rect1 = new Rect(0,rect.bottom+10,canvas.getWidth(),rect.bottom+50);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(textSize);
        paint.setColor(Color.BLACK);
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        int baseline = (rect1.bottom+rect1.top-fontMetrics.top-fontMetrics.bottom)/2;
        paint.setTextAlign(Paint.Align.CENTER);
        rare = Math.abs(rare);
        if(rare>0&&rare<0.3){
            canvas.drawText("请稍后.  ",rect1.centerX(),baseline,paint);
        }else if(rare>=0.3&&rare<0.6) {
            canvas.drawText("请稍后.. ",rect1.centerX(),baseline,paint);
        }else {
            canvas.drawText("请稍后...",rect1.centerX(),baseline,paint);
        }
    }
    Thread thread = new Thread(){
        @Override
        public void run() {
            super.run();
            while (showAnimal){
                try {
                    deg+=5;
                    if(deg>=360){
                        deg=0;
                        type++;
                        if(type>2){
                            type=0;
                        }
                    }
                    postInvalidate();
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
}
