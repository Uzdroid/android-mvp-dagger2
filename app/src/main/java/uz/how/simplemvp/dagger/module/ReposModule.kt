package uz.how.simplemvp.dagger.module

import dagger.Binds
import dagger.Module
import uz.how.simplemvp.view.fragment.ReposFragment

/**
 * Created by mirjalol on 6/10/17.
 */
@Module
abstract class ReposModule {

    @Binds
    internal abstract fun provideReposView(reposFragment: ReposFragment): ReposFragment

}
