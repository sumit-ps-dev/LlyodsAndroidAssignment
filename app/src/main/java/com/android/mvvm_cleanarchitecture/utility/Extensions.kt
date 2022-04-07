package com.android.mvvm_cleanarchitecture

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.NonNull
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * Show a toast message
 */
fun Context.showToast(@NonNull message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

/**
 * Function that either loads image from Glide or loads the default logo, if the image url is
 *  not available.
 */
fun ImageView.loadImageOrDefault(imgUrl: String) {
    if (imgUrl.isNotEmpty())
        Glide.with(context)
            .load(imgUrl)
            .apply(RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background))
            .into(this)
    else
        this.setImageResource(R.drawable.ic_launcher_background)
}