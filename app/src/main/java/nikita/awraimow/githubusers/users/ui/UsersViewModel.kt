package nikita.awraimow.githubusers.users.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import nikita.awraimow.githubusers.users.usecase.GetUsersUseCase
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val usersMapper: GithubUsersMapper
): ViewModel() {

    private val _uiState = MutableStateFlow<UsersScreenState>(UsersScreenState.Loading)
    val uiState = _uiState.asStateFlow()

    fun loadUsers() {
        viewModelScope.launch {
            _uiState.value = UsersScreenState.Loaded(
                usersMapper.mapToUiListModels(getUsersUseCase.getUsers())
            )
        }
    }
}