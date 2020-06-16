package com.muslim.taskmadarsoft.Java.data;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Infoo.class}, version = 2)
public abstract class InfooDatabase extends RoomDatabase {

    private static InfooDatabase instance;

    public abstract
    InfooDao InfooDao();

    public static synchronized
    InfooDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder( context.getApplicationContext(),
                                             InfooDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private InfooDao infooDao;

        private PopulateDbAsyncTask(InfooDatabase db) {
            infooDao = db.InfooDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }
    }
}
