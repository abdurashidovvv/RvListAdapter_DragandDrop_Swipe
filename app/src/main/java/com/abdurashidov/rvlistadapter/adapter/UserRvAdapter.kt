package com.abdurashidov.rvlistadapter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdurashidov.rvlistadapter.databinding.RvItemBinding
import com.abdurashidov.rvlistadapter.models.User
import com.abdurashidov.rvlistadapter.utils.ItemTouchHelperAdapter
import java.util.*
import kotlin.collections.ArrayList


class UserRvAdapter(val list:ArrayList<User>, val context: Context):RecyclerView.Adapter<UserRvAdapter.Vh>(),ItemTouchHelperAdapter{

    inner class Vh(val rvItem: RvItemBinding): RecyclerView.ViewHolder(rvItem.root){
        fun onBind(user:User){
            rvItem.tvName.text=user.name
            rvItem.tvNumber.text=user.number

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition>toPosition){
            for (i in fromPosition until toPosition){
                Collections.swap(list, i, i+1)
            }
        }else{
            for (i in fromPosition until toPosition){
                Collections.swap(list, i, i-1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

}