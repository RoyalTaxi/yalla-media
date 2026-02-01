package uz.yalla.client.core.photo.gallery.model

import android.net.Uri

internal data class YallaMediaImage(
    val id: Long,
    val uri: Uri,
    val name: String?
)
