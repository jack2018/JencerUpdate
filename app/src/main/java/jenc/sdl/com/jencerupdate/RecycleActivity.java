package jenc.sdl.com.jencerupdate;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RecycleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
      RecyclerView recyclerView = findViewById(R.id.re_te);
      recyclerView.addItemDecoration(new ite());
      recyclerView.setLayoutManager(new MyLayoutManager3());
//        recyclerView.setLayoutManager(new GridLayoutManager(RecycleActivity.this,2));
//        recyclerView.setLayoutManager(new LinearLayoutManager(RecycleActivity.this));
      recyclerView.setAdapter(new rehold());
    }
    class rehold extends RecyclerView.Adapter<MyHold>{
        @NonNull
        @Override
        public MyHold onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new MyHold(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_text,viewGroup,false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyHold viewHolder, int i) {
            viewHolder.textView.setText("geshu"+i);
        }

        @Override
        public int getItemCount() {
            return 800;
        }
    }
    class MyHold extends RecyclerView.ViewHolder{
        TextView textView;
        public MyHold(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_ss);
        }
    }
    class ite extends RecyclerView.ItemDecoration{
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
        }
    }
}
