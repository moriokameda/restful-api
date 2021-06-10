package com.moriokameda.restfulApi.domain.model.entity

import com.moriokameda.restfulApi.adaptor.infra.table.ProductRecordTable
import java.math.BigInteger

data class ProductEntity(
    val id: BigInteger,
    val title: String,
    val description: String,
    val price: Int,
    val imagePath: String?,
    val version: Int
) {
    companion object {
        fun from(record: ProductRecordTable): ProductEntity = ProductEntity(
            id = record.id,
            title = record.title,
            description = record.description,
            price = record.price,
            imagePath = record.imagePath,
            version = record.version
        )
    }
}
