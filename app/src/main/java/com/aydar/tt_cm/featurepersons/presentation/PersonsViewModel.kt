package com.aydar.tt_cm.featurepersons.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aydar.tt_cm.data.Person
import com.aydar.tt_cm.featurepersons.domain.ShowPersonsUseCase

class PersonsViewModel(private val showPersonsUseCase: ShowPersonsUseCase) : ViewModel() {

    private val _personsLiveData = MutableLiveData<List<Person>>()
    val personsLiveData: LiveData<List<Person>>
        get() = _personsLiveData

    fun showPersons() {
        val persons = showPersonsUseCase.invoke()
        _personsLiveData.value = persons
    }
}