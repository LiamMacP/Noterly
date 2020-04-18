package apps.liamm.noterly.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import apps.liamm.noterly.data.daos.CategoryDao;
import apps.liamm.noterly.data.entities.CategoryEntity;
import apps.liamm.noterly.data.entities.NoteEntity;

@Database(entities = {CategoryEntity.class, NoteEntity.class}, version = 1, exportSchema = false)
public abstract class NoterlyRoomDatabase extends RoomDatabase {

    public abstract CategoryDao categoryDao();

    private static volatile NoterlyRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static NoterlyRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NoterlyRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NoterlyRoomDatabase.class, "noterly_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
