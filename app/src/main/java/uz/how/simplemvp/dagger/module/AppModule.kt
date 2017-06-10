package uz.how.simplemvp.dagger.module

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.view.LayoutInflater
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.how.simplemvp.MvpApplication
import uz.how.simplemvp.dagger.subcomponent.LoginSubComponent
import uz.how.simplemvp.dagger.subcomponent.ProfileSubComponent
import uz.how.simplemvp.dagger.subcomponent.ReposSubComponent
import uz.how.simplemvp.model.GithubService
import javax.inject.Singleton

/**
 * Created by mirjalol on 6/10/17.
 */
@Module(subcomponents = arrayOf(LoginSubComponent::class, ProfileSubComponent::class, ReposSubComponent::class))
class AppModule {

    @Provides internal fun provideContext(application: MvpApplication): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://api.github.com/")
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    internal fun provideApiService(retrofit: Retrofit): GithubService {
        return retrofit.create(GithubService::class.java)
    }

    @Provides
    @Singleton
    internal fun provideSharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Provides
    @Singleton
    internal fun provideLayoutInflater(context: Context): LayoutInflater {
        return LayoutInflater.from(context)
    }


}
