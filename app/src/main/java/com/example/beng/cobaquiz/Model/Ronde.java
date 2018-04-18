package com.example.beng.cobaquiz.Model;

import java.util.List;

/**
 * Created by Beng on 3/12/2018.
 */

public class Ronde {
    private List<Card> listKartu;
    private List<User> listUserBenar;
    private int idRonde;
    private int idRoom;

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public void setIdRonde(int idRonde) {
        this.idRonde = idRonde;
    }

    public int getIdRonde() {
        return idRonde;
    }

    public List<Card> getListKartu() {
        return listKartu;
    }

    public void setListKartu(List<Card> listKartu) {
        this.listKartu = listKartu;
    }

    public List<User> getListUserBenar() {
        return listUserBenar;
    }

    public void setListUserBenar(List<User> listUserBenar) {
        this.listUserBenar = listUserBenar;
    }

    public String getListCardinString(){
        String listCardinString = "";
        for(int i=0; i<this.listKartu.size(); i++){
            if(i == this.listKartu.size()-1){
                listCardinString += this.listKartu.get(i).getIdCard();
            }
            else {
                listCardinString += this.listKartu.get(i).getIdCard() + ",";
            }
        }
        return listCardinString;
    }

    public String getUserCorrectinString(){
        String userCorrectString = "";;
        for(int i=0; i<listUserBenar.size(); i++){
            userCorrectString += i == listUserBenar.size()-1 ? listUserBenar.get(i).getNamaUser() : listUserBenar.get(i).getNamaUser() + ",";
        }
        return userCorrectString;
    }
}
