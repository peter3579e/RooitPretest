package com.peter.pretest.data.source

import com.peter.pretest.data.Articles
import com.peter.pretest.data.Result
import com.peter.pretest.data.source.local.PretestLocalDataSource

/**
 *
 * Concrete implementation to load Pretest sources.
 */

class DefaultPreTestRepository(
    private val pretestRemoteDataSource: PretestDataSource,
    private val pretestLocalDataSource: PretestDataSource
) : PretestRepository {

    override suspend fun getNews(country: String, keyApi: String): Result<Articles> {
        return pretestRemoteDataSource.getNews(country, keyApi)
    }

    override suspend fun insertData(title: String, name: String, image: String) {
        return pretestLocalDataSource.insertData(title, name, image)
    }

}