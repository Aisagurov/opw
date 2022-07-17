package suvorov.openweather.util

import android.widget.ImageView
import suvorov.openweather.R

fun ImageView.convertingDegrees(degree: Int?) {
    setImageResource(
        when (degree) {
            in 0 until 22 -> R.drawable.ic_baseline_south_24
            in 23 until 67 -> R.drawable.ic_baseline_south_west_24
            in 68 until 112 -> R.drawable.ic_baseline_west_24
            in 113 until 157 -> R.drawable.ic_baseline_north_west_24
            in 158 until 202 -> R.drawable.ic_baseline_north_24
            in 203 until 247 -> R.drawable.ic_baseline_north_east_24
            in 248 until 292 -> R.drawable.ic_baseline_east_24
            in 293 until 337 -> R.drawable.ic_baseline_south_east_24
            in 338 until 360 -> R.drawable.ic_baseline_south_24
            else -> R.color.transparent
        }
    )
}