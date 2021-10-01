package com.example.roomdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdemo.db.FootballClub
import com.example.roomdemo.db.MainDatabase
import com.example.roomdemo.db.Person
import com.facebook.stetho.Stetho
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    lateinit var mainAdapter: RecyclerViewAdapter
    var mainList = listOf("Pipo", "Fido")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Stetho.initializeWithDefaults(this)

        mainAdapter = RecyclerViewAdapter(listOf())
        rvPersonList.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        val mainRepository = MainRepository(MainDatabase(this))
        val mainViewModelFactory = MainViewModelFactory(mainRepository)
        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
        rvPersonList.setHasFixedSize(true)

        bUpsert.setOnClickListener {
            if (etName.text.isEmpty()) {
                etName.error = "Name required"
                return@setOnClickListener
            }

            if (etLastName.text.isEmpty()) {
                etLastName.error = "Last name required"
                return@setOnClickListener
            }

            if (etYears.text.isEmpty()) {
                etYears.error = "Years required"
                return@setOnClickListener
            }

            mainViewModel.upsertPerson(
                Person(
                    etName.text.toString(),
                    etLastName.text.toString(),
                    etYears.text.toString().toInt(),
                    mainList,
                    FootballClub("FC Bayern Munich", 1900, "Germany"),
                    "Glamoc"
                )
            )

            showToast(this@MainActivity, "Successfully inserted")
        }

        mainViewModel.listAllPeople().observe(this, Observer {
            mainAdapter.differ.submitList(it)
//            mainAdapter.items = it
//            mainAdapter.notifyDataSetChanged()
        })

        //TODO: Write code to delete a user
        bDelete.setOnClickListener {

        }

    }
}