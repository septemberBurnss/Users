package nikita.awraimow.githubusers.users.ui

import nikita.awraimow.githubusers.users.data.model.GithubUserDetailsResponseItem
import nikita.awraimow.githubusers.users.ui.model.UserDetailsUiModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubUserDetailsMapper @Inject constructor() {

    fun mapToUiModel(responseItem: GithubUserDetailsResponseItem): UserDetailsUiModel {
        return UserDetailsUiModel(
            responseItem.name,
            responseItem.login,
            responseItem.avatar_url,
            responseItem.company,
            responseItem.blog,
            responseItem.location,
            responseItem.bio,
            responseItem.followers,
            responseItem.following,
        )
    }
}