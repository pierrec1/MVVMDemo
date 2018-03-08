package com.demo.mvvmdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.demo.mvvmdemo.model.Films
import com.demo.mvvmdemo.dagger.MyApplication
import javax.inject.Inject

class MainActivity : AppCompatActivity(), FilmsPresenter.View  {

    @Inject
    lateinit var presenter: FilmsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as MyApplication).appComponent.inject(this)

        presenter.setView(this)
        presenter.searchForFilms()
    }

    override fun showLoading() {
        Toast.makeText(this, "loading", Toast.LENGTH_LONG).show()
    }

    override fun hideLoading() {
        Toast.makeText(this, "done", Toast.LENGTH_LONG).show()
    }

    override fun showFilmList(films: Films?) {
        if (films != null) {
            Toast.makeText(this, "Nb of films: " + films.filmList.size, Toast.LENGTH_LONG).show()
        }
    }

    override fun showError(error: String?) {
        Toast.makeText(this, getString(R.string.error) + error, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
