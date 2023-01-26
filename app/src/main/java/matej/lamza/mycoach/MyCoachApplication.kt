package matej.lamza.mycoach

import android.app.Application
import com.facebook.soloader.SoLoader
import matej.lamza.mycoach.di.MyCoachDI
import matej.lamza.mycoach.utils.FlipperInitializer
import org.koin.android.ext.android.inject

class MyCoachApplication : Application() {

    private val myCoachDI by lazy { MyCoachDI(this) }
    private val flipperInitializer by inject<FlipperInitializer>()

    override fun onCreate() {
        super.onCreate()
        SoLoader.init(this, false)

        myCoachDI.initialize()
        flipperInitializer.initialize()
    }
}
