package uz.yalla.client.core.photo.utils

/**
 * Compresses an image to ensure it's under 1MB
 * @param imageBytes Original image bytes
 * @param maxSizeBytes Maximum size in bytes (default 1MB)
 * @param maxDimension Maximum width/height dimension (default 1024px)
 * @return Compressed image bytes
 */
expect fun compressImage(
    imageBytes: ByteArray,
    maxSizeBytes: Int = 1024 * 1024, // 1MB
    maxDimension: Int = 1024
): ByteArray
