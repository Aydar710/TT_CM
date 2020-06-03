package com.aydar.tt_cm.featurepersons.domain

import com.aydar.tt_cm.data.Person
import com.aydar.tt_cm.data.repository.PersonsRepository

class ShowPersonsUseCase(private val repository: PersonsRepository) {

    fun invoke(): List<Person> {
        return repository.getPersons()
    }
}