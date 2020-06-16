package com.muslim.taskmadarsoft.kotlin.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.muslim.taskmadarsoft.kotlin.data.Info
import com.muslim.taskmadarsoft.kotlin.data.InfoRepository


class InfoViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: InfoRepository =
            InfoRepository(application)
    private var allInfo: LiveData<List<Info>> = repository.getAllInfo()

    fun insert(infos: Info) {
        repository.insert(infos)
    }

    fun update(infos: Info) {
        repository.update(infos)
    }

    fun delete(infos: Info) {
        repository.delete(infos)
    }

    fun deleteAllInfo() {
        repository.deleteAllNotes()
    }

    fun getAllInfo(): LiveData<List<Info>> {
        return allInfo
    }
}