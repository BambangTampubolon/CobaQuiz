package com.example.beng.cobaquiz.Model;

import java.util.List;

/**
 * Created by Beng on 3/12/2018.
 */

public class Room {
    private List<User> listUser;
    private List<Ronde> listRonde;
    private int idRoom;

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public List<Ronde> getListRonde() {
        return listRonde;
    }

    public void setListRonde(List<Ronde> listRonde) {
        this.listRonde = listRonde;
    }

    public void setListUser(List<User> listUser) {
        this.listUser = listUser;
    }

    public List<User> getListUser() {
        return listUser;
    }

    public String getListUserinString(){
        String listUserinString = "";
        for(int i = 0; i<this.listUser.size(); i++){
            if(i == this.listUser.size()-1){
                listUserinString += this.getListUser().get(i).getIdUser();
            }
            else {
                listUserinString += this.getListUser().get(i).getIdUser()+",";
            }
        }
        return listUserinString;
    }
}
