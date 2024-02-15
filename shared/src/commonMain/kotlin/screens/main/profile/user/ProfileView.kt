package screens.main.profile.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.moriatsushi.insetsx.statusBars
import ui.MRTheme

@Composable
fun ProfileView(state: UserState, doAction: (UserAction) -> Unit) {
    Scaffold(
        modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
        backgroundColor = MRTheme.colors.background
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                imageVector = Icons.Default.Person,
                colorFilter = ColorFilter.tint(MRTheme.colors.primary),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(84.dp)
                    .clip(CircleShape)                       // clip to the circle shape
                    .border(2.dp, MRTheme.colors.primary, CircleShape)
            )
            Text(
                text = state.name,
                modifier = Modifier
                    .fillMaxWidth(),
                style = MRTheme.typography.header
            )
            Text(
                text = state.login,
                modifier = Modifier
                    .fillMaxWidth(),
                style = MRTheme.typography.hint
            )
            ProfileSettingsBox(doAction)
        }

    }
}
