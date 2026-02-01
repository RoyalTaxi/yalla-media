package uz.yalla.client.core.photo.camera

internal sealed interface CameraAccess {
    data object Undefined : CameraAccess

    data object Denied : CameraAccess

    data object Authorized : CameraAccess
}
