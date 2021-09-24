package com.example.kotlinflowandcoroutinesexample.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.api.load
import coil.decode.SvgDecoder
import coil.request.LoadRequest
import com.example.kotlinflowandcoroutinesexample.MapsActivity
import com.example.kotlinflowandcoroutinesexample.R
import com.example.kotlinflowandcoroutinesexample.model.Country
import kotlinx.android.synthetic.main.list_item_country.view.*
import java.util.*


class CountryAdapter(private val country: ArrayList<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    fun addCountry(country: List<Country>) {
        this.country.apply {
            clear()
            addAll(country)
        }
    }

    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewLatLang: TextView = itemView.findViewById(R.id.textViewLatLang) as TextView
        var image: ImageView = itemView.findViewById(R.id.imageViewFlag) as ImageView

        @SuppressLint("SetTextI18n")
        fun bind(country: Country) {
            itemView.apply {
                textViewCountryName.text = country.name
                textViewCapital.text = "Capital: ${country.capital}"
                textViewCallingCode.text = "CallingCode: ${country.callingCodes.toString()}"
                textViewRegion.text = "Region: ${country.region}"

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_country, parent, false)
        return CountryViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(country[position])

        var latLang = ""
        for (string in country[position].latlng!!) {
            latLang += "$string, "
        }
        latLang = if (latLang.isNotEmpty()) latLang.substring(0, latLang.length - 2) else latLang
        holder.textViewLatLang.text = "LatLang : $latLang"

        val latLngStr = latLang.split(",").toTypedArray()

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, MapsActivity::class.java)
            intent.putExtra("Lat", latLngStr[0])
            intent.putExtra("Long", latLngStr[1])
            intent.putExtra("country", country[position].name)
            holder.itemView.context.startActivity(intent)

        }

        val imageUri = country[position].flag
        fun ImageView.loadSvgOrOthers(myUrl: String?) {
            myUrl?.let {
                if (it.lowercase(Locale.ENGLISH).endsWith("svg")) {
                    val imageLoader = ImageLoader.Builder(this.context)
                        .componentRegistry {
                            add(SvgDecoder(this@loadSvgOrOthers.context))
                        }
                        .build()
                    val request = LoadRequest.Builder(this.context)
                        .data(it)
                        .target(holder.image)
                        .build()
                    imageLoader.execute(request)
                } else {
                    this.load(myUrl)
                }
            }
        }
        holder.image.loadSvgOrOthers(imageUri)
        Log.d("flag:", imageUri)

    }

    override fun getItemCount(): Int {
        return country.size
    }
}