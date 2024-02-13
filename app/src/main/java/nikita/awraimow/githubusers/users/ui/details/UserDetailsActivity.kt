package nikita.awraimow.githubusers.users.ui.details

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import nikita.awraimow.githubusers.ui.theme.Values.DefaultPadding
import nikita.awraimow.githubusers.ui.theme.Values.MediumPadding
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
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                            .padding(MediumPadding)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            GlideImage(
                                model = state.userDetails.profilePicUrl,
                                contentDescription = null,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(Values.ProfilePicRadius))
                                    .fillMaxWidth(0.4f)
                            )
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(0.6f)
                                    .padding(DefaultPadding)
                            ) {
                                state.userDetails.name?.let {
                                    Text(
                                        text =it,
                                        style = TextStyle(fontWeight = FontWeight.Bold)
                                    )
                                }
                                Text(text = state.userDetails.login)
                            }
                        }
                        state.userDetails.bio?.let {
                            Text(
                                text = "Bio",
                                style = TextStyle(fontWeight = FontWeight.Bold),
                                modifier = Modifier.padding(top = DefaultPadding)
                            )
                            Text(text = it)
                        }

                        state.userDetails.location?.let {
                            Text(
                                text = "Location",
                                style = TextStyle(fontWeight = FontWeight.Bold),
                                modifier = Modifier.padding(top = DefaultPadding)
                            )
                            Text(text = it)
                        }

                        state.userDetails.blog?.let {
                            Text(
                                text = "Blog",
                                style = TextStyle(fontWeight = FontWeight.Bold),
                                modifier = Modifier.padding(top = DefaultPadding)
                            )
                            Text(text = it)
                        }

                        Row(modifier = Modifier.padding(top = DefaultPadding)) {
                            Text(
                                text = "Followers:",
                                style = TextStyle(fontWeight = FontWeight.Bold),
                                modifier = Modifier.padding(end = SmallPadding)
                            )
                            Text(text = "${state.userDetails.followers}")
                        }
                        Row {
                            Text(
                                text = "Following:",
                                style = TextStyle(fontWeight = FontWeight.Bold),
                                modifier = Modifier.padding(end = SmallPadding)
                            )
                            Text(text = "${state.userDetails.following}")
                        }
                    }
                }

            }
        }
        userDetailsViewModel.loadUser(intent.getIntExtra(USER_ID_KEY, -1))
    }

    companion object {
        const val USER_ID_KEY = "userId"
    }
}