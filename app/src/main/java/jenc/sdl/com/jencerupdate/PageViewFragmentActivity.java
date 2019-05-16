package jenc.sdl.com.jencerupdate;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class PageViewFragmentActivity extends AppCompatActivity {
    ArrayList<Fragment> fragments;
    View viewid;
    int dis=0;
    myAdapter m;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
           int i= msg.what;
            viewid.setTranslationX(viewid.getWidth()*i);
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lazy);
        final ViewPager viewPager = findViewById(R.id.vp);
        findViewById(R.id.tv_jpg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2);
                m.notifyDataSetChanged();
//                handler.sendEmptyMessageDelayed(1,500);
            }
        });
         fragments = new ArrayList<>();
        fragments.add(new MViewPageFragemnt());
        fragments.add(new MViewPageFragemnt2());
        fragments.add(new MViewPageFragemnt3());
        fragments.add(new MViewPageFragemnt4());
        viewid  =findViewById(R.id.view_ind);
        m  = new myAdapter(getSupportFragmentManager());
        viewPager.setAdapter(m);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            int item=-1;

            /**
             * @param i 当前页
             * @param v 移动百分比
             * @param i1 移动距离
             */
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                Log.e("5555","页数"+i+" ------- "+v+" ---- "+i1+"-----》"+item);
//                if(i==item){
                    viewid.setTranslationX(dis*(v+i));
//                }
//                if(i<item){
//                    viewid.setTranslationX((i+v)*dis);
//                }
            }

            /**
             * @param i 当前选择也
             */
            @Override
            public void onPageSelected(int i) {
                if(item==-1){
                    handler.sendEmptyMessageDelayed(i,300);
                }
            }

            /**
             * @param i 手指按下的状态值
             */
            @Override
            public void onPageScrollStateChanged(int i) {
                if(i==1){
                dis=  viewid.getWidth();
                }
//                Log.e("5555","状态值"+viewPager.getCurrentItem());
            }
        });
    }

    /**
     * page适配器
     */
    class myAdapter extends FragmentPagerAdapter {

        public myAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
