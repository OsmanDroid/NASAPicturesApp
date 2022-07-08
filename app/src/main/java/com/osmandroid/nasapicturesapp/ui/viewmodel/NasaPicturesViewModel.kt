package com.osmandroid.nasapicturesapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.osmandroid.nasapicturesapp.data.model.NasaItem
import com.osmandroid.nasapicturesapp.data.repository.NasaRepo
import com.osmandroid.nasapicturesapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NasaPicturesViewModel @Inject constructor(private val nasaRepo: NasaRepo) : ViewModel() {

    private val _picturesList = MutableLiveData<Resource<List<NasaItem>>>()
    val picturesList get() = _picturesList as LiveData<Resource<List<NasaItem>>>

    init {
        getPicturesList()
    }

    fun getPicturesList() {
        viewModelScope.launch {
            _picturesList.postValue(Resource.Loading)
            nasaRepo.getPictures().catch {
                _picturesList.postValue(Resource.Failure(errorBody = it))
            }.collect {
                _picturesList.postValue(Resource.Success(it.reversed()))
            }
        }
    }
}