package com.example.dell.autogallery.dto

/**
 * Created by ${DERYA_YANAL} on 19.04.2018.
 */
class UserDTO {

    var isim: String? = null
    var email: String? = null
    var phoneNumber:String? =null

    constructor(isim:String,email:String,phoneNumber:String){
        this.isim=isim
        this.email=email
        this.phoneNumber=phoneNumber
    }

    constructor(){}

}