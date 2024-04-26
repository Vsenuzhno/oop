class PostNotFoundException(message: String) : RuntimeException(message)
class CommentNotFoundException(message: String) : RuntimeException(message)
class IllegalArgumentException(message: String) : RuntimeException(message)

object WallService {

    private var posts = emptyArray<Post>()
    private var comments = mutableListOf<Comment>()
    private var reports = mutableListOf<Report>()
    private var nextId = 0
    private var nextCommentId = 0

    fun add(post: Post): Post {
        posts += post.copy(
            id = ++nextId,
            numbersOfComments = post.numbersOfComments.copy(),
            attachments = post.attachments.copyOf()
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
//
//    fun likeById(id: Int) {
//        for ((index, post) in posts.withIndex()) {
//            if (post.id == id) {
//                posts[index] = post.copy(likes = post.likes + 1)
//            }
//        }
//    }

    fun createComment(postId: Int, comment: Comment): Comment {
        if (!posts.any { it.id == postId }) {
            throw PostNotFoundException("Пост с id $postId не найден")
        }
        comments += comment.copy(id = ++nextCommentId)
        return comments.last()
    }

    fun createReport(ownerId: Int, commentId: Int, reason: Int) {
        if (!comments.any { it.id == commentId }) {
            throw CommentNotFoundException("Комментарий с id $commentId не найден")
        }
        if (reason !in 0..8) {
            throw IllegalArgumentException("Причина жалобы не существует: $reason")
        }
        reports.add(Report(ownerId = ownerId, commentId = commentId, reason = reason))
    }

    fun clear() {
        posts = emptyArray()
        nextId = 0
    }
}

