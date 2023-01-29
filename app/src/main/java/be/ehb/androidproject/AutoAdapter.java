package be.ehb.androidproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AutoAdapter extends RecyclerView.Adapter<AutoAdapter.MyHolder> {

    Context context;
    ArrayList<ModelClass> arrayList;
    LayoutInflater layoutInflater;

    public AutoAdapter(Context context, ArrayList<ModelClass> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        layoutInflater=LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.item_file,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.AutoName.setText(arrayList.get(position).getAutoName());
        holder.AutoNum.setText(arrayList.get(position).getAutoNum());
        holder.img.setImageResource(arrayList.get(position).getImg());



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class MyHolder extends RecyclerView.ViewHolder {

        TextView AutoName,AutoNum;
        ImageView img;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            AutoName=itemView.findViewById(R.id.txt);
            AutoNum=itemView.findViewById(R.id.txt2);
            img=itemView.findViewById(R.id.img);
        }
    }
}
