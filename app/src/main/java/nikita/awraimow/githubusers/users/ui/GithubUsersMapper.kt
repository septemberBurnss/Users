package nikita.awraimow.githubusers.users.ui

import nikita.awraimow.githubusers.users.data.model.GithubUsersResponseItem
import nikita.awraimow.githubusers.users.ui.model.UiListUserModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubUsersMapper @Inject constructor() {

    fun mapToUiListModel(responseItem: GithubUsersResponseItem): UiListUserModel {
        return UiListUserModel(responseItem.login, responseItem.avatar_url)
    }
}