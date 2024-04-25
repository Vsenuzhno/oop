interface Attachment {
    val type: String
}

data class PhotoAttachment(
    override val type: String = "photo", val photo: Photo
) : Attachment

data class Photo(
    val id: Int, val albumId: Int, val ownerId: Int, val userId: Int, val text: String, val data: Int
)

data class AudioAttachment(
    override val type: String = "audio", val audio: Audio
) : Attachment

data class Audio(
    val id: Int, val ownerId: Int, val artist: String, val title: String, val duration: Int
)

data class VideoAttachment(
    override val type: String = "video", val video: Video
) : Attachment

data class Video(
    val id: Int, val ownerId: Int, val title: String, val description: String, val duration: Int
)

data class FileAttachment(
    override val type: String = "file", val file: File
) : Attachment

data class File(
    val id: Int, val ownerId: Int, val title: String, val size: Int, val ext: String
)

data class GraffitiAttachment(
    override val type: String = "graffiti", val graffiti: Graffiti
) : Attachment

data class Graffiti(
    val id: Int, val ownerId: Int, val url: String, val width: Int, val height: Int

)
