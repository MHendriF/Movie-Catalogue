package com.hendri.movie.catalogue.ui.listeners

import com.hendri.movie.catalogue.data.DataEntity

interface ItemListener {
    fun onItemClicked(dataEntity: DataEntity)
}