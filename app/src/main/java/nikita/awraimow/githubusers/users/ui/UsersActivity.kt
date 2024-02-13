package nikita.awraimow.githubusers.users.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import nikita.awraimow.githubusers.users.ui.model.UiListUserModel
import nikita.awraimow.githubusers.ui.theme.GithubUsersTheme
import nikita.awraimow.githubusers.ui.theme.Values.DefaultPadding
import nikita.awraimow.githubusers.ui.theme.Values.ProfilePicRadius
import nikita.awraimow.githubusers.ui.theme.Values.ProfilePicSize
import nikita.awraimow.githubusers.ui.theme.Values.SmallPadding
import nikita.awraimow.githubusers.users.ui.details.UserDetailsActivity

@AndroidEntryPoint
class UsersActivity : ComponentActivity() {

    private val usersViewModel: UsersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubUsersTheme {
                val state = usersViewModel.uiState.collectAsState().value

                Column(modifier = Modifier.fillMaxSize()) {
                    if (state is UsersScreenState.Loaded) {
                        UsersList(
                            users = state.users,
                            this@UsersActivity::goToUserDetailsScreen
                        )
                    }
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                usersViewModel.loadUsers()
            }
        }
    }

    private fun goToUserDetailsScreen(userId: Int) {
        startActivity(
            Intent(
                this,
                UserDetailsActivity::class.java
            ).putExtra(UserDetailsActivity.USER_ID_KEY, userId)
        )
    }
}

@Composable
fun UsersList(users: List<UiListUserModel>, onClick: (Int) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(users) { user ->
            UserRow(
                user = user,
                modifier = Modifier
                    .padding(SmallPadding)
                    .clickable { onClick(user.userId) }
                    .fillMaxWidth()
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun UserRow(user: UiListUserModel, modifier: Modifier) {
    val background = if (user.isViewed) {
        Color.LightGray
    } else {
        Color.White
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(background)
            .padding(SmallPadding)
    ) {
        GlideImage(
            model = user.profilePicUrl,
            contentDescription = null,
            modifier = Modifier
                .size(ProfilePicSize)
                .clip(RoundedCornerShape(ProfilePicRadius))
        )
        Text(
            text = user.name,
            modifier = Modifier.padding(start = DefaultPadding)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserListPreview() {
    GithubUsersTheme {
        val sampleList: List<UiListUserModel> = emptyList()
        UsersList(users = sampleList) {}
    }
}