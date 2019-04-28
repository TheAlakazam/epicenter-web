package com.example.epicentre;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.viewholder> {
    ArrayList<Data> datas;
    Context context;

    public Adapter(ArrayList<Data> datas, Context context) {
        this.datas = datas;
        this.context=context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater lf = LayoutInflater.from(viewGroup.getContext());
        View view=lf.inflate(R.layout.itemview,viewGroup,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder viewholder, int i) {
        viewholder.heading.setText(datas.get(i).Heading);
        viewholder.image.setImageBitmap(datas.get(i).image);
        final int pos=i;
        viewholder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, information.class);
                intent.putExtra("info",pos);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView heading;
        ImageView image;
        ConstraintLayout parentLayout;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            heading=itemView.findViewById(R.id.Heading);
            image=itemView.findViewById(R.id.itemImage);
            parentLayout=itemView.findViewById(R.id.parentLayout);
        }
    }
}
