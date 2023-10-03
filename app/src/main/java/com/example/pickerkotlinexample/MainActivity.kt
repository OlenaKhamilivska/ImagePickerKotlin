package com.example.pickerkotlinexample

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.pickerkotlinexample.databinding.ActivityMainBinding
import com.example.pickerkotlinexample.databinding.ItemImageBinding
import gun0912.tedimagepicker.builder.TedImagePicker
import gun0912.tedimagepicker.builder.type.ButtonGravity
import gun0912.tedimagepicker.builder.type.MediaType

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var selectedUriList: List<Uri>? = null
    private lateinit var newAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setNormalMultiButton()
    }

    private fun setNormalMultiButton() {
        binding.btnNormalMulti.setOnClickListener {
            TedImagePicker.with(this)
                .mediaType(MediaType.IMAGE)
                .scrollIndicatorDateFormat("YYYYMMDD")
                .buttonGravity(ButtonGravity.BOTTOM)
                .buttonBackground(R.drawable.btn_sample_done_button)
                .buttonTextColor(R.color.sample_yellow)
                .errorListener { message -> Log.d("ted", "message: $message") }
                .cancelListener { Log.d("ted", "image select cancel") }
                .selectedUri(selectedUriList)
                .startMultiImage { list: List<Uri> -> showMultiImage(list) }
        }
    }

    private fun showMultiImage(uriList: List<Uri>) {
        newAdapter = CustomAdapter(uriList)
        this.selectedUriList = uriList
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = newAdapter
        }
    }
}