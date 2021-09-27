package com.example.roomdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdemo.db.Person
import kotlinx.android.synthetic.main.item_list.view.*

class RecyclerViewAdapter(var items: List<Person>): RecyclerView.Adapter<RecyclerViewAdapter.PersonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        return PersonViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
//        val myPerson = differ.currentList[position]
        val myPerson = items[position]
        holder.itemView.apply {
            tvName.text = myPerson.name
            tvLastName.text = myPerson.lastName
            tvYears.text = myPerson.years.toString()
            tvCity.text = myPerson.city.toString()
            tvBrothersSisters.text = myPerson.brothersSisters.toString()
            tvFootballClub.text = "${myPerson.footballClub.clubName}\n${myPerson.footballClub.founded}\n${myPerson.footballClub.country}"
        }
    }

    override fun getItemCount() = items.size


    //TODO
    //use diff callbacks instead of onNotifyDataSetChanged()
    private val differCallback = object: DiffUtil.ItemCallback<Person>() {

        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    inner class PersonViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

}