package uz.how.simplemvp.dagger.module

import dagger.Binds
import dagger.Module
import uz.how.simplemvp.view.activity.LoginActivity

/**
 * Created by mirjalol on 6/10/17.
 */
@Module
abstract class LoginModule {

    @Binds
    internal abstract fun provideLoginView(loginActivity: LoginActivity): LoginActivity

}
