package nikita.awraimow.githubusers.users.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.compose.ui.tooling.preview.Preview
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import dagger.hilt.android.AndroidEntryPoint
import nikita.awraimow.githubusers.users.ui.model.UiListUserModel
import nikita.awraimow.githubusers.ui.theme.GithubUsersTheme
import nikita.awraimow.githubusers.ui.theme.Values.DefaultPadding
import nikita.awraimow.githubusers.ui.theme.Values.ProfilePicRadius
import nikita.awraimow.githubusers.ui.theme.Values.ProfilePicSize
import nikita.awraimow.githubusers.ui.theme.Values.SmallPadding

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
                        UsersList(users = state.users)
                    }
                }
            }
        }
        usersViewModel.loadUsers()
    }
}

@Composable
fun UsersList(users: List<UiListUserModel>) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(users) { user ->
            UserRow(user)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun UserRow(user: UiListUserModel) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(SmallPadding)
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
        UsersList(users = sampleList)
    }
}