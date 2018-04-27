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

        switch (cardList.get(position).getJenis()){
            case 1:
                switch (cardList.get(position).getTampilan()){
                    case "A":
                        holder.textNumber.setBackgroundResource(R.drawable.spade_a);
                        break;
                    case "2":
                        holder.textNumber.setBackgroundResource(R.drawable.spade_2);
                        break;
                    case "3":
                        holder.textNumber.setBackgroundResource(R.drawable.spade_3);
                        break;
                    case "4":
                        holder.textNumber.setBackgroundResource(R.drawable.spade_4);
                        break;
                    case "5":
                        holder.textNumber.setBackgroundResource(R.drawable.spade_5);
                        break;
                    case "6":
                        holder.textNumber.setBackgroundResource(R.drawable.spade_6);
                        break;
                    case "7":
                        holder.textNumber.setBackgroundResource(R.drawable.spade_7);
                        break;
                    case "8":
                        holder.textNumber.setBackgroundResource(R.drawable.spade_8);
                        break;
                    case "9":
                        holder.textNumber.setBackgroundResource(R.drawable.spade_9);
                        break;
                    case "10":
                        holder.textNumber.setBackgroundResource(R.drawable.spade_10);
                        break;
                    case "J":
                        holder.textNumber.setBackgroundResource(R.drawable.spade_j);
                        break;
                    case "Q":
                        holder.textNumber.setBackgroundResource(R.drawable.spade_q);
                        break;
                    case "K":
                        holder.textNumber.setBackgroundResource(R.drawable.spade_k);
                        break;
                    default:
                        break;
                }
                break;
            case 2:
                switch (cardList.get(position).getTampilan()){
                    case "A":
                        holder.textNumber.setBackgroundResource(R.drawable.club_a);
                        break;
                    case "2":
                        holder.textNumber.setBackgroundResource(R.drawable.club_2);
                        break;
                    case "3":
                        holder.textNumber.setBackgroundResource(R.drawable.club_3);
                        break;
                    case "4":
                        holder.textNumber.setBackgroundResource(R.drawable.club_4);
                        break;
                    case "5":
                        holder.textNumber.setBackgroundResource(R.drawable.club_5);
                        break;
                    case "6":
                        holder.textNumber.setBackgroundResource(R.drawable.club_6);
                        break;
                    case "7":
                        holder.textNumber.setBackgroundResource(R.drawable.club_7);
                        break;
                    case "8":
                        holder.textNumber.setBackgroundResource(R.drawable.club_8);
                        break;
                    case "9":
                        holder.textNumber.setBackgroundResource(R.drawable.club_9);
                        break;
                    case "10":
                        holder.textNumber.setBackgroundResource(R.drawable.club_10);
                        break;
                    case "J":
                        holder.textNumber.setBackgroundResource(R.drawable.club_j);
                        break;
                    case "Q":
                        holder.textNumber.setBackgroundResource(R.drawable.club_q);
                        break;
                    case "K":
                        holder.textNumber.setBackgroundResource(R.drawable.club_k);
                        break;
                    default:
                        break;
                }
                break;
            case 3:
                switch (cardList.get(position).getTampilan()){
                    case "A":
                        holder.textNumber.setBackgroundResource(R.drawable.diamonds_a);
                        break;
                    case "2":
                        holder.textNumber.setBackgroundResource(R.drawable.diamonds_2);
                        break;
                    case "3":
                        holder.textNumber.setBackgroundResource(R.drawable.diamonds_3);
                        break;
                    case "4":
                        holder.textNumber.setBackgroundResource(R.drawable.diamonds_4);
                        break;
                    case "5":
                        holder.textNumber.setBackgroundResource(R.drawable.diamonds_5);
                        break;
                    case "6":
                        holder.textNumber.setBackgroundResource(R.drawable.diamonds_6);
                        break;
                    case "7":
                        holder.textNumber.setBackgroundResource(R.drawable.diamonds_7);
                        break;
                    case "8":
                        holder.textNumber.setBackgroundResource(R.drawable.diamonds_8);
                        break;
                    case "9":
                        holder.textNumber.setBackgroundResource(R.drawable.diamonds_9);
                        break;
                    case "10":
                        holder.textNumber.setBackgroundResource(R.drawable.diamonds_10);
                        break;
                    case "J":
                        holder.textNumber.setBackgroundResource(R.drawable.diamonds_j);
                        break;
                    case "Q":
                        holder.textNumber.setBackgroundResource(R.drawable.diamonds_q);
                        break;
                    case "K":
                        holder.textNumber.setBackgroundResource(R.drawable.diamonds_k);
                        break;
                    default:
                        break;
                }
                break;
            case 4:
                switch (cardList.get(position).getTampilan()){
                    case "A":
                        holder.textNumber.setBackgroundResource(R.drawable.hearts_a);
                        break;
                    case "2":
                        holder.textNumber.setBackgroundResource(R.drawable.hearts_2);
                        break;
                    case "3":
                        holder.textNumber.setBackgroundResource(R.drawable.hearts_3);
                        break;
                    case "4":
                        holder.textNumber.setBackgroundResource(R.drawable.hearts_4);
                        break;
                    case "5":
                        holder.textNumber.setBackgroundResource(R.drawable.hearts_5);
                        break;
                    case "6":
                        holder.textNumber.setBackgroundResource(R.drawable.hearts_6);
                        break;
                    case "7":
                        holder.textNumber.setBackgroundResource(R.drawable.hearts_7);
                        break;
                    case "8":
                        holder.textNumber.setBackgroundResource(R.drawable.hearts_8);
                        break;
                    case "9":
                        holder.textNumber.setBackgroundResource(R.drawable.hearts_9);
                        break;
                    case "10":
                        holder.textNumber.setBackgroundResource(R.drawable.hearts_9);
                        break;
                    case "J":
                        holder.textNumber.setBackgroundResource(R.drawable.hearts_j);
                        break;
                    case "Q":
                        holder.textNumber.setBackgroundResource(R.drawable.hearts_q);
                        break;
                    case "K":
                        holder.textNumber.setBackgroundResource(R.drawable.hearts_k);
                        break;
                    default:
                        break;
                }
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
