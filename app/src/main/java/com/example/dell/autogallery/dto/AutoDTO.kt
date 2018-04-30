package com.example.dell.autogallery.dto

/**
 * Created by ${DERYA_YANAL} on 23.04.2018.
 */
class AutoDTO{

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
    var security:List<SecurityEquipmentDTO>?=null
    var comfort:List<ComfortAccessoriesDTO>?=null


    constructor(){}

    constructor(profilePhoto: String?, brand: String?, model: String?, modelYear: String?, price: String?, km: String?, gear: String?, fuelType: String?, engineCapacity: String?, fuelComsumption: String?, enginePower: String?,
                security: List<SecurityEquipmentDTO>?,comfort:List<ComfortAccessoriesDTO>) {
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
        this.security = security
        this.comfort=comfort
    }
}