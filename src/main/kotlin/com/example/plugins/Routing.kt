package com.example.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import kotlinx.serialization.Serializable
import org.litote.kmongo.*

fun Application.configureRouting() {

    routing {
        get("/") {
            call.respondText("Hello World!")

        }

        post("/login") {
            val client = KMongo.createClient()
            val db = client.getDatabase("fbDataBase")
            val col = db.getCollection<User>("users")
            val user = call.receive<User>()
            col.insertOne(user)
            call.respond(col.findOne(User::name eq "Ahmed") as User)
        }

    }
}

@Serializable
data class User(val name: String, val phone: String)




