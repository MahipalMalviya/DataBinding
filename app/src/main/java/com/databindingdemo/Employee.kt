package com.databindingdemo

import com.google.gson.annotations.SerializedName
import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.Glide
import androidx.databinding.BindingAdapter


data class Employee (

	@SerializedName("id") val id : Int,
	@SerializedName("email") val email : String,
	@SerializedName("first_name") val first_name : String,
	@SerializedName("last_name") val last_name : String,
	@SerializedName("avatar") val avatar : String
) {
	companion object {

		@BindingAdapter("android:src")
		@JvmStatic
		fun loadImage(imageView: ImageView, imageURL: String) {
			Glide.with(imageView.context)
				.setDefaultRequestOptions(
					RequestOptions()
						.circleCrop()
				)
				.load(imageURL)
				.into(imageView)
		}
	}
}