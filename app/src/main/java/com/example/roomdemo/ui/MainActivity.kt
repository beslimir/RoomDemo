package com.example.roomdemo.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdemo.*
import com.example.roomdemo.adapters.RecyclerViewAdapter
import com.example.roomdemo.util.Constants.Companion.LOG_TAG
import com.example.roomdemo.db.FootballClub
import com.example.roomdemo.db.MainDatabase
import com.example.roomdemo.db.Person
import com.example.roomdemo.repository.MainRepository
import com.facebook.stetho.Stetho
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    lateinit var mainAdapter: RecyclerViewAdapter
    var mainList = listOf("Pipo", "Fido")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TODO: Fix list initialisation on app start

        Stetho.initializeWithDefaults(this)

        mainAdapter = RecyclerViewAdapter()
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
            Log.d(LOG_TAG, "aaaa: ${mainAdapter.differ.currentList.size}")
        })

        /* Delete recycler view items (onItemClick -> Button delete press; OnItemLongClickListener) */
        bDelete.setOnClickListener {
            val position = mainAdapter.getItemPosition()!!
            mainViewModel.deletePerson(mainAdapter.differ.currentList[position])
            Toast.makeText(this@MainActivity,
                "Person ${mainAdapter.differ.currentList[position].name} deleted", Toast.LENGTH_SHORT).show()
        }


        mainAdapter.setOnItemLongClickListener { person ->
            mainViewModel.deletePerson(person)
            Toast.makeText(this@MainActivity,
                "Person ${person.name} deleted", Toast.LENGTH_SHORT).show()
        }

        mainAdapter.setOnItemClickListener { person ->
            etName.setText(person.name)
            etLastName.setText(person.lastName)
            etYears.setText(person.years.toString())
        }

    }
}