package com.example.leadershop.admin

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.leadershop.R
import com.example.leadershop.admin.Customers_Horozantal_Adapter.*
import com.squareup.picasso.Picasso

class Customers_Horozantal_Adapter(
    private val context: Context,
    private val productList: List<Product_ad>,
) : RecyclerView.Adapter<Customers_Horozantal_Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_view2, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = productList[position]

        holder.idTextView.text = "ID: ${currentItem.id}"
        holder.nameTextView.text = " ${currentItem.name}"
        holder.descTextView.text = " ${currentItem.description}"
        holder.priceTextView.text = " ${currentItem.price} MAD"

        if (currentItem.free_d) {
            holder.deliveryTextView.text = "Free Delivery"
            holder.deliveryTextView.setTextColor(ContextCompat.getColor(context, R.color.green))
            holder.deliveryTextView.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_free_delivery,
                0,
                0,
                0
            )
        } else {
            holder.deliveryTextView.text = "Delivery Not Free"
            holder.deliveryTextView.setTextColor(ContextCompat.getColor(context, R.color.red))
            holder.deliveryTextView.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_delivery_charges,
                0,
                0,
                0
            )
        }

        if (isConnectedToInternet()) {
            Picasso.get()
                .load(currentItem.img)
                .placeholder(R.drawable.baseline_forward_10_24)
                .error(R.drawable.nointernet)
                .into(holder.imgImageView)
        } else {
            Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idTextView: TextView = itemView.findViewById(R.id.ivProductid1)
        val nameTextView: TextView = itemView.findViewById(R.id.ivProductname1)
        val descTextView: TextView = itemView.findViewById(R.id.ivProductdesc1)
        val priceTextView: TextView = itemView.findViewById(R.id.ivProductprice1)
        val imgImageView: ImageView = itemView.findViewById(R.id.ivProductImg1)
        val deliveryTextView: TextView = itemView.findViewById(R.id.ivProductdelivery1)
    }

    private fun isConnectedToInternet(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return false

        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}
