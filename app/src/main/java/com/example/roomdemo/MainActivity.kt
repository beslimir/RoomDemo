package com.example.roomdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.roomdemo.db.FootballClub
import com.example.roomdemo.db.MainDatabase
import com.example.roomdemo.db.Person
import com.facebook.stetho.Stetho
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    var mainList = listOf("Pipo", "Fido")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Stetho.initializeWithDefaults(this)

        val mainRepository = MainRepository(MainDatabase(this))
        val mainViewModelFactory = MainViewModelFactory(mainRepository)
        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)

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
                    "80230, Glamoc"
                )
            )

            showToast(this@MainActivity, "Successfully inserted")
        }

        //TODO: Write code to show users in recyclerView

        //TODO: Write code to delete a user
        bDelete.setOnClickListener {

        }

    }
}