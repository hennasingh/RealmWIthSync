package com.realm.todoappfullsync.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import java.util.*

open class Task() : RealmObject() {

    constructor(name: String) : this() {
        this.body = name
    }

    @PrimaryKey
    var itemId: String = UUID.randomUUID().toString()

    var body: String = ""

    var isDone: Boolean = false

    var timeStamp: Date = Date()

    companion object {
        val FIELD_ID = "itemId"
        val FIELD_BODY = "body"
        val FIELD_DONE = "isDone"
        val FIELD_TIMESTAMP = "timestamp"
    }
}