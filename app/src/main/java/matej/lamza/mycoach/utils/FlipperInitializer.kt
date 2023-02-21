package matej.lamza.mycoach.utils

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.databases.impl.SqliteDatabaseDriver
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.leakcanary2.LeakCanary2FlipperPlugin
import com.facebook.flipper.plugins.navigation.NavigationFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import matej.lamza.mycoach.BuildConfig
import java.io.File

class FlipperInitializer(private val application: Application) {

    private val inspectorPlugin: InspectorFlipperPlugin by lazy {
        InspectorFlipperPlugin(
            application,
            DescriptorMapping.withDefaults()
        )
    }

    private val networkPlugin: NetworkFlipperPlugin by lazy { NetworkFlipperPlugin() }
    private val leakCanaryPlugin: LeakCanary2FlipperPlugin by lazy { LeakCanary2FlipperPlugin() }
    private val sharedPreferencesPlugin: SharedPreferencesFlipperPlugin by lazy {
        SharedPreferencesFlipperPlugin(application, BuildConfig.APPLICATION_ID)
    }
    private val databasePlugin by lazy {
        val databaseDriver =
            SqliteDatabaseDriver(application.applicationContext) {
                val databaseFiles = arrayListOf<File>()
                for (databaseName in application.databaseList()) {
                    databaseFiles.add(application.getDatabasePath(databaseName));
                }
                databaseFiles.add(application.getDatabasePath("mycoach_db"))
                databaseFiles;
            }

        DatabasesFlipperPlugin(databaseDriver)
    }
    private val navigationPlugin by lazy { NavigationFlipperPlugin.getInstance() }

    fun initialize() {
        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(application)) {
            AndroidFlipperClient.getInstance(application).apply {
                addPlugin(inspectorPlugin)
                addPlugin(networkPlugin)
                addPlugin(leakCanaryPlugin)
                addPlugin(InspectorFlipperPlugin(application, DescriptorMapping.withDefaults()))
                addPlugin(sharedPreferencesPlugin)
                addPlugin(databasePlugin)
                addPlugin(navigationPlugin)
            }.start()
        }
    }
}
