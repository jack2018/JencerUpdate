package jenc.sdl.com.jencerupdate;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.text.LoginFilter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ScreenUtils;

public class MyLayoutManager3 extends RecyclerView.LayoutManager {
    int totalHeight = 0;
    int offy=0;
    public Rect showPlace = new Rect(0,offy,ScreenUtils.getScreenWidth(),offy+ScreenUtils.getScreenHeight());
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {

        if(getItemCount()<=0||state.isPreLayout()){
            return;
        }
        super.onLayoutChildren(recycler, state);
        detachAndScrapAttachedViews(recycler);//清楚数据
        calculate(recycler);
        Log.e("cc","初始totalheight"+totalHeight);
    }
    public void calculate(RecyclerView.Recycler recycler){
        totalHeight =0;
        for (int i = 0; i < getItemCount(); i++) {
            View view = recycler.getViewForPosition(i);
            addView(view);
            measureChildWithMargins(view,0,0);
           Rect dre = new Rect();
            calculateItemDecorationsForChild(view,dre);//计算ItemDecoration
           int widyh = getDecoratedMeasuredWidth(view);
           int height = getDecoratedMeasuredHeight(view);
           Rect rect = new Rect(0,totalHeight,widyh,totalHeight+height);
//           if(!Rect.intersects(showPlace,rect)){
//               detachAndScrapView(view,recycler);
//           break;
//           }
            layoutDecoratedWithMargins(view,0,totalHeight,widyh,totalHeight+height);
            totalHeight+=height;
        }
        logda("weizhis:"+getChildCount()+"totalHeight:"+totalHeight);
    }
public void logda(String log){
        Log.e("ccb",log);
}
    @Override
    public boolean canScrollVertically() {
        // 返回true表示可以纵向滑动
        return true;
    }

    public int getHorizontalSpace() {
        return getWidth() - getPaddingLeft() - getPaddingRight();
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int travel = dy;
//       LinearLayout linearLayout = (LinearLayout) recycler.getViewForPosition(0);
//    TextView textView = (TextView) linearLayout.getChildAt(0);
//        Log.e("cc","上："+showPlace.top+"下："+showPlace.bottom+"左："+showPlace.left+"右："+showPlace.right+"偏移量:"+offy+"已加载数量"+getChildCount()+"数据："+textView.getText().toString());

        if(offy+dy<0){
            travel=-offy;
        }

        offy+=travel;
//        showPlace.top=offy;
//        showPlace.bottom =offy+ScreenUtils.getScreenHeight();
////        Log.e("ddd","woyao1&&"+travel);
//        Log.e("ddd","woyao2&&"+computeVerticalScrollRange(state));
        offsetChildrenVertical(-travel);
//
//        int date = getChildCount();
//        Log.e("cc","开始totalheight"+totalHeight);
//        for (int i = date; i < getItemCount(); i++) {
//            View view = recycler.getViewForPosition(i);
//            addView(view);
//            measureChildWithMargins(view,0,0);
//            Rect dre = new Rect();
//            calculateItemDecorationsForChild(view,dre);//计算ItemDecoration
//            int widyh = getDecoratedMeasuredWidth(view);
//            int height = getDecoratedMeasuredHeight(view);
//            Rect rect = new Rect(0,totalHeight,widyh,totalHeight+height);
//            Log.e("cc","totalheightv"+totalHeight+"geight---"+height+"^^^^^^"+getHeight());
//            if(!Rect.intersects(showPlace,rect)){
//                Log.e("cc","totalheightv进");
//                detachAndScrapView(view,recycler);
//                break;
//            }
//            layoutDecoratedWithMargins(view,0,1200,widyh,1200+height);
//            totalHeight+=height;
//        }

        return travel;
    }
}
