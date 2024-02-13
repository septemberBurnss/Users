package nikita.awraimow.githubusers.users.data.network

import nikita.awraimow.githubusers.users.data.model.GithubUserDetailsResponseItem
import nikita.awraimow.githubusers.users.data.model.GithubUsersResponseItem
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("users")
    suspend fun getUsers(): List<GithubUsersResponseItem>

    @GET("user/{userId}")
    suspend fun getUser(@Path("userId") userId: Int): GithubUserDetailsResponseItem
}
