package nikita.awraimow.githubusers.users.ui.details

import nikita.awraimow.githubusers.users.ui.model.UserDetailsUiModel

sealed class UserDetailsScreenState {
    data class Loaded(
        val userDetails: UserDetailsUiModel
    ): UserDetailsScreenState()
    data object Loading: UserDetailsScreenState()
}