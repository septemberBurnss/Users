package nikita.awraimow.githubusers.users.ui.model

data class UserDetailsUiModel(
    val name: String?,
    val login: String,
    val profilePicUrl: String,
    val company: String?,
    val blog: String?,
    val location: String?,
    val bio: String?,
    val followers: Int,
    val following: Int
)