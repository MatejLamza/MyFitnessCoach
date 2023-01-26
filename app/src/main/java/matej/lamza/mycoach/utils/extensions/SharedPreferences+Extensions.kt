package matej.lamza.mycoach.utils.extensions

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

val serializer: Gson by lazy { GsonBuilder().create() }

suspend fun SharedPreferences.setString(key: String, value: String, commit: Boolean = true) {
    withContext(IO) { edit(commit = commit) { putString(key, value) } }
}

suspend fun SharedPreferences.getString(key: String): String? =
    withContext(IO) { getString(key, null) }

fun SharedPreferences.observeString(key: String): Flow<String?> =
    channelFlow {
        trySend(getString(key))
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, changedKey ->
            if (key == changedKey) trySend(getString(key, null))
        }
        registerOnSharedPreferenceChangeListener(listener)
        awaitClose { unregisterOnSharedPreferenceChangeListener(listener) }
    }.flowOn(IO)


suspend fun SharedPreferences.setObject(key: String, value: Any, commit: Boolean = true) {
    withContext(IO) {
        Log.d("bbb", "setObject: $key | $value")
        val json = kotlin.runCatching { serializer.toJson(value) }.getOrNull()
        edit(commit = commit) { putString(key, json) }
    }
}

inline fun <reified T> SharedPreferences.getObject(key: String, defaultValue: T?): T? {
    val json = getString(key, null)
    return kotlin.runCatching { serializer.fromJson(json, T::class.java) }.getOrNull() ?: defaultValue
}

suspend inline fun <reified T> SharedPreferences.getObject(key: String): T? =
    withContext(IO) { getObject<T>(key, null) }

inline fun <reified T> SharedPreferences.observeObject(key: String): Flow<T?> =
    channelFlow<T?> {
        trySend(getObject<T>(key))
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, changedKey ->
            if (key == changedKey) {
                trySend(getObject<T>(key, null))
            }
        }
        registerOnSharedPreferenceChangeListener(listener)
        awaitClose { unregisterOnSharedPreferenceChangeListener(listener) }
    }

