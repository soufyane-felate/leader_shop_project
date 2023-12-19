package com.example.leadershop.admin

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.leadershop.R

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class CustomAdapter(private val context: Context, private val productList: List<Product_ad>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.layout_store_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = productList[position]

        holder.idTextView.text = "ID: ${currentItem.id}"
        holder.nameTextView.text = " ${currentItem.name}"
        holder.descTextView.text = " ${currentItem.description}"
        holder.priceTextView.text = " ${currentItem.price} MAD"

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
        val idTextView: TextView = itemView.findViewById(R.id.ivProductid)
        val nameTextView: TextView = itemView.findViewById(R.id.ivProductname)
        val descTextView: TextView = itemView.findViewById(R.id.ivProductdesc)
        val priceTextView: TextView = itemView.findViewById(R.id.ivProductprice)
        val imgImageView: ImageView = itemView.findViewById(R.id.ivProductImg)
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
