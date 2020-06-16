package com.muslim.taskmadarsoft.kotlin.data

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class InfoRepository(application: Application) {

    private var infoDao: InfoDao

    private var allInfo: LiveData<List<Info>>

    init {
        val database: InfoDatabase = InfoDatabase.getInstance(
            application.applicationContext
        )!!
        infoDao = database.noteDao()
        allInfo = infoDao.getAllInfo()
    }

    fun insert(info: Info) {
        val insertNoteAsyncTask = InsertInfoAsyncTask(infoDao).execute(info)
    }

    fun update(info: Info) {
        val updateNoteAsyncTask = UpdateInfoAsyncTask(infoDao).execute(info)
    }


    fun delete(info: Info) {
        val deleteNoteAsyncTask = DeleteInfoAsyncTask(infoDao).execute(info)
    }

    fun deleteAllNotes() {
        val deleteAllNotesAsyncTask = DeleteAllInfoAsyncTask(
            infoDao
        ).execute()
    }

    fun getAllInfo(): LiveData<List<Info>> {
        return allInfo
    }

    companion object {
        private class InsertInfoAsyncTask(infoDao: InfoDao) : AsyncTask<Info, Unit, Unit>() {
            val noteDao = infoDao

            override fun doInBackground(vararg p0: Info?) {
                noteDao.insert(p0[0]!!)
            }
        }

        private class UpdateInfoAsyncTask(infoDao: InfoDao) : AsyncTask<Info, Unit, Unit>() {
            val noteDao = infoDao

            override fun doInBackground(vararg p0: Info?) {
                noteDao.update(p0[0]!!)
            }
        }

        private class DeleteInfoAsyncTask(infoDao: InfoDao) : AsyncTask<Info, Unit, Unit>() {
            val noteDao = infoDao

            override fun doInBackground(vararg p0: Info?) {
                noteDao.delete(p0[0]!!)
            }
        }

        private class DeleteAllInfoAsyncTask(infoDao: InfoDao) : AsyncTask<Unit, Unit, Unit>() {
            val noteDao = infoDao

            override fun doInBackground(vararg p0: Unit?) {
                noteDao.deleteAllInfo()
            }
        }
    }
}