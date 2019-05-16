package jenc.sdl.com.jencerupdate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.pdf.PdfRenderer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.blankj.utilcode.util.ScreenUtils;

public class SurviceAnimal extends SurfaceView implements SurfaceHolder.Callback,Runnable {
    public boolean startrun=false;
    SurfaceHolder surfaceHolder;
    Paint paint;
    public int roat=0;
    public SurviceAnimal(Context context) {
        super(context);
        init();
    }

    public SurviceAnimal(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SurviceAnimal(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void init(){
        Log.e("ss","获取DPI"+ScreenUtils.getScreenDensity());
        setZOrderOnTop(true);//设置背景透明1
        surfaceHolder = getHolder();
        surfaceHolder.setFormat(PixelFormat.TRANSLUCENT);//设置背景透明2
        surfaceHolder.addCallback(this);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
    }

    public void draw(){
        synchronized (surfaceHolder){
           Canvas canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);//如果为lockCanvas()清空痕迹
//            canvas.translate(100,100);
            canvas.rotate(roat,100,100);
            canvas.drawLine(0,0,100,100,paint);
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        startrun =true;
        Thread thread = new Thread(this);
         thread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void run() {
        while (startrun&roat<180){
           roat = roat+10;
            draw();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
