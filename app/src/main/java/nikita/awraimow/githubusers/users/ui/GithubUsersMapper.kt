package nikita.awraimow.githubusers.users.ui

import nikita.awraimow.githubusers.users.data.local.SharedPreferencesWrapper
import nikita.awraimow.githubusers.users.data.model.GithubUsersResponseItem
import nikita.awraimow.githubusers.users.ui.model.UiListUserModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubUsersMapper @Inject constructor(
    private val sharedPreferencesWrapper: SharedPreferencesWrapper
) {

    fun mapToUiListModels(responseItems: List<GithubUsersResponseItem>): List<UiListUserModel> {
        val viewHistory = sharedPreferencesWrapper.getAllViewed()
        return responseItems.map { responseItem ->
            UiListUserModel(
                responseItem.login,
                responseItem.avatar_url,
                responseItem.id,
                viewHistory.contains(responseItem.id)
            )
        }
    }
}