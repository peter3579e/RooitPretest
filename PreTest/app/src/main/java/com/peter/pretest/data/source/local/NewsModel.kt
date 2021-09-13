package com.peter.pretest.data.source.local

import com.peter.pretest.data.News
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class NewsModel(
    var title: String? = null,
    var name: String? = null,
    var image: String? = null
) : RealmObject()

