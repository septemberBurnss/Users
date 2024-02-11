package nikita.awraimow.githubusers.users.ui

import nikita.awraimow.githubusers.users.ui.model.UiListUserModel

sealed class UsersScreenState {

    data class Loaded(
        val users: List<UiListUserModel>
    ): UsersScreenState()
    data object Loading: UsersScreenState()
}