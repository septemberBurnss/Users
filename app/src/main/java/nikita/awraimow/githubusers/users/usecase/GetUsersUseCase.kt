package nikita.awraimow.githubusers.users.usecase

import nikita.awraimow.githubusers.users.data.UsersRepository
import nikita.awraimow.githubusers.users.data.model.GithubUsersResponseItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUsersUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {

    suspend fun getUsers(): List<GithubUsersResponseItem> {
        return usersRepository.getUsers()
    }
}