package com.example.beerappspindox

import BeerResponseItem
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.detail_activity.*

class BeerDetailActivity : AppCompatActivity() {

    var beerClicked : BeerResponseItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        beerClicked = intent.extras?.get(MainActivity.BEER_CLICKED) as BeerResponseItem


        initView()

    }

    fun navigateBackToMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun initView(){

        toolbar.setNavigationOnClickListener{
            navigateBackToMainActivity()
        }

        detail_title_tv.text = beerClicked?.name
        brewer_tips.text = beerClicked?.brewers_tips
        first_brewed.text = beerClicked?.first_brewed
        food_pairing.text = beerClicked?.food_pairing.toString().replace("[","").replace("]","")







    }


}