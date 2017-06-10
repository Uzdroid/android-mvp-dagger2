package uz.how.simplemvp

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import uz.how.simplemvp.dagger.component.DaggerAppComponent
import javax.inject.Inject

/**
 * Created by mirjalol on 6/9/17.
 */

class MvpApplication : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>


    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
    }
    override fun activityInjector()= activityInjector
    override fun supportFragmentInjector()=fragmentInjector
}
