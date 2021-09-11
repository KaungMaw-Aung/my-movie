package com.kaungmaw.mymovie

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

private const val IMG_BASE_URL = "https://image.tmdb.org/t/p/w500/"

fun loadImageUrl(imageView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Glide.with(imageView.context).load("$IMG_BASE_URL$it").apply(
            RequestOptions.placeholderOf(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
        ).into(imageView)
    }
}