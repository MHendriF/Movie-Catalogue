package com.hendri.movie.catalogue.base.adapter

interface ItemListener<Model> {
    fun onItemClick(model: Model)
}