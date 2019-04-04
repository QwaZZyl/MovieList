package com.karavatskiy.serhii.requestmovieslist.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import com.karavatskiy.serhii.requestmovieslist.data.model.MovieDescription;

@Database(entities = {MovieDescription.class}, version = 1)
@TypeConverters(GenresTypeConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    public abstract Dao daoAccess();
}