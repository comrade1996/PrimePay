package com.example.primepay.data.model

//import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
    val name: String,
    val email: String,
    val access_token:String
)
{
//    class Deserializer: ResponseDeserializable<Array<LoggedInUser>> {
//        override fun deserialize(content: String): Array<LoggedInUser>? = Gson().fromJson(content, Array<LoggedInUser>::class.java)
//    }
}
