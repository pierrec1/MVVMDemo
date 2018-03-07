package com.demo.mvvmdemo

import com.demo.mvvmdemo.model.Films
import com.demo.mvvmdemo.model.SearchFilmsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FilmsPresenter @Inject constructor(val searchFilmsUseCase: SearchFilmsUseCase) {

    private lateinit var view: View
    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun setView(view: View) {
        this.view = view
    }

    fun loadUsers() {
        view.showLoading()
        compositeDisposable.add(searchFilmsUseCase.searchByName("car")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ filmsList -> view.showFilmList(filmsList) }
                        , { throwable -> view.showError(throwable.message) })
        )
    }

    fun onDestroy() {
        compositeDisposable.clear()
    }

    interface View {
        fun showLoading()

        fun hideLoading()

        fun showFilmList(films: Films?)

        fun showError(error: String?)
    }
}

