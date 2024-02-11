package nikita.awraimow.githubusers.users.data.network

import nikita.awraimow.githubusers.users.data.model.GithubUsersResponseItem
import retrofit2.http.GET

interface GithubService {

    @GET("users")
    suspend fun getUsers(): List<GithubUsersResponseItem>
}
