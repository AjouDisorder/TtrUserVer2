package com.example.ttruserver2

class UserData {
    companion object{
        private val TAG = UserData::class.java.simpleName
        private lateinit var objectId: String
        private lateinit var userId: String
        private lateinit var nickname: String
        private lateinit var restaurantOid: String
        private lateinit var restaurantTitle: String


        fun setOid(id:String){
            objectId = id
        }
        fun getOid() : String{
            return objectId
        }

        fun setBid(id:String){
            userId = id
        }
        fun getBid() : String{
            return userId
        }

//        fun setROid(id:String){
//            restaurantOid = id
//        }
//        fun getROid() : String{
//            return restaurantOid
//        }
//
//        fun setRTitle(title:String){
//            restaurantTitle = title
//        }
//        fun getRTitle() : String{
//            return restaurantTitle
//        }
    }
}