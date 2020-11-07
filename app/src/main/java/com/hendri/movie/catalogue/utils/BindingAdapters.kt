package com.hendri.movie.catalogue.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class BindingAdapters {
    @BindingAdapter("android:imageURL")
    fun setImageURL(imageView: ImageView, URL: String?) {
        try {
            imageView.alpha = 0f
            Picasso.get().load(URL).noFade().into(imageView, object : Callback {
                override fun onSuccess() {
                    imageView.animate().setDuration(300).alpha(1f).start()
                }
                override fun onError(e: Exception) {}
            })
        } catch (ignored: Exception) { }
    }

    @BindingAdapter("android:imageResource")
    fun setImageResource(imageView: ImageView, image: Int) {
        try {
            imageView.alpha = 0f
            Picasso.get().load(image).noFade().into(imageView, object : Callback {
                override fun onSuccess() {
                    imageView.animate().setDuration(300).alpha(1f).start()
                }
                override fun onError(e: Exception) {}
            })
        } catch (ignored: Exception) { }
    }
}