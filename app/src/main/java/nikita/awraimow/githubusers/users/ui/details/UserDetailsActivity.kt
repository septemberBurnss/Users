package nikita.awraimow.githubusers.users.ui.details

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import dagger.hilt.android.AndroidEntryPoint
import nikita.awraimow.githubusers.ui.theme.GithubUsersTheme
import nikita.awraimow.githubusers.ui.theme.Values
import nikita.awraimow.githubusers.ui.theme.Values.SmallPadding

@AndroidEntryPoint
class UserDetailsActivity: ComponentActivity() {

    private val userDetailsViewModel: UserDetailsViewModel by viewModels()

    @OptIn(ExperimentalGlideComposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubUsersTheme {
                val state = userDetailsViewModel.uiState.collectAsState().value

                if (state is UserDetailsScreenState.Loaded) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        GlideImage(
                            model = state.userDetails.profilePicUrl,
                            contentDescription = null,
                            modifier = Modifier.clip(RoundedCornerShape(Values.ProfilePicRadius))
                        )
                        Text(
                            text = state.userDetails.name,
                            style = TextStyle(fontWeight = FontWeight.Bold)
                        )
                        Text(text = state.userDetails.login)
                        Text(text = state.userDetails.bio)
                        Row {
                            Text(text = "${state.userDetails.followers}")
                            Text(
                                text = "followers",
                                modifier = Modifier.padding(horizontal = SmallPadding)
                            )
                            Text(text = "${state.userDetails.following}")
                            Text(
                                text = "following",
                                modifier = Modifier.padding(horizontal = SmallPadding)
                            )
                        }
                        Text(text = state.userDetails.location)
                        Text(text = state.userDetails.blog)
                    }
                }

            }
        }
        userDetailsViewModel.loadUser()
    }
}