package uz.how.simplemvp.dagger.subcomponent

import dagger.Subcomponent
import dagger.android.AndroidInjector
import uz.how.simplemvp.view.activity.LoginActivity

/**
 * Created by mirjalol on 6/10/17.
 */
@Subcomponent
interface LoginSubComponent : AndroidInjector<LoginActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<LoginActivity>()

}
