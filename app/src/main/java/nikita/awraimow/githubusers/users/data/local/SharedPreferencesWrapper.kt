package nikita.awraimow.githubusers.users.data.local

import android.content.Context
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesWrapper @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: Gson
) {

    private val sharedPreferences = context.getSharedPreferences(
        VIEWED_USERS_PREF_NAME,
        Context.MODE_PRIVATE
    )

    fun saveView(userId: Int) {
        val editor = sharedPreferences.edit()
        val savedData = getAllViewed()
        if (!savedData.contains(userId)) {
            editor.putString(
                VIEWED_USERS_PREF_KEY, gson.toJson(savedData.plus(userId))
            )
        }
        editor.apply()
    }

    fun getAllViewed(): List<Int> {
        val savedJson = sharedPreferences.getString(VIEWED_USERS_PREF_KEY, null)
        if (savedJson.isNullOrEmpty()) return emptyList()
        return gson.fromJson(savedJson, Array<Int>::class.java).toList()
    }

    private companion object {
        private const val VIEWED_USERS_PREF_NAME = "viewed_users_preferences"
        private const val VIEWED_USERS_PREF_KEY = "viewed_users"
    }
}