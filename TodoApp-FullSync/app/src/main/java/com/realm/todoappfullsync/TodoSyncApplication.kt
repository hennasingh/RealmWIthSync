package com.realm.todoappfullsync

import android.app.Application
import io.realm.Realm

class TodoSyncApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
    }
}