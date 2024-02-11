package nikita.awraimow.githubusers.users.data

import nikita.awraimow.githubusers.users.data.model.GithubUsersResponseItem
import nikita.awraimow.githubusers.users.data.network.GithubService
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val githubService: GithubService
) {

    suspend fun getUsers(): List<GithubUsersResponseItem> {
        return githubService.getUsers()
    }
}