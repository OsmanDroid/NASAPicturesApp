package com.osmandroid.nasapicturesapp.data.repository

import android.content.Context
import com.osmandroid.nasapicturesapp.data.model.NasaItem
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject


class NasaRepo @Inject constructor(@ApplicationContext private val context: Context) {

    fun getPictures(): Flow<List<NasaItem>> {
        val jsonData = context.assets.open("data.json").bufferedReader().use { it.readText() }
        return flow { emit(Json.decodeFromString(jsonData)) }
    }
}