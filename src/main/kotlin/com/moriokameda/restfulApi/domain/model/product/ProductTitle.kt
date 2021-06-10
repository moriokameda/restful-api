package com.moriokameda.restfulApi.domain.model.entity

data class ProductTitle(
    val value: String
) {
    companion object {
        fun of(value: String): ProductTitle = ProductTitle(value)
    }
}
