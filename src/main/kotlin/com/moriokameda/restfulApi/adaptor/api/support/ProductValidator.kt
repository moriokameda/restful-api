package com.moriokameda.restfulApi.adaptor.api.support

import com.moriokameda.restfulApi.adaptor.view.form.ProductForm
import com.moriokameda.restfulApi.application.service.ProductService
import com.moriokameda.restfulApi.config.ValidationMessage
import com.moriokameda.restfulApi.domain.model.product.ProductTitle
import org.apache.commons.io.FilenameUtils
import org.springframework.context.MessageSource
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.validation.BindException
import org.springframework.validation.BindingResult
import org.springframework.web.multipart.MaxUploadSizeExceededException
import org.springframework.web.multipart.MultipartFile
import java.math.BigInteger
import java.util.*

/**
 * 商品バリデーションクラス
 */
@Component
class ProductValidation(
    private val productService: ProductService,
    private val messageSource: MessageSource,
    private val environment: Environment
) {
    /**
     * 商品のバリデーション
     */
    fun validate(form: ProductForm, result: BindingResult, id: BigInteger?) {
        if (result.getFieldError("title") == null && productService.isTitleCreated(ProductTitle.of(form.title!!)))
            result.rejectValue(
                "title",
                HttpStatus.BAD_GATEWAY.value().toString(),
                environment.getProperty(
                    "{product.form.title.duplicated", String::class.java,
                    ValidationMessage.TITLE_DUPLICATED
                )
            )
        if (result.hasErrors()) throw BindException(result)
    }

    /**
     * 商品画像のバリデーション
     */
    fun validate(file: MultipartFile) {
        val maxSize = environment.getProperty("environment.images.max-size-bytes", Long::class.java, 0)
        if (file.size > maxSize) throw MaxUploadSizeExceededException(
            file.size, Exception(
                messageSource.getMessage("file.max.size", null, Locale.JAPAN)
            )
        )
        file.originalFilename?.let {
            if ()
        }
    }

    /**
     * 拡張子をメディアタイプに変更する
     */
    private fun extensionToMediaType(extension: String): MediaType {
        return MediaType.parseMediaType(
            environment.getProperty(
                "environment.images.extension-to-media-type.${extension.toLowerCase()}",
                String::class.java,
                ""
            )
        )
    }

    /**
     * サポートしている拡張子か判定
     */
    private fun hasSupportedExtension(path: String): Boolean {
        val extension = FilenameUtils.getExtension(path)
        val supportedExtensions = environment.getProperty(
            "environment.images.supported-extensions", List::class.java
        )
        return supportedExtensions?.let { extensions ->
            extensions.any { e ->
                e?.equals(extension.toLowerCase()) == true
            }
        } == true
    }

    /**
     * サポートしているメディアタイプか判定
     */
    private fun isSupportedMediaType(byteArray: ByteArray): Boolean
    {
        val config =
    }
}