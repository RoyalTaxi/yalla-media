package uz.yalla.client.core.photo.gallery.repository

import app.cash.paging.PagingSource
import uz.yalla.client.core.photo.gallery.model.YallaMediaImage

internal interface YallaGalleryRepository {
    suspend fun getCount(): Int

    suspend fun getByOffset(offset: Int): YallaMediaImage?

    fun getPicturePagingSource(): PagingSource<Int, YallaMediaImage>
}
