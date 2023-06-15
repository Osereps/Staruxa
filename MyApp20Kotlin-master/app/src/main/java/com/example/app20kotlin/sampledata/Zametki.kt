package com.example.app20kotlin.sampledata

class Zametki {
    private var ID: String
    private var Users_Key: String
    private var Zametki_Text: String

    constructor(ID: String, Users_Key: String, Zametki_Text: String) {
        this.ID = ID
        this.Users_Key = Users_Key
        this.Zametki_Text = Zametki_Text
    }

    fun getID():String{
        return ID
    }
    fun getUsers_Key():String{
        return Users_Key
    }
    fun getZametki_Text():String{
        return Zametki_Text
    }
    fun setID(value : String){
        ID= value
    }
    fun setUsers_Key(value : String) {
        Users_Key = value
    }
    fun setZametki_Text(value : String){
        Zametki_Text= value
    }
}