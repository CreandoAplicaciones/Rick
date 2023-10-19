package com.rick.and.morty.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
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


    }


}



