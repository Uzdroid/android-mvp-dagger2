package uz.how.simplemvp.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import uz.how.simplemvp.model.domains.Repo
import uz.how.simplemvp.model.domains.User

/**
 * Created by mirjalol on 6/10/17.
 */

interface GithubService {

    @GET("users/{user}")
    fun getUser(@Path("user") username: String): Call<User>

    @GET("users/{user}/repos")
    fun getRepos(@Path("user") username: String): Call<List<Repo>>
}
