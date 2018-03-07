package com.demo.mvvmdemo.dagger

import com.demo.mvvmdemo.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DemoModule::class, FilmsModule::class])

interface AppComponent {
    fun inject(mainActivity: MainActivity)
}
