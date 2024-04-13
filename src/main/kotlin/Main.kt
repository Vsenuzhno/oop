data class Post(
    val id: Int = 0,
    val ownerId: Int? = null,
    val fromId: Int? = null,
    val createdBy: Int = 0,
    val authorName: String = "",
    val text: String? = null,
    val replyOwnerId: Int = 0,
    val replyPostId: Int = 0,
    val published: Long = 0,
    val likes: Int = 0,
    val numbersOfComments: NumbersOfComments = NumbersOfComments(),
    val attachments: Array<Attachment> = emptyArray()
)

data class NumbersOfComments(val numbersOfComments: Int = 0)

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

object WallService {
    var posts = emptyArray<Post>()
    private var nextId = 0

    fun add(post: Post): Post {
        posts += post.copy(
            id = ++nextId, numbersOfComments = post.numbersOfComments.copy(), attachments = post.attachments.copyOf()
        )
        return posts.last()
    }

    fun update(newPost: Post): Boolean {
        for ((index, post) in posts.withIndex()) {
            if (post.id == newPost.id) {
                posts[index] = post.copy(
                    ownerId = newPost.ownerId ?: post.ownerId,
                    fromId = newPost.fromId ?: post.fromId,
                    text = newPost.text ?: post.text,
                    likes = newPost.likes,
                    numbersOfComments = newPost.numbersOfComments.copy(),
                    attachments = newPost.attachments.copyOf()
                )
                return true
            }
        }
        return false
    }


    fun printPosts() {
        for (post in posts) {
            println("Пост: ")
            println(printPostWithoutAttachments(post))
            print(" ")
            println("Вложение: ")
            for (attachment in post.attachments) {
                println(attachment)
            }
            println()
        }
    }
    fun printPostWithoutAttachments(post: Post) {
        println("Post(id=${post.id}, ownerId=${post.ownerId}, fromId=${post.fromId}, createdBy=${post.createdBy}, authorName=${post.authorName}, text=${post.text}, replyOwnerId=${post.replyOwnerId}, replyPostId=${post.replyPostId}, published=${post.published}, likes=${post.likes}, numbersOfComments=${post.numbersOfComments})")
    }

    fun likeById(id: Int) {
        for ((index, post) in posts.withIndex()) {
            if (post.id == id) {
                posts[index] = post.copy(likes = post.likes + 1)
            }
        }
    }

    fun clear() {
        posts = emptyArray()
        nextId = 0
    }
}


fun main(args: Array<String>) {

    val numbersOfComments = NumbersOfComments(3)
    val photo = Photo(1, 1, 1, 1, "Красивое фото", 13)
    val photoAttachment = PhotoAttachment(photo = photo)
    val audio = Audio(1, 1, "Engelbert Humperdinck", "From Here To Eternity", 183)
    val audioAttachment = AudioAttachment(audio = audio)
    val video = Video(1, 1, "My video", "Clip", 183)
    val videoAttachment = VideoAttachment(video = video)
    val file = File(1, 1, "Document", 1024, "pdf")
    val fileAttachment = FileAttachment(file = file)
    val graffiti = Graffiti(1, 1, "https://graffiti.com/music.png", 130, 130)
    val graffitiAttachment = GraffitiAttachment(graffiti = graffiti)


    WallService.add(
        Post(
            1,
            2,
            1,
            3,
            "Hello!",
            "hi",
            3,
            33,
            3,
            2,
            numbersOfComments,
            arrayOf(photoAttachment, audioAttachment, videoAttachment, fileAttachment, graffitiAttachment)
        )
    )

    WallService.printPosts()
    //  WallService.update(Post(1, 2, 1, 3, "Kira", "hi!!", 3, 33, 3, 2, numbersOfComments, arrayOf(photoAttachment)))
    //  WallService.printPosts()
}
