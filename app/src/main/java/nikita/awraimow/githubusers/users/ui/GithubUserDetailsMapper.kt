package nikita.awraimow.githubusers.users.ui

import nikita.awraimow.githubusers.users.data.model.GithubUserDetailsResponseItem
import nikita.awraimow.githubusers.users.ui.model.UserDetailsUiModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubUserDetailsMapper @Inject constructor() {

    fun mapToUiModel(responseItem: GithubUserDetailsResponseItem): UserDetailsUiModel {
        return UserDetailsUiModel(
            name = responseItem.name,
            login = responseItem.login,
            profileUrl = responseItem.html_url,
            profilePicUrl = responseItem.avatar_url,
            company = responseItem.company,
            blog = responseItem.blog,
            location = responseItem.location,
            bio = responseItem.bio,
            followers = responseItem.followers,
            following = responseItem.following,
        )
    }
}