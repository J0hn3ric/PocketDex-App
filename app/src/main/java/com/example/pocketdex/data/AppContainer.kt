package com.example.pocketdex.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.pocketdex.BuildConfig
import com.example.pocketdex.BuildConfig.BASE_URL
import com.example.pocketdex.data.card.CardRepository
import com.example.pocketdex.data.card.DefaultCardRepository
import com.example.pocketdex.data.user.DefaultUserRepository
import com.example.pocketdex.data.user.LocalUserRepository
import com.example.pocketdex.data.user.RemoteUserRepository
import com.example.pocketdex.data.user.UserRepository
import com.example.pocketdex.data.user_card.DefaultUserCardRepository
import com.example.pocketdex.data.user_card.LocalUserCardRepository
import com.example.pocketdex.data.user_card.RemoteUserCardRepository
import com.example.pocketdex.data.user_card.UserCardRepository
import com.example.pocketdex.network.BackendCardApiService
import com.example.pocketdex.network.BackendUserApiService
import com.example.pocketdex.network.BackendUserCardApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

/* for dependency injection */

private const val USER_PREFERENCE_NAME = "user_preference"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = USER_PREFERENCE_NAME
)

interface AppContainter {

    val userRepository: UserRepository
    val userCardRepository: UserCardRepository
    val cardRepository: CardRepository

}

class AppDataContainer(private val context: Context) : AppContainter {

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .client(okHttpClient)
        .build()

    private val userApiService: BackendUserApiService by lazy {
        retrofit.create(BackendUserApiService::class.java)
    }

    private val userCardApiService: BackendUserCardApiService by lazy {
        retrofit.create(BackendUserCardApiService::class.java)
    }

    private val cardApiService: BackendCardApiService by lazy {
        retrofit.create(BackendCardApiService::class.java)
    }


    override val userRepository: UserRepository by lazy {
        DefaultUserRepository(
            LocalUserRepository(context.dataStore),
            RemoteUserRepository(userApiService),
            PocketDexDatabase.getDataBase(context)
        )
    }

    override val userCardRepository: UserCardRepository by lazy {
        DefaultUserCardRepository(
            LocalUserCardRepository(PocketDexDatabase.getDataBase(context).userCardDao()),
            RemoteUserCardRepository(userCardApiService),
            userRepository
        )
    }

    override val cardRepository: CardRepository by lazy {
        DefaultCardRepository(cardApiService)
    }
}