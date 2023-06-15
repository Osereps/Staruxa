package com.example.app20kotlin.sampledata
import java.io.Serializable
class ListTwo:Serializable {
    private var Users_Key: String
        get() = field
        set(value) {field=value}
    private var Zametki_Text: String
        get() = field
        set(value) {field=value}

    constructor(Users_Key: String, Zametki_Text: String) {
        this.Users_Key = Users_Key
        this.Zametki_Text = Zametki_Text
    }
}