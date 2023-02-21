package matej.lamza.mycoach.di.modules

import androidx.room.Room
import matej.lamza.mycoach.data.local.database.DatabaseConstants
import matej.lamza.mycoach.data.local.database.MyCoachDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val databaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), MyCoachDatabase::class.java, DatabaseConstants.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<MyCoachDatabase>().provideDAO() }
}
