package com.example.shopforgroceries.dependencyinjection

import com.example.shopforgroceries.data.remote.Api
import com.example.shopforgroceries.data.repository.UserRepositoryImpl
import com.example.shopforgroceries.domain.repositories.UserRepository
import com.example.shopforgroceries.domain.usecases.auth.AuthenticationUseCases
import com.example.shopforgroceries.domain.usecases.auth.Login
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    val baseUrl = "https://grocerisonline.herokuapp.com/"

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient{
        return  OkHttpClient.Builder().build()
    }

    @Singleton
    @Provides
    fun provideGroceriesApi(client: OkHttpClient): Api{
        return  Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
            .client(client)
            .build()
            .create(Api::class.java)
    }

    @Singleton
    @Provides
    fun provideUserRepository(api: Api): UserRepository {
        return UserRepositoryImpl(api = api)

    }
    @Singleton
    @Provides
    fun provideAuthenticationUseCases(repository: UserRepository): AuthenticationUseCases {
        return AuthenticationUseCases(
            login = Login(
                userRepository = repository
            )
        )
    }
}