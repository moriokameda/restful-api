package com.moriokameda.restfulApi.adaptor.infra.table


import org.seasar.doma.Column
import org.seasar.doma.Entity
import org.seasar.doma.GeneratedValue
import org.seasar.doma.GenerationType
import org.seasar.doma.Id
import org.seasar.doma.Table
import org.seasar.doma.Version
import java.math.BigInteger
import java.time.LocalDateTime

@Entity(immutable = true)
@Table(name = "products")
data class ProductRecordTable(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: BigInteger,

    @Column(name = "title")
    val title: String,

    @Column(name = "description")
    val description: String,

    @Column(name = "price")
    val price: Int,

    @Column(name = "image_path")
    val imagePath: String?,

    @Column(name = "created_at", updatable = false)
    val createdAt: LocalDateTime,

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime,

    @Version
    @Column(name = "version")
    val version: Int
) {
    companion object {
        fun of(
            id: BigInteger,
            title: String,
            description: String,
            price: Int,
            imagePath: String?,
            version: Int
        ): ProductRecordTable {
            val now = LocalDateTime.now()
            when (id) {
                BigInteger.ZERO -> {
                    return ProductRecordTable(
                        id = id,
                        title = title,
                        description = description,
                        price = price,
                        imagePath = null,
                        createdAt = now,
                        updatedAt = now,
                        version = 0
                    )
                }
                else -> {
                    return ProductRecordTable(
                        id = id,
                        title = title,
                        description = description,
                        price = price,
                        imagePath = imagePath,
                        createdAt = now,
                        updatedAt = now,
                        version = version
                    )
                }
            }
        }
    }
}
