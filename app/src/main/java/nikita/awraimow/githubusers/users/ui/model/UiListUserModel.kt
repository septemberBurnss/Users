package nikita.awraimow.githubusers.users.ui.model

data class UiListUserModel(
    val name: String,
    val profilePicUrl: String,
    val userId: Int,
    val isViewed: Boolean = false
)