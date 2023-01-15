package com.example.project.drugs.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project.R
import com.example.project.drugs.model.Drug
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class DrugAdapter () : RecyclerView.Adapter<DrugAdapter.ViewHolder>() {


    val DiffCallBack: DiffUtil.ItemCallback<Drug> = object: DiffUtil.ItemCallback<Drug>(){
           override fun areItemsTheSame(oldItem: Drug, newItem: Drug): Boolean {
               return oldItem?.id == newItem?.id
    }


    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Drug, newItem: Drug): Boolean {
        return oldItem == newItem
    }

    }

    private val mDiffer: AsyncListDiffer<Drug> = AsyncListDiffer<Drug>(this, DiffCallBack)


    fun submitList(list: List<Drug?>?) {
        mDiffer.submitList(list)
    }

    var data: ArrayList<Drug> = arrayListOf()
        set(value) {
            field=value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
            val v = LayoutInflater.from(viewGroup?.context).inflate(R.layout.drug_row, viewGroup, false)
            return ViewHolder(v);
        }
        override fun getItemCount(): Int {
            return data.size
        }
        override fun onBindViewHolder(viewHolder: ViewHolder, p1: Int) {

            viewHolder.name?.text = data[p1].name
            viewHolder.dose.text = data[p1].dose
            viewHolder.day?.text = data[p1].day
            viewHolder.time?.text = data[p1].time.toString()
            viewHolder.doseType.text = data[p1].doseType
            Glide.with(viewHolder.itemView.getContext())

                .load(data[p1].imageUrl)
                .centerCrop()
                .placeholder(R.drawable.drugs)
                .into(viewHolder.imageView)
//            Glide.with(viewHolder.itemView.getContext()).load(data[p1].imageUrl)
//                .placeholder(R.drawable.drugs).into(viewHolder.imageView);

            viewHolder.del_button.setOnClickListener {
                val uid = FirebaseAuth.getInstance().currentUser?.uid.toString()
                FirebaseFirestore.getInstance().collection("users").document(uid).collection("medicines").document(data[p1].id).delete().addOnSuccessListener {
                    data.removeAt(viewHolder.adapterPosition)

                    notifyItemRemoved(viewHolder.adapterPosition)

                }
            }
        }
        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val name = itemView.findViewById<TextView>(R.id.drug_name)
            val dose = itemView.findViewById<TextView>(R.id.doseQuantity)
            val day = itemView.findViewById<TextView>(R.id.day_time)
            val time = itemView.findViewById<TextView>(R.id.drug_time)
            val doseType = itemView.findViewById<TextView>(R.id.dose_details)
            val del_button = itemView.findViewById<FloatingActionButton>(R.id.delete_button)
            val imageView = itemView.findViewById<ImageView>(R.id.drugico)


        }
    }




//class DiffCallback : DiffUtil.ItemCallback<Drug>() {
//
//    override fun areItemsTheSame(oldItem: Drug, newItem: Drug): Boolean {
//        return oldItem?.id == newItem?.id
//    }
//
//    @SuppressLint("DiffUtilEquals")
//    override fun areContentsTheSame(oldItem: Drug, newItem: Drug): Boolean {
//        return oldItem == newItem
//    }
//
//}