package com.muslim.taskmadarsoft.Java.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class InfooRepository {
    private InfooDao infooDao;
    private LiveData<List<Infoo>> allInfoo;

    public
    InfooRepository(Application application) {
        InfooDatabase database = InfooDatabase.getInstance( application);
        infooDao = database.InfooDao();
        allInfoo = infooDao.getAllInfoo();
    }

    public void insert(Infoo infoo) {
        new InsertNoteAsyncTask( infooDao ).execute( infoo );
    }

    public void update(Infoo infoo) {
        new UpdateInfooAsyncTask( infooDao ).execute( infoo );
    }

    public void delete(Infoo infoo) {
        new DeleteInfooAsyncTask( infooDao ).execute( infoo );
    }

    public void deleteAllinfoo() {
        new DeleteAllInfooAsyncTask( infooDao ).execute();
    }

    public
    LiveData <List<Infoo>> getAllInfoo() {
        return allInfoo;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Infoo, Void, Void> {
        private InfooDao infooDao;

        private InsertNoteAsyncTask(InfooDao infooDao) {
            this.infooDao = infooDao;
        }

        @Override
        protected Void doInBackground(Infoo... infoos) {
            infooDao.insert( infoos[0]);
            return null;
        }
    }

    private static class UpdateInfooAsyncTask extends AsyncTask<Infoo, Void, Void> {
        private InfooDao infooDao;

        private
        UpdateInfooAsyncTask(InfooDao infooDao) {
            this.infooDao = infooDao;
        }

        @Override
        protected Void doInBackground(Infoo... infoos) {
            infooDao.update( infoos[0]);
            return null;
        }
    }

    private static class DeleteInfooAsyncTask extends AsyncTask<Infoo, Void, Void> {
        private InfooDao infooDao;

        private
        DeleteInfooAsyncTask(InfooDao infooDao) {
            this.infooDao = infooDao;
        }

        @Override
        protected Void doInBackground(Infoo... infoos) {
            infooDao.delete( infoos[0]);
            return null;
        }
    }

    private static class DeleteAllInfooAsyncTask extends AsyncTask<Void, Void, Void> {
        private InfooDao infooDao;

        private
        DeleteAllInfooAsyncTask(InfooDao infooDao) {
            this.infooDao = infooDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            infooDao.deleteAllNotes();
            return null;
        }
    }
}