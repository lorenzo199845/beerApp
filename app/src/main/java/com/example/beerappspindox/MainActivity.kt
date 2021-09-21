package com.example.beerappspindox

import BeerResponse
import BeerResponseItem
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        const val  BEER_CLICKED ="BEER_CLICKED"
    }

    private var beerAdapter : BeersAdapter? = null

    private lateinit var viewModel: BeerViewModel

    private val filterTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            //do nothing here
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            //progress_bar.visibility = View.VISIBLE
            if (s.isNullOrEmpty()){
                viewModel.getBeers()
            } else {

                viewModel.filterBeers(s.toString())
            }

        }

        override fun afterTextChanged(s: Editable?) {
            //do nothing here
        }

    }

    private val beerListener = object : BeersAdapter.IBeerListener {
        override fun onBeerClicked(beerModelView: BeerResponseItem) {
            val intent = Intent(this@MainActivity,BeerDetailActivity::class.java)
            intent.putExtra(BEER_CLICKED, beerModelView)
            startActivity(intent)
        }
    }

    private val beerListObserver = Observer<BeerResponse> {
        progress_bar.visibility = View.GONE
        if (it != null){
            beerAdapter?.updateBeerList(it)
        } else {
            Toast.makeText(this@MainActivity,"Si è verificato un errore", Toast.LENGTH_LONG).show()

        }
    }

    private val beerFilteredListObserver = Observer<BeerResponse> {
        progress_bar.visibility = View.GONE
        if (it != null){
            beerAdapter?.updateBeerList(it)
        } else {
            Toast.makeText(this@MainActivity,"Si è verificato un errore", Toast.LENGTH_LONG).show()

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(BeerViewModel::class.java)
        viewModel.getBeers()
        progress_bar.visibility = View.VISIBLE
        viewModel.observableBeerList.observe(this,beerListObserver)
        viewModel.observableFilteredBeerList.observe(this,beerFilteredListObserver)
        initRv()
        initFilter()


    }

    private fun initRv() {
        recyclerview.apply {
            beerAdapter =
                BeersAdapter(beerListener)
            this.adapter = beerAdapter
            this.layoutManager = LinearLayoutManager(context)
        }
    }

    private fun initFilter(){
        searchBar.addTextChangedListener(filterTextWatcher)
    }
}