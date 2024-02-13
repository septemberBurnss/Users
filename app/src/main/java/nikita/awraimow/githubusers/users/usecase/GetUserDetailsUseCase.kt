package nikita.awraimow.githubusers.users.usecase

import nikita.awraimow.githubusers.users.data.UsersRepository
import nikita.awraimow.githubusers.users.data.model.GithubUserDetailsResponseItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUserDetailsUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {

    suspend fun getUserDetails(): GithubUserDetailsResponseItem {
        return usersRepository.getUser()
    }
}