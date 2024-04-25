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

data class Comment(
    val id: Int = 0,
    val fromId: Int = 0,
    val data: Int,
    val text: String? = null,
    val replyToUser: Int? = null,
    val replyToComment: Int? = null
)

data class NumbersOfComments(val numbersOfComments: Int = 0)