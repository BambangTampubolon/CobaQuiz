package com.example.beng.cobaquiz.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beng.cobaquiz.Adapter.CardAdapter;
import com.example.beng.cobaquiz.Adapter.CardAdapterMain;
import com.example.beng.cobaquiz.Interface.RecyclerViewItemClickInterface;
import com.example.beng.cobaquiz.Model.Card;
import com.example.beng.cobaquiz.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends Activity implements RecyclerViewItemClickInterface {

    private TextView answerTimer;
    private CountDownTimer timerCountDown;
    private TextView textOperator1, textOperator2, textOperator3, textOperator4, textHasil, textOperator5, textOperator6;
    private Button buttonSubmit;
    private RecyclerView mRecyclerView, cardRecyclerView;
    private RecyclerView.Adapter mAdapter, cardAdapter;
    private LinearLayoutManager mLayoutManager, cardLayoutManager;
    private List<Card> cardList;
    private List<Card> selectedCard;
    private List<Card> cardRandomed;
    private List<Card> cardOperator;

    private boolean card1Clicked, card2Clicked, card3Clicked, card4Clicked;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardList = new ArrayList<>();
        selectedCard = new ArrayList<>();
        cardRandomed = new ArrayList<>();
        cardOperator = populateOperatorCard();
        initiateCardCondition();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        cardRecyclerView = (RecyclerView) findViewById(R.id.recyclerKartuRandom);
        mAdapter = new CardAdapterMain(selectedCard, this, this);
        cardAdapter = new CardAdapter(cardRandomed,this, this);
        mLayoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);
        cardLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        cardRecyclerView.setLayoutManager(cardLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        cardRecyclerView.setAdapter(cardAdapter);
        textOperator1 = (TextView) findViewById(R.id.operator1);
        textOperator2 = (TextView) findViewById(R.id.operator2);
        textOperator3 = (TextView) findViewById(R.id.operator3);
        textOperator4 = (TextView) findViewById(R.id.operator4);
        textOperator5 = (TextView) findViewById(R.id.operator5);
        textOperator6 = (TextView) findViewById(R.id.operator6);
        answerTimer = (TextView) findViewById(R.id.timerText);
        textHasil = (TextView) findViewById(R.id.hasilResult);
        buttonSubmit = (Button) findViewById(R.id.submit_result);
        StartTimer(10000);
        Intent intentFromQuizActivity = getIntent();
        List<Card> checkCard = (List<Card>)intentFromQuizActivity.getSerializableExtra("listCardRandomed");
        for(Card a: checkCard){
            cardRandomed.add(a);
        }
        Log.i("ceklistlemparan", "onCreate: " + cardRandomed.size());
        cardAdapter.notifyDataSetChanged();

        textOperator1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addOperatorHitung(textOperator1.getText().toString());
            }
        });

        textOperator2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addOperatorHitung(textOperator2.getText().toString());
            }
        });

        textOperator3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addOperatorHitung(textOperator3.getText().toString());
            }
        });

        textOperator4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addOperatorHitung(textOperator4.getText().toString());
            }
        });

        textOperator5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addOperatorBracket(textOperator5.getText().toString());
            }
        });

        textOperator6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addOperatorBracket(textOperator6.getText().toString());
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               FinishActivity();
            }
        });
    }


    public void FinishActivity(){
        int finalResult = doPrioritiesToCalculation(selectedCard);
        Intent intentToResult = new Intent(MainActivity.this, ResultDialog.class);
        intentToResult.putExtra("answerByUser", finalResult);
        textHasil.setText(String.valueOf(finalResult));
        startActivity(intentToResult);
    }

    public void StartTimer(long timerLimit){
        timerCountDown = new CountDownTimer(timerLimit, 1000) {
            @Override
            public void onTick(long l) {
                answerTimer.setText(String.valueOf(l/1000));
            }

            @Override
            public void onFinish() {
                answerTimer.setText("times up boysss");
                FinishActivity();
            }
        };
        timerCountDown.start();
    }

    @Override
    public void onBackPressed() {

//        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.submit_menu:
                cardAdapter.notifyDataSetChanged();

            default:
            return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_submit, menu);
        return super.onCreateOptionsMenu(menu);
    }


    //resetting card every round
    public void resetDataKartuHitung(){
        selectedCard.clear();
        cardRandomed.clear();
        mAdapter.notifyDataSetChanged();
        cardAdapter.notifyDataSetChanged();
    }

    //addomg card to list card to calculate
    public void addDataToHitung(Card masukan){
        selectedCard.add(masukan);
        mAdapter.notifyDataSetChanged();
    }

    //deleting card from listcard to calculate
    public void removeDataFromHitung(Card masukan){
        for(int a=0; a<selectedCard.size(); a++){
            if(selectedCard.get(a) == masukan){
                selectedCard.remove(a);
                a--;
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    //populate operator card to use by user
    public List<Card> populateOperatorCard(){
        List<Card> cardReturned = new ArrayList<>();
        Card myCard = new Card();
        myCard.setTampilan("+");
        myCard.setJenis(5);
        myCard.setValue(1);
        cardReturned.add(myCard);

        Card myCard1 = new Card();
        myCard1.setTampilan("-");
        myCard1.setJenis(5);
        myCard1.setValue(2);
        cardReturned.add(myCard1);

        Card myCard2 = new Card();
        myCard2.setTampilan("*");
        myCard2.setJenis(5);
        myCard2.setValue(3);
        cardReturned.add(myCard2);

        Card myCard3 = new Card();
        myCard3.setTampilan("/");
        myCard3.setJenis(5);
        myCard3.setValue(4);
        cardReturned.add(myCard3);

        Card myCard4 = new Card();
        myCard4.setTampilan("(");
        myCard4.setJenis(6);
        myCard4.setValue(5);
        cardReturned.add(myCard4);

        Card myCard5 = new Card();
        myCard5.setTampilan(")");
        myCard5.setJenis(6);
        myCard5.setValue(6);
        cardReturned.add(myCard5);
        return cardReturned;

    }



    //listcard randomed click controller==> adding or removing card from list card to calculate
    @Override
    public void recyclerItemClick(View v, int position) {
        switch (position){
            case 0:
                if(card1Clicked){
                    removeDataFromHitung(cardRandomed.get(position));
                    card1Clicked = false;
                } else {
                    if(checkLastCard(cardRandomed.get(position))){
                        addDataToHitung(cardRandomed.get(position));
                    }
                    card1Clicked = true;
                }
                break;
            case 1:
                if(card2Clicked){
                    removeDataFromHitung(cardRandomed.get(position));
                    card2Clicked = false;
                } else {
                    if(checkLastCard(cardRandomed.get(position))){
                        addDataToHitung(cardRandomed.get(position));
                    }
                    card2Clicked = true;
                }
                break;
            case 2:
                if(card3Clicked){
                    removeDataFromHitung(cardRandomed.get(position));
                    card3Clicked = false;
                } else {
                    if(checkLastCard(cardRandomed.get(position))){
                        addDataToHitung(cardRandomed.get(position));
                    }
                    card3Clicked = true;
                }
                break;
            case 3:
                if(card4Clicked){
                    removeDataFromHitung(cardRandomed.get(position));
                    card4Clicked = false;
                }else {
                    if(checkLastCard(cardRandomed.get(position))){
                        addDataToHitung(cardRandomed.get(position));
                    }
                    card4Clicked = true;
                }
        }
        Log.i("ceklistsize", "recyclerItemClick: " + selectedCard.size());
    }

    //selectedcard click controller==> removing card from listcardto calculate
    @Override
    public void recyclerItemMainClick(View v, int position) {
        removeDataFromHitung(selectedCard.get(position));
    }

    //initiate flag for everycard if clicked once true else false
    void initiateCardCondition(){
        card1Clicked = false;
        card2Clicked = false;
        card3Clicked = false;
        card4Clicked = false;
    }

    //add operator bracket
    void addOperatorBracket(String bracketOperator){
        if(checkLastCard(cardOperator.get(4))){
            switch (bracketOperator){
                case "(" :
                    selectedCard.add(cardOperator.get(4));
                    break;
                case ")" :
                    selectedCard.add(cardOperator.get(5));
                default:
                    break;
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    //add operator to Calculation
    void addOperatorHitung(String operator){
        if(checkLastCard(cardOperator.get(0))){
            switch (operator){
                case "*" :
                    selectedCard.add(cardOperator.get(2));
                    Log.i("ceklistisioperator", "addOperatorHitung: " + cardOperator.get(2).getJenis());
                    break;
                case "/" :
                    selectedCard.add(cardOperator.get(3));
                    Log.i("ceklistisioperator", "addOperatorHitung: " + cardOperator.get(3).getJenis());
                    break;
                case "+" :
                    selectedCard.add(cardOperator.get(0));
                    Log.i("ceklistisioperator", "addOperatorHitung: " + cardOperator.get(0).getJenis());
                    break;
                case "-" :
                    selectedCard.add(cardOperator.get(1));
                    Log.i("ceklistisioperator", "addOperatorHitung: " + cardOperator.get(1).getJenis());
                    break;
                default:
                    break;
            }
        }
        mAdapter.notifyDataSetChanged();
    }


    private int doPrioritiesToCalculation(List<Card> listCardtoCalculate){
        List<Card> listTambahan = new ArrayList<>();
        boolean calculatePriority = false;
        int startingIndexReplace = 0;
        int finishedIndexReplace = 0;
        int counterBracket = 0;
        for(int i=0; i<listCardtoCalculate.size(); i++){
            if(listCardtoCalculate.get(i).getTampilan().equals(")")){
                counterBracket--;
                if(counterBracket==0){
                    finishedIndexReplace = i;
                    break;
                }
            }
            if(listCardtoCalculate.get(i).getTampilan().equals("(")){
                if(!calculatePriority){
                    startingIndexReplace = i;
                }
                calculatePriority = true;
                counterBracket++;
            }
        }
        if(calculatePriority){
            for(int i=startingIndexReplace + 1; i<finishedIndexReplace; i++){
                listTambahan.add(listCardtoCalculate.get(i));
            }
            for(int i = 0 ; i<=finishedIndexReplace-startingIndexReplace; i++){
                listCardtoCalculate.remove(startingIndexReplace);
            }
            int resultFromPriority = doPrioritiesToCalculation(listTambahan);
            Card resultCard = new Card();
            resultCard.setValue(resultFromPriority);
            resultCard.setTampilan(String.valueOf(resultFromPriority));
            listCardtoCalculate.add(startingIndexReplace, resultCard);
        }
        int priorityResult = doCalculation(listCardtoCalculate);
        return priorityResult;
    }

    //method to calculate all stacked card in selectedcard list
    private int doCalculation(List<Card> listToCalculate){
        for(Card a : listToCalculate){
            Log.i("ceklistyangdihitung", "doCalculation: " + a.getTampilan());
        }
        List<Integer> listOperator = new ArrayList<>();
        List<Integer> listAngka = new ArrayList<>();
        int lastOperator = -1;
        try {
            for(int a = 0; a<listToCalculate.size(); a++){
                if(listToCalculate.get(a).getTampilan().equalsIgnoreCase("*")
                        || listToCalculate.get(a).getTampilan().equalsIgnoreCase("+")
                        || listToCalculate.get(a).getTampilan().equalsIgnoreCase("/")
                        || listToCalculate.get(a).getTampilan().equalsIgnoreCase("-")){
                    listOperator.add(a);
                    String angka = "";
                    for(int i = lastOperator + 1; i<a; i++){
                        angka += String.valueOf(listToCalculate.get(i).getValue());
                    }
                    listAngka.add(Integer.valueOf(angka));
                    lastOperator = a;
                }
                if(a==listToCalculate.size()-1){
                    String angka = "";
                    for(int j=lastOperator+1; j<listToCalculate.size(); j++){
                        angka += String.valueOf(listToCalculate.get(j).getValue());
                    }
                    listAngka.add(Integer.valueOf(angka));
                }
            }
            for(int i = 0; i < listOperator.size(); i++){
                if(listToCalculate.get(listOperator.get(i)).getTampilan().equalsIgnoreCase("*")){
                    int angkaGanti = listAngka.get(i) * listAngka.get(i+1);
                    listOperator.remove(i);
                    listAngka.remove(i);
                    listAngka.add(i, angkaGanti);
                    listAngka.remove(i+1);
                    i--;
                } else if(listToCalculate.get(listOperator.get(i)).getTampilan().equalsIgnoreCase("/")){
                    int angkaGanti = listAngka.get(i) / listAngka.get(i+1);
                    listOperator.remove(i);
                    listAngka.remove(i);
                    listAngka.add(i, angkaGanti);
                    listAngka.remove(i+1);
                    i--;
                }
            }

            for(int i = 0; i<listOperator.size(); i++){
                if(listToCalculate.get(listOperator.get(i)).getTampilan().equalsIgnoreCase("+")){
                    int angkaGanti = listAngka.get(i) + listAngka.get(i+1);
                    listAngka.remove(i+1);
                    listAngka.add(i+1, angkaGanti);
                }else if(listToCalculate.get(listOperator.get(i)).getTampilan().equalsIgnoreCase("-")){
                    int angkaGanti = listAngka.get(i) - listAngka.get(i+1);
                    listAngka.remove(i+1);
                    listAngka.add(i+1, angkaGanti);
                }
            }
            Log.i("cekdimana", "doCalculation: " + listAngka.get(listAngka.size()-1));

        } catch (Exception e){
            Log.i("cekerror", "doCalculation: " + e.toString());
        }
        return listAngka.get(listAngka.size()-1);
    }

    //method to check the last card in the list if its the same type dont add it to calculation
    private boolean checkLastCard(Card clickedCark){
        Log.i("selectedCard", "checkLastCard: " + clickedCark.getJenis());
        if(selectedCard.size()>0){
            Card lastCard = selectedCard.get(selectedCard.size()-1);
            Log.i("cekcardLast", "checkLastCard: " + lastCard.getJenis());
            if(lastCard.getJenis() == 5){
                if(clickedCark.getJenis() == 5){
                    return false;
                }else {
                    return true;
                }
            } else if(lastCard.getJenis()==6){
                if(clickedCark.getJenis() == 5){
                    return true;
                } else {
                    return true;
                }
            } else {
                if(clickedCark.getJenis() == 5){
                    return true;
                }else if (clickedCark.getJenis() == 6){
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            if(clickedCark.getJenis() == 5){
                return false;
            }else {
                return true;
            }
        }

    }
}

