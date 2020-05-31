package com.realm.todoappfullsync.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.Index
import io.realm.annotations.PrimaryKey
import java.util.*

open class Project : RealmObject() {

    @PrimaryKey
    var id = "singleton"
    var owner: String = ""
    var name: String = ""
    var timestamp: Date = Date()
    var tasks: RealmList<Task> = RealmList()

}