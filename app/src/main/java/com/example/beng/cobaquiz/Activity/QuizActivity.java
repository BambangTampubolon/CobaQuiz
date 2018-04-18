package com.example.beng.cobaquiz.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.beng.cobaquiz.Adapter.QuizAdapter;
import com.example.beng.cobaquiz.Model.Card;
import com.example.beng.cobaquiz.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuizActivity extends Activity {

    private RecyclerView randomedCardView;
    private RecyclerView.Adapter randomedCardAdapter;
    private LinearLayoutManager randomedCardLayout;
    private List<Card> cardListDeck;
    private List<Card> cardRandomedResult;
    private List<Card> listToPass;
    private Button startButton, answerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        listToPass = new ArrayList<>();
        cardRandomedResult = new ArrayList<>();
        startButton = (Button) findViewById(R.id.startButton);
        answerButton = (Button) findViewById(R.id.answerButton);
        randomedCardView = (RecyclerView) findViewById(R.id.randomedCardRecycler);
        randomedCardAdapter = new QuizAdapter(cardRandomedResult);
        randomedCardLayout = new LinearLayoutManager(QuizActivity.this,LinearLayoutManager.HORIZONTAL,false);
        randomedCardView.setLayoutManager(randomedCardLayout);
        randomedCardView.setAdapter(randomedCardAdapter);
        cardListDeck = populateDeckCard();
        Log.i("ceklistsize", "onCreate: " + cardListDeck.size());



        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0; i<4; i++){
                    cardRandomedResult.add(getNumberToCalculate(cardListDeck));
                }
                Log.i("cekrandomedcardsize", "onClick: " + cardRandomedResult.size());
                Log.i("ceklistdeck", "onClick: " + cardListDeck.size());
                randomedCardAdapter.notifyDataSetChanged();
                listToPass.clear();
            }
        });

        answerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(Card a : cardRandomedResult){
                    listToPass.add(a);
                }
                Intent intent = new Intent(QuizActivity.this, MainActivity.class);
                intent.putExtra("listCardRandomed", (Serializable) listToPass);
                cardRandomedResult.clear();
                randomedCardAdapter.notifyDataSetChanged();
                startActivity(intent);
            }
        });
    }

    //getting randomed listcard to calculate
    private Card getNumberToCalculate(List<Card> listParam){
        Random random = new Random();
        int selected = random.nextInt(listParam.size()-1);
        Card myCard = cardListDeck.get(selected);
        cardListDeck.remove(selected);
        return myCard;
    }

    //populate 1 deck card that will be randomed
    public List<Card> populateDeckCard(){
        List<Card> myCard = new ArrayList<>();
        for(int i = 1; i<=4; i++){
            for (int j=2; j<=10; j++){
                Card newCard = new Card();
                newCard.setJenis(i);
                newCard.setValue(j);
                newCard.setTampilan(String.valueOf(j));
                myCard.add(newCard);
            }
        }
        for(int y=1; y<=4;y++){
                Card newCard = new Card();
            newCard.setJenis(y);
            newCard.setValue(1);
            newCard.setTampilan("A");
            myCard.add(newCard);
        }
        for(int y=1; y<=4;y++){
            Card newCard = new Card();
            newCard.setJenis(y);
            newCard.setValue(10);
            newCard.setTampilan("J");
            myCard.add(newCard);
        }
        for(int y=1; y<=4;y++){
            Card newCard = new Card();
            newCard.setJenis(y);
            newCard.setValue(10);
            newCard.setTampilan("Q");
            myCard.add(newCard);
        }
        for(int y=1; y<=4;y++){
            Card newCard = new Card();
            newCard.setJenis(y);
            newCard.setValue(10);
            newCard.setTampilan("K");
            myCard.add(newCard);
        }
        return myCard;
    }
}
