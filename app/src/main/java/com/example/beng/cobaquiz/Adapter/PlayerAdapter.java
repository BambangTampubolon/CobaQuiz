package com.example.beng.cobaquiz.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.beng.cobaquiz.Interface.EditTextOnActionListener;
import com.example.beng.cobaquiz.Model.User;
import com.example.beng.cobaquiz.R;

import java.util.List;

/**
 * Created by Beng on 5/7/2018.
 */

public class PlayerAdapter extends BaseAdapter{
    List<User> listUser;
    Context context;
    private static EditTextOnActionListener editTextOnActionListener;

    public PlayerAdapter(Context context, List<User> userList, EditTextOnActionListener editTextOnActionListener){
        this.listUser = userList;
        this.context = context;
        this.editTextOnActionListener = editTextOnActionListener;
    }

    @Override
    public int getCount() {
        return listUser.size();
    }

    @Override
    public User getItem(int i) {
        return listUser.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listUser.get(i).getIdUser();
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        PlayerViewHolder playerViewHolder;
        if(view == null){
            LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
            view = layoutInflater.inflate(R.layout.player_layout,null);
            playerViewHolder = new PlayerViewHolder(view);
            view.setTag(playerViewHolder);
        }else {
            playerViewHolder = (PlayerViewHolder) view.getTag();
        }
        playerViewHolder.userName.setImeOptions( i != listUser.size()-1 ? EditorInfo.IME_ACTION_NEXT : EditorInfo.IME_ACTION_DONE);
        playerViewHolder.userName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if(textView.getText()==null || textView.getText().length()<1){
                    return true;
                }
                editTextOnActionListener.EditTextActionListener(textView, i, actionId);
                return  false;
            }
        });
        switch (i){
            case 1:
                playerViewHolder.userColour.setBackgroundResource(R.color.colorLight);
                break;
            case 2:
                playerViewHolder.userColour.setBackgroundResource(R.color.black_overlay);
                break;
            case 3:
                playerViewHolder.userColour.setBackgroundResource(R.color.red);
                break;
            case 4:
                playerViewHolder.userColour.setBackgroundResource(R.color.brown);
                break;
            case 5:
                playerViewHolder.userColour.setBackgroundResource(R.color.green);
                break;
            case 6:
                playerViewHolder.userColour.setBackgroundResource(R.color.colorLight);
                break;
            case 7:
                playerViewHolder.userColour.setBackgroundResource(R.color.yelow);
                break;
            case 8:
                playerViewHolder.userColour.setBackgroundResource(R.color.pink);
                break;
            default:
                break;
        }
        User user = getItem(i);
        if(user!=null){
            playerViewHolder.userName.setText(user.getNamaUser());
        }

        return view;
    }

    public class PlayerViewHolder extends RecyclerView.ViewHolder implements TextView.OnEditorActionListener{
        private TextView userColour;
        private EditText userName;

        public PlayerViewHolder(View itemView) {
            super(itemView);
            userColour = (TextView) itemView.findViewById(R.id.player_colour);
            userName = (EditText) itemView.findViewById(R.id.player_name);
        }


        @Override
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            editTextOnActionListener.EditTextActionListener(textView, this.getLayoutPosition(), i);
            return false;
        }
    }
}
