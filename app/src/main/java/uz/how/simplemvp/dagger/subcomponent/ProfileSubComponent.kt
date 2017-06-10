package uz.how.simplemvp.dagger.subcomponent

import dagger.Subcomponent
import dagger.android.AndroidInjector
import uz.how.simplemvp.view.activity.ProfileActivity

/**
 * Created by mirjalol on 6/10/17.
 */
@Subcomponent
interface ProfileSubComponent : AndroidInjector<ProfileActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<ProfileActivity>()

}
