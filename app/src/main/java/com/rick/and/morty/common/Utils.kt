package com.rick.and.morty.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.rick.and.morty.R
import com.rick.and.morty.domain.model.AnimationCharacter
import java.text.Normalizer

class Utils {

    companion object {

        fun toast(context: Context, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        fun hideKeyboard(view: View) {
            val inputManager = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(view.windowToken, 0)
        }

        fun filterLis(list: List<AnimationCharacter>, queryString: String) : List<AnimationCharacter> {
            return list.filter {
                val titleNormalized = it.name
                    .replace("\\p{M}".toRegex(), "")
                val queryNormalized = Normalizer.normalize(queryString, Normalizer.Form.NFD)
                    .replace("\\p{M}".toRegex(), "")
                titleNormalized.contains(queryNormalized, ignoreCase = true)
            }
        }

        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val activeNetwork =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }

        fun getGenderIcon(gender: String): Int {
            return when (gender) {
                "Female" -> R.drawable.icon_female
                "Male" -> R.drawable.icon_male
                else -> R.drawable.icon_unknown
            }
        }

        fun getStatusIcon(status: String): Int {
            return when (status) {
                "Alive" -> R.drawable.icon_alive
                "Dead" -> R.drawable.icon_dead
                else -> R.drawable.icon_unknown
            }
        }

        fun getSpeciesIcon(species: String): Int {
            return when (species) {
                "Human" -> R.drawable.icon_human
                "Alien" -> R.drawable.icon_alien
                else -> R.drawable.icon_unknown
            }
        }fun getOriginIcon(origin: String): Int {
            return when (origin) {
                "Earth (C-137)" -> R.drawable.icon_eart_137
                "Earth (Replacement Dimension)" -> R.drawable.icon_eart
                "Abadango" -> R.drawable.icon_abandango
                else -> R.drawable.icon_unknown
            }
        }

    }


}



