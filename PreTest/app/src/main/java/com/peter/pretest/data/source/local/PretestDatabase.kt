package com.peter.pretest.data.source.local

import io.realm.Realm

object PretestDatabase {

    fun insertData(title: String, name: String, image: String) {
        val task = NewsModel(title, name, image)
        val realm = Realm.getDefaultInstance()

        realm.executeTransaction { realm ->
            realm.copyToRealm(task)
        }
    }
}