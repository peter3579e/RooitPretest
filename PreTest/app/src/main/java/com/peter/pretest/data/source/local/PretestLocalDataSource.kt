package com.peter.pretest.data.source.local

import android.content.Context
import com.peter.pretest.data.Articles
import com.peter.pretest.data.Result
import com.peter.pretest.data.source.PretestDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *
 * Concrete implementation of a Pretest source as a db.
 */

class PretestLocalDataSource(val context: Context) : PretestDataSource {
    override suspend fun getNews(country: String, keyApi: String): Result<Articles> {
        TODO("Not yet implemented")
    }

    override suspend fun insertData(title: String, name: String, image: String) {
        withContext(Dispatchers.IO) {
            PretestDatabase.insertData(title, name, image)
        }
    }
}