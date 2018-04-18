package com.example.beng.cobaquiz.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.beng.cobaquiz.Model.Card;
import com.example.beng.cobaquiz.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Beng on 4/13/2018.
 */

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.CardQuizViewHolder>{
    List<Card> listCard = new ArrayList<>();

    public QuizAdapter(List<Card> listToBound){
        this.listCard = listToBound;
    }

    @Override
    public CardQuizViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_selected_layout,parent,false);
        CardQuizViewHolder cardQuizViewHolder = new CardQuizViewHolder(view);
        return cardQuizViewHolder;
    }

    @Override
    public void onBindViewHolder(CardQuizViewHolder holder, int position) {
        holder.textCard.setText(listCard.get(position).getTampilan());
    }

    @Override
    public int getItemCount() {
        return listCard.size();
    }

    public class CardQuizViewHolder extends RecyclerView.ViewHolder{

        private TextView textCard;

        public CardQuizViewHolder(View itemView) {
            super(itemView);
            textCard = (TextView) itemView.findViewById(R.id.textSelectedCard);
        }
    }
}
