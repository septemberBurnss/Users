package nikita.awraimow.githubusers.users.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import nikita.awraimow.githubusers.users.ui.GithubUserDetailsMapper
import nikita.awraimow.githubusers.users.usecase.GetUserDetailsUseCase
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    private val usersMapper: GithubUserDetailsMapper
): ViewModel() {

    private val _uiState = MutableStateFlow<UserDetailsScreenState>(UserDetailsScreenState.Loading)
    val uiState = _uiState.asStateFlow()

    fun loadUser(userId: Int) {
        viewModelScope.launch {
            _uiState.value = UserDetailsScreenState.Loaded(
                usersMapper.mapToUiModel(
                    getUserDetailsUseCase.getUserDetails(userId)
                )
            )
        }
    }
}