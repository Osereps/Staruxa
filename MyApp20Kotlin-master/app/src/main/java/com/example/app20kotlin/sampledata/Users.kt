package com.example.app20kotlin.sampledata

class Users {
    private var email: String
    get() = field
    set(value) {field=value}

    private var key: String
        get() = field
        set(value) {field=value}
    private var Password: String
        get() = field
        set(value) {field=value}

    constructor(email: String, key: String, Password: String) {
        this.email = email
        this.key = key
        this.Password = Password
    }


}