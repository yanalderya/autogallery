package com.example.dell.autogallery.dto

/**
 * Created by ${DERYA_YANAL} on 8.05.2018.
 */
class CrashDTO{

    var frontRightFender:String?=null
    var rearRightFender: String? = null
    var frontLeftFender: String? = null
    var rearLeftFender:String? =null
    var frontBumper: String? = null
    var rearBumper:String? =null
    var rightFrontDoor: String? = null
    var leftFrontDoor:String? =null
    var rightRearDoor: String? = null
    var leftRearDoor: String? = null
    var luggage:String? =null
    var ceiling:String?=null
    var bonnet:String?=null

    constructor(){}

    constructor(frontRightFender: String?, rearRightFender: String?, frontLeftFender: String?, rearLeftFender: String?, frontBumper: String?, rearBumper: String?, rightFrontDoor: String?, leftFrontDoor: String?, rightRearDoor: String?, leftRearDoor: String?, luggage: String?, ceiling: String?, bonnet: String?) {
        this.frontRightFender = frontRightFender
        this.rearRightFender = rearRightFender
        this.frontLeftFender = frontLeftFender
        this.rearLeftFender = rearLeftFender
        this.frontBumper = frontBumper
        this.rearBumper = rearBumper
        this.rightFrontDoor = rightFrontDoor
        this.leftFrontDoor = leftFrontDoor
        this.rightRearDoor = rightRearDoor
        this.leftRearDoor = leftRearDoor
        this.luggage = luggage
        this.ceiling = ceiling
        this.bonnet = bonnet
    }


}