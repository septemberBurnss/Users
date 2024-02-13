package nikita.awraimow.githubusers.users.data.model

data class GithubUserDetailsResponseItem(
    val login: String,
    val id: Int,
    val avatar_url: String,
    val html_url: String,
    val name: String?,
    val company: String?,
    val blog: String?,
    val location: String?,
    val bio: String?,
    val followers: Int,
    val following: Int
)