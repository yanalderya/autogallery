package com.example.dell.autogallery.dto

import java.io.Serializable

/**
 * Created by ${DERYA_YANAL} on 23.04.2018.
 */
class AutoDTO:Serializable{

    var id:String?=null
    var profilePhoto: String? = null
    var brand: String? = null
    var model:String? =null
    var modelYear: String? = null
    var price: String? = null
    var km:String? =null
    var gear: String? = null
    var fuelType:String? =null
    var engineCapacity: String? = null
    var fuelComsumption: String? = null
    var enginePower:String? =null
    var description:String?=null
    var carCrash:String?=null


    constructor(){}

    constructor(profilePhoto: String?, brand: String?, model: String?, modelYear: String?, price: String?, km: String?, gear: String?, fuelType: String?, engineCapacity: String?, fuelComsumption: String?, enginePower: String?, description: String?, carCrash: String?) {
        this.profilePhoto = profilePhoto
        this.brand = brand
        this.model = model
        this.modelYear = modelYear
        this.price = price
        this.km = km
        this.gear = gear
        this.fuelType = fuelType
        this.engineCapacity = engineCapacity
        this.fuelComsumption = fuelComsumption
        this.enginePower = enginePower
        this.description = description
        this.carCrash = carCrash
    }

}