package uz.how.simplemvp.dagger.subcomponent

import dagger.Subcomponent
import dagger.android.AndroidInjector
import uz.how.simplemvp.view.fragment.ReposFragment

/**
 * Created by mirjalol on 6/10/17.
 */
@Subcomponent
interface ReposSubComponent : AndroidInjector<ReposFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<ReposFragment>()

}
