package nikita.awraimow.githubusers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import nikita.awraimow.githubusers.ui.models.UiListUserModel
import nikita.awraimow.githubusers.ui.theme.GithubUsersTheme
import nikita.awraimow.githubusers.ui.theme.Values.DefaultPadding
import nikita.awraimow.githubusers.ui.theme.Values.ProfilePicRadius
import nikita.awraimow.githubusers.ui.theme.Values.ProfilePicSize
import nikita.awraimow.githubusers.ui.theme.Values.SmallPadding

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubUsersTheme {
                Column(modifier = Modifier.fillMaxSize()) {
                    UsersList(
                        users = listOf()
                    )
                }
            }
        }
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