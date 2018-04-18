package com.example.beng.cobaquiz.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.beng.cobaquiz.Model.Card;
import com.example.beng.cobaquiz.R;
import com.example.beng.cobaquiz.Interface.RecyclerViewItemClickInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Beng on 2/23/2018.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.UserViewHolder> {
    private List<Card> cardList = new ArrayList<>();
    private Context context;
    private static RecyclerViewItemClickInterface recyclerViewItemClickInterface;

    public CardAdapter(List<Card> listCard, Context context, RecyclerViewItemClickInterface item){
        this.cardList = listCard;
        this.context = context;
        this.recyclerViewItemClickInterface = item;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        UserViewHolder userViewHolder;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout,parent,false);
        userViewHolder = new UserViewHolder(view);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, final int position) {
        holder.textNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerViewItemClickInterface.recyclerItemClick(view, position);
            }
        });

        holder.textNumber.setText(cardList.get(position).getTampilan());
        switch (cardList.get(position).getJenis()){
            case 1:
                holder.textNumber.setBackgroundResource(R.drawable.bg_spade_straigth);
                holder.textNumber.setPadding(2,0,0,2);
                break;
            case 2:
                holder.textNumber.setBackgroundResource(R.drawable.bg_club_straight);
                holder.textNumber.setPadding(2,0,0,5);
                break;
            case 3:
                holder.textNumber.setBackgroundResource(R.drawable.bg_diamond_straight);
                holder.textNumber.setPadding(2,5,0,0);
                break;
            case 4:
                holder.textNumber.setBackgroundResource(R.drawable.bg_hearts_straight);
                holder.textNumber.setPadding(2,10,0,0);
                break;
            default:
                break;
        }

    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }


    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView textNumber;

        public UserViewHolder(View view){
            super(view);
            textNumber = (TextView)view.findViewById(R.id.cardView);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            recyclerViewItemClickInterface.recyclerItemClick(view, this.getLayoutPosition());

        }
    }
}
