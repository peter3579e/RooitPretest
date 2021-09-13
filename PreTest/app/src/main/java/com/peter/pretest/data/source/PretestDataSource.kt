package com.peter.pretest.data.source

import com.peter.pretest.data.Articles

/**
 *
 * Main entry point for accessing Pretest sources.
 */

interface PretestDataSource {

    suspend fun getNews(country: String, keyApi: String): com.peter.pretest.data.Result<Articles>

    suspend fun insertData(title: String, name: String, image: String)

}