package matej.lamza.mycoach.ui.theme

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable

@Composable
fun SearchIcon() = Icon(
    imageVector = Icons.Filled.Search,
    contentDescription = "Search"
)

@Composable
fun CloseIcon() = Icon(
    imageVector = Icons.Default.Close,
    contentDescription = "Close"
)
