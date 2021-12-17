package com.oyelabs.marvel.universe.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.oyelabs.marvel.universe.Activities.CharacterIdActivity;
import com.oyelabs.marvel.universe.R;
import com.oyelabs.marvel.universe.Response.CharacterResponse.ResultsItem;

import java.util.ArrayList;
import java.util.Locale;

public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.ViewHolder> {
    Context context;
    ArrayList<ResultsItem> arrayList= new ArrayList();
    ArrayList<ResultsItem> filterarray= new ArrayList();

    public CharactersAdapter(Context context, ArrayList<ResultsItem> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        this.filterarray.addAll(arrayList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.custom_first_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.character_name.setText(""+arrayList.get(position).getName());
        holder.character_id.setText(""+arrayList.get(position).getId());
        Glide.with(context).load(arrayList.get(position).getThumbnail().getPath()+"."+arrayList.get(position).getThumbnail().getExtension()).into(holder.character_image);
        holder.character_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, CharacterIdActivity.class);
                intent.putExtra("id",arrayList.get(position).getId());
                context.startActivity(intent);
            }
        });

        Log.e("TAG", "onBindViewHolder: "+arrayList.get(position).getThumbnail().getPath()+"."+arrayList.get(position).getThumbnail().getExtension() );

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout character_layout;
        ImageView character_image;
        TextView character_name,character_id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            character_layout=itemView.findViewById(R.id.character_layout);
            character_image=itemView.findViewById(R.id.image_marvel);
            character_id=itemView.findViewById(R.id.character_id);
            character_name=itemView.findViewById(R.id.character_name);
        }
    }



    public void filterForSearch(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        arrayList.clear();
        if (charText.length() == 0) {
            arrayList.addAll(filterarray);
        } else {
            for (int i = 0; i < filterarray.size(); i++) {
                if (filterarray.get(i).getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    arrayList.add(filterarray.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }
}

