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
 * Created by Beng on 3/1/2018.
 */

public class CardAdapterMain extends RecyclerView.Adapter<CardAdapterMain.CardViewHolder>{
    private List<Card> cardList = new ArrayList<>();
    private Context context;
    private static RecyclerViewItemClickInterface recyclerViewItemClickInterface;

    public CardAdapterMain(List<Card> cardList, Context context, RecyclerViewItemClickInterface item){
        this.cardList = cardList;
        this.context = context;
        this.recyclerViewItemClickInterface = item;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardViewHolder cardViewHolder;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout,parent,false);
        cardViewHolder = new CardViewHolder(view);
        return cardViewHolder;
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, final int position) {
        holder.textNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerViewItemClickInterface.recyclerItemMainClick(view, position);
            }
        });

        holder.textNumber.setText(cardList.get(position).getTampilan());

    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView textNumber;

        public CardViewHolder(View view){
            super(view);
            textNumber = (TextView)view.findViewById(R.id.cardView);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            recyclerViewItemClickInterface.recyclerItemMainClick(view, this.getLayoutPosition());

        }
    }
}
