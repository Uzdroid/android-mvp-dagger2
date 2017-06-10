package uz.how.simplemvp.dagger.module

import dagger.Binds
import dagger.Module
import uz.how.simplemvp.view.activity.ProfileActivity

/**
 * Created by mirjalol on 6/10/17.
 */
@Module
abstract class ProfileModule {

    @Binds
    internal abstract fun provideProfileView(profileActivity: ProfileActivity): ProfileActivity

}
