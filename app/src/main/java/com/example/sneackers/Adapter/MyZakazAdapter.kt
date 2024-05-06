package com.example.sneackers.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.sneackers.Helper.ChangeNumberItemsListener
import com.example.sneackers.Helper.ManagmentCart
import com.example.sneackers.Model.ItemsModel
import com.example.sneackers.databinding.ViewholderCartBinding

class MyZakazAdapter(private val listItemSelected: ArrayList<ItemsModel>,
                     private val context: Context,
                     private var changeNumberItemsListeners: ChangeNumberItemsListener? = null) :
    RecyclerView.Adapter<MyZakazAdapter.ViewHolder>() {

    private val managementCart = ManagmentCart(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItemSelected[position]

        holder.binding.titleTxt.text = item.title
        holder.binding.feeEachItem.text = "Баллов: ${item.price}"
        holder.binding.totalEachItem.text = "Баллов: ${Math.round(item.numberInCart * item.price)}"
        holder.binding.numberItemTxt.text = item.numberInCart.toString()

        Glide.with(holder.itemView.context)
            .load(item.picUrl[0])
            .apply(RequestOptions().transform(CenterCrop()))
            .into(holder.binding.pic)

        holder.binding.plusCartBtn.setOnClickListener {
            managementCart.plusItem(listItemSelected, position, object : ChangeNumberItemsListener {
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListeners?.onChanged()
                }

            })
        }
        holder.binding.minusCartBtn.setOnClickListener {
            managementCart.minusItem(listItemSelected, position, object : ChangeNumberItemsListener {
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListeners?.onChanged()
                }

            })
        }
    }

    override fun getItemCount(): Int = listItemSelected.size

    inner class ViewHolder(val binding: ViewholderCartBinding) : RecyclerView.ViewHolder(binding.root)
}
