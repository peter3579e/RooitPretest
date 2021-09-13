package com.peter.pretest.data.source.remote

import com.peter.pretest.R
import com.peter.pretest.data.Articles
import com.peter.pretest.data.source.PretestDataSource
import com.peter.pretest.network.PreTestApi
import com.peter.pretest.util.Logger
import com.peter.pretest.util.Util.getString
import com.peter.pretest.util.Util.isInternetConnected

/**
 *
 * Implementation of the Pretest source that from network.
 */

object PretestRemoteDataSource : PretestDataSource {

    override suspend fun getNews(
        country: String,
        keyApi: String
    ): com.peter.pretest.data.Result<Articles> {

        if (!isInternetConnected()) {
            return com.peter.pretest.data.Result.Fail(getString(R.string.internet_not_connected))
        }

        return try {

            val result = PreTestApi.retrofitService.getNews(country, keyApi)

            com.peter.pretest.data.Result.Success(result)
        } catch (e: Exception) {
            Logger.w("[${this::class.simpleName}] exception=${e.message}")
            com.peter.pretest.data.Result.Error(e)
        }
    }

    override suspend fun insertData(title: String, name: String, image: String) {
        TODO("Not yet implemented")
    }


}