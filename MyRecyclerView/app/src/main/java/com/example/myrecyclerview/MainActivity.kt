package com.example.myrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myrecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Hero>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvHeroes.setHasFixedSize(true)

        list.addAll(getListHeroes())
        showRecyclerList()
    }

    fun getListHeroes(): ArrayList<Hero> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)

        val listHero = ArrayList<Hero>()
        for (position in dataName.indices) {
            val hero = Hero(
                    dataName[position],
                    dataDescription[position],
                    dataPhoto[position]
            )
            listHero.add(hero)
        }
        return listHero
    }

    // utk menampilkan dalam bentuk list view
    private fun showRecyclerList() {
        binding.rvHeroes.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListHeroAdapter(list)
        binding.rvHeroes.adapter = listHeroAdapter
    }

    // utk menampilkan dalam bentuk grid view
    private fun showRecyclerGrid() {
        binding.rvHeroes.layoutManager = GridLayoutManager(this, 2)
        val gridHeroAdapter = GridHeroAdapter(list)
        binding.rvHeroes.adapter = gridHeroAdapter
    }

    // utk menampilkan dalam bentuk card view
    private fun showRecyclerCardView() {
        binding.rvHeroes.layoutManager = LinearLayoutManager(this)
        val cardViewHeroAdapter = CardViewHeroAdapter(list)
        binding.rvHeroes.adapter = cardViewHeroAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_list -> {
                showRecyclerList()
            }
            R.id.action_grid -> {
                showRecyclerGrid()
            }
            R.id.action_cardview -> {
                showRecyclerCardView()
            }
        }
    }
}