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
import com.example.beng.cobaquiz.Model.User;
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
    private Button playerButton1, playerButton2, playerButton3, playerButton4, playerButton5, playerButton6, playerButton7, playerButton8;
    private List<Button> listButtonPlayer;
    private List<User> listplayer;
    int playerCount = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        startButton = (Button) findViewById(R.id.startButton);
        answerButton = (Button) findViewById(R.id.answerButton);
        playerButton1 = (Button) findViewById(R.id.player1);
        playerButton2 = (Button) findViewById(R.id.player2);
        playerButton3 = (Button) findViewById(R.id.player3);
        playerButton4 = (Button) findViewById(R.id.player4);
        playerButton5 = (Button) findViewById(R.id.player5);
        playerButton6 = (Button) findViewById(R.id.player6);
        playerButton7 = (Button) findViewById(R.id.player7);
        playerButton8 = (Button) findViewById(R.id.player8);
        listplayer = initiateListPlayer();

        playerButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!listplayer.get(0).isAnswerStatus()){
                    playerButton1.setClickable(false);
                }
                answerHandler(listplayer.get(0));
            }
        });

        playerButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!listplayer.get(1).isAnswerStatus()){
                    playerButton2.setClickable(false);
                }
                answerHandler(listplayer.get(1));
            }
        });

        playerButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!listplayer.get(2).isAnswerStatus()){
                    playerButton3.setClickable(false);
                }
                answerHandler(listplayer.get(2));
            }
        });

        playerButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!listplayer.get(3).isAnswerStatus()){
                    playerButton4.setClickable(false);
                }
                answerHandler(listplayer.get(3));
            }
        });

        playerButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!listplayer.get(4).isAnswerStatus()){
                    playerButton5.setClickable(false);
                }
                answerHandler(listplayer.get(4));
            }
        });

        playerButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!listplayer.get(5).isAnswerStatus()){
                    playerButton6.setClickable(false);
                }
                answerHandler(listplayer.get(5));
            }
        });

        playerButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!listplayer.get(6).isAnswerStatus()){
                    playerButton7.setClickable(false);
                }
                answerHandler(listplayer.get(6));
            }
        });

        playerButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!listplayer.get(7).isAnswerStatus()){
                    playerButton8.setClickable(false);
                }
                answerHandler(listplayer.get(7));
            }
        });

        listToPass = new ArrayList<>();
        cardRandomedResult = new ArrayList<>();
        listButtonPlayer = initiateListButtononCreate();
        Log.i("ceksize", "onCreate: " + listButtonPlayer.size());
        initiateListButtonBaseOnPlayerCount(playerCount);
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

    public List<Button> initiateListButtononCreate(){
        List<Button> initialButtonList = new ArrayList<>();
            initialButtonList.add(playerButton1);
            initialButtonList.add(playerButton2);
            initialButtonList.add(playerButton3);
            initialButtonList.add(playerButton4);
            initialButtonList.add(playerButton5);
            initialButtonList.add(playerButton6);
            initialButtonList.add(playerButton7);
            initialButtonList.add(playerButton8);
        return initialButtonList;
    }

    public void initiateListButtonBaseOnPlayerCount(int playerCount){
        for(int i=playerCount -1 ; i<listButtonPlayer.size()-1; i++){
            listButtonPlayer.get(i).setVisibility(View.GONE);
        }
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

    public void answerHandler(User player){
        for(Card a : cardRandomedResult){
            listToPass.add(a);
        }
        Intent intent = new Intent(QuizActivity.this, MainActivity.class);
        intent.putExtra("listCardRandomed", (Serializable) listToPass);
        intent.putExtra("userAnswer", (Serializable) player);
        cardRandomedResult.clear();
        randomedCardAdapter.notifyDataSetChanged();
        startActivity(intent);
    }

    public List<User> initiateListPlayer(){
        List<User> listUserReturn = new ArrayList<>();
        User myUser = new User();
        myUser.setIdUser("haha");
        myUser.setNamaUser("haha");
        myUser.setJumlahBenar(0);
        myUser.setAnswerStatus(false);

        User myUser2 = new User();
        myUser2.setIdUser("haha");
        myUser2.setNamaUser("haha");
        myUser2.setJumlahBenar(0);
        myUser2.setAnswerStatus(false);

        User myUser3 = new User();
        myUser3.setIdUser("haha");
        myUser3.setNamaUser("haha");
        myUser3.setJumlahBenar(0);
        myUser3.setAnswerStatus(false);

        User myUser4 = new User();
        myUser4.setIdUser("haha");
        myUser4.setNamaUser("haha");
        myUser4.setJumlahBenar(0);
        myUser4.setAnswerStatus(false);

        listUserReturn.add(myUser);
        listUserReturn.add(myUser2);
        listUserReturn.add(myUser3);
        listUserReturn.add(myUser4);

        return listUserReturn;
    }

    public void initiateConditionButtonPlayer(){
        for(Button button : listButtonPlayer){
            button.setClickable(true);
        }
        for(User user: listplayer){
            user.setAnswerStatus(false);
        }
    }
}
