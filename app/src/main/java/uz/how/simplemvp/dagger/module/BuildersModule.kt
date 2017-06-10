package uz.how.simplemvp.dagger.module

import android.app.Activity
import android.support.v4.app.Fragment

import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap
import uz.how.simplemvp.dagger.subcomponent.LoginSubComponent
import uz.how.simplemvp.dagger.subcomponent.ProfileSubComponent
import uz.how.simplemvp.dagger.subcomponent.ReposSubComponent
import uz.how.simplemvp.view.activity.LoginActivity
import uz.how.simplemvp.view.activity.ProfileActivity
import uz.how.simplemvp.view.fragment.ReposFragment

/**
 * Created by mirjalol on 6/10/17.
 */
@Module
abstract class BuildersModule {

    @Binds
    @IntoMap
    @ActivityKey(LoginActivity::class)
    internal abstract fun bindLoginActivityInjectorFactory(builder: LoginSubComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(ProfileActivity::class)
    internal abstract fun bindProfileActivityInjectorFactory(builder: ProfileSubComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @FragmentKey(ReposFragment::class)
    internal abstract fun bindReposFragmentInjectorFactory(builder: ReposSubComponent.Builder): AndroidInjector.Factory<out Fragment>

}
