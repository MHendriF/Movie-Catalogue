package com.hendri.movie.catalogue.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.data.DataEntity
import com.hendri.movie.catalogue.databinding.ActivityDetailBinding
import com.hendri.movie.catalogue.ui.viewmodels.DetailViewModel
import com.hendri.movie.catalogue.utils.Constants.TYPE_MOVIE
import timber.log.Timber

class DetailActivity : AppCompatActivity() {

    private lateinit var detailBinding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel
    private lateinit var data: DataEntity

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_TYPE = "extra_type"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        doInitialization()
    }

    private fun doInitialization() {
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        detailBinding.ivBack.setOnClickListener { onBackPressed() }
        getDataDetails()
    }

    private fun getDataDetails() {
        detailBinding.isLoading = true
        val dataEntity: DataEntity? = intent.getParcelableExtra(EXTRA_DATA)
        val type: String? = intent.getStringExtra(EXTRA_TYPE)
        if (dataEntity != null) {
            data = viewModel.getDataById(dataEntity.id, type)
        }
        Timber.d("Trace :: data(${dataEntity})")

        detailBinding.model = data
        detailBinding.tvReadMore.setOnClickListener {
            if (detailBinding.tvReadMore.text.toString() == "Read More") {
                detailBinding.tvDescription.maxLines = Int.MAX_VALUE
                detailBinding.tvDescription.ellipsize = null
                detailBinding.tvReadMore.setText(R.string.read_less)
            } else {
                detailBinding.tvDescription.maxLines = 4
                detailBinding.tvDescription.ellipsize = TextUtils.TruncateAt.END
                detailBinding.tvReadMore.setText(R.string.read_more)
            }
        }
        detailBinding.isLoading = false
    }
}