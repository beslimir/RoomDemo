package com.example.roomdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdemo.db.Person
import kotlinx.android.synthetic.main.item_list.view.*

class RecyclerViewAdapter :
    RecyclerView.Adapter<RecyclerViewAdapter.PersonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        return PersonViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val myPerson = differ.currentList[position]
//        val myPerson = items[position]
        holder.itemView.apply {
            tvName.text = myPerson.name
            tvLastName.text = myPerson.lastName
            tvYears.text = myPerson.years.toString()
            tvCity.text = myPerson.city.toString()
            tvBrothersSisters.text = myPerson.brothersSisters.toString()
            tvFootballClub.text = "${myPerson.footballClub.clubName}\n" +
                    "${myPerson.footballClub.founded}\n${myPerson.footballClub.country}"

            setOnClickListener {
                getItemPosition = position
                onItemClickListener?.let {
                    it(myPerson)
                }
            }

            setOnLongClickListener {
                onItemLongClickListener?.let {
                    it(myPerson)
                }
                true
            }

        }
    }

    override fun getItemCount() = differ.currentList.size /* items.size */


    //use diff callbacks instead of onNotifyDataSetChanged()
    private val differCallback = object : DiffUtil.ItemCallback<Person>() {

        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    //get position of recyclerView Item
    private var getItemPosition: Int? = null

    fun getItemPosition(): Int? {
        return getItemPosition
    }

    inner class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    //create onItemClickListener
    private var onItemClickListener: ((Person) -> Unit)? = null

    fun setOnItemClickListener(listener: (Person) -> Unit) {
        onItemClickListener = listener
    }

    //create onItemLongClickListener
    private var onItemLongClickListener: ((Person) -> Unit)? = null

    fun setOnItemLongClickListener(listener: (Person) -> Unit) {
        onItemLongClickListener = listener
    }

}