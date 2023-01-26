package matej.lamza.mycoach

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import matej.lamza.mycoach.ui.theme.MyCoachTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCoachTheme {
                MyCoachApp()
            }
        }
    }
}

