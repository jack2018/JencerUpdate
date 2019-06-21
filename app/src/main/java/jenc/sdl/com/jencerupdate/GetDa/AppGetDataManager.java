package jenc.sdl.com.jencerupdate.GetDa;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidParameterException;

import android_serialport_api.SerialPort;

public class AppGetDataManager {
    private static AppGetDataManager appGetDataManager;
    private InputStream mInputStream;
    public  GetDateAPI getDateAPI;
    OutputStream mOutputStream;
    ReadThread  mReadThread;
    String port;
    int rate;
    SerialPort mSerialPort;
    public boolean IsRun=false;
   public byte[] curentorder = Config.getWeight;
    private class ReadThread extends Thread {

        @Override
        public void run() {
            super.run();
            while(IsRun) {
                int size;
                try {
                    byte[] buffer = new byte[26];
                    if (mInputStream == null)
                        return;
                    size = mInputStream.read(buffer);
                    if (size > 0) {
                       String dd = toHexString1(buffer).toUpperCase();
                        getDateAPI.getWeight(dd,"",false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return;

                }
            }
        }
    }

    private AppGetDataManager() {
    }
    private AppGetDataManager(@NonNull GetDateAPI getDateAPI, @NonNull String port, @NonNull int rate) {
        this.getDateAPI =getDateAPI;
        this.port=port;
        this.rate =rate;
        setCreatport(port,rate);
    }
    public static AppGetDataManager instance(GetDateAPI getDateAPI,String port,int rate){
        if(appGetDataManager==null){
            synchronized (AppGetDataManager.class){
                if(appGetDataManager==null){
                    appGetDataManager=new AppGetDataManager(getDateAPI,port,rate);
                }
            }
        }
        return appGetDataManager;
    }
    public  void  writeDate(byte[] bytes){
//              setCreatport(port+"",rate);
        synchronized (bytes){
            try {
                if(bytes!=null&&bytes.length>0){
                    if(mOutputStream!=null)
                    mOutputStream.write(bytes);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private  void  writeweight(byte[] bytes){
//              setCreatport(port+"",rate);
        synchronized (bytes){
            try {
                if(bytes!=null&&bytes.length>0){
                    if(mOutputStream!=null)
                    mOutputStream.write(bytes);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public  void  setCreatport(String devide,int port){
        try {
            //mSerialPort = mApplication.getSerialPort("/dev/ttyS2",9600);


            mSerialPort  =new SerialPort(new File(devide), port, 0);

            //mApplication.getSerialPort(devide,port);

            mOutputStream  = mSerialPort.getOutputStream();
            mInputStream = mSerialPort.getInputStream();
            startRead();

        } catch (InvalidParameterException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (SecurityException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

    }
    public void close(){
        IsRun=false;
        mReadThread.interrupt();
        mSerialPort.close();
        appGetDataManager=null;
    }

   private void startRead(){
       IsRun=true;
       mReadThread  = new ReadThread();
       mReadThread.start();
       writeweight(curentorder);
   }

    public static String toHexString1(byte[] b){
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < b.length; ++i){
            buffer.append(toHexString1(b[i]));
        }
        return buffer.toString();
    }

    public static String toHexString1(byte b){
        String s = Integer.toHexString(b & 0xFF);
        if (s.length() == 1){
            return "0" + s;
        }else{
            return s;
        }
    }
}
