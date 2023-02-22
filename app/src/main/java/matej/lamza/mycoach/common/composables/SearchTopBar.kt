package matej.lamza.mycoach.common.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import matej.lamza.mycoach.ui.theme.CloseIcon
import matej.lamza.mycoach.ui.theme.SearchIcon
import matej.lamza.mycoach.ui.theme.Typography

@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onClose: () -> Unit,
    onSearchClicked: (String) -> Unit,
    placeholder: @Composable () -> Unit = { Text("Search...", modifier = Modifier.alpha(ContentAlpha.medium)) }
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.primary
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = onTextChange,
            placeholder = placeholder,
            textStyle = Typography.body2.copy(color = Color.White),
            singleLine = true,
            leadingIcon = {
                IconButton(modifier = Modifier.alpha(ContentAlpha.medium), onClick = { }) { SearchIcon() }
            },
            trailingIcon = {
                IconButton(onClick = {
                    if (text.isNotEmpty()) onTextChange("")
                    else onClose.invoke()
                }) { CloseIcon() }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearchClicked(text) })
        )
    }
}
