package suvorov.openweather.util

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.setImage(uri: String) {
    Glide.with(this)
        .load("https://openweathermap.org/img/wn/${uri}.png")
        .into(this)
}

fun ImageView.setFlat(uri: String) {
    Glide.with(this)
        .load("https://www.countryflagicons.com/FLAT/32/${uri}.png")
        .into(this)
}