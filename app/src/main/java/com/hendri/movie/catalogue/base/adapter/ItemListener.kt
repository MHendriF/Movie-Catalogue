package com.hendri.movie.catalogue.base.adapter

interface ItemListener<Entity> {
    fun onItemClick(entity: Entity)
}