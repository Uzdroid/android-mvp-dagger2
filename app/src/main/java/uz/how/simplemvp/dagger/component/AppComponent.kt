package uz.how.simplemvp.dagger.component

import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import uz.how.simplemvp.MvpApplication
import uz.how.simplemvp.dagger.module.AppModule
import uz.how.simplemvp.dagger.module.BuildersModule
import javax.inject.Singleton

/**
 * Created by mirjalol on 6/10/17.
 */
@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class, AppModule::class, BuildersModule::class))
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MvpApplication): Builder
        fun build(): AppComponent
    }

    fun inject(application: MvpApplication)

}
