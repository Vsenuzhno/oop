data class Post(
    val id: Int = 0,
    val ownerId: Int = 0,
    val fromId: Int = 0,
    val createdBy: Int = 0,
    val authorName: String = "",
    val text: String = "",
    val replyOwnerId: Int = 0,
    val replyPostId: Int = 0,
    val published: Long = 0,
    val likes: Int = 0,
    val numbersOfComments: NumbersOfComments = NumbersOfComments()
)

data class NumbersOfComments(val numbersOfComments: Int = 0)

object WallService {
    var posts = emptyArray<Post>()
    private var nextId = 0

    fun add(post: Post): Post {
        posts += post.copy(id = ++nextId, numbersOfComments = post.numbersOfComments.copy())
        return posts.last()
    }

    fun update(newPost: Post): Boolean {
        for ((index, post) in posts.withIndex()) {
            if (post.id == newPost.id) {
                posts[index] = newPost.copy()
                return true
            }
        }
        return false
    }

    fun printPosts() {
        for (post in posts) {
            print(post)
            print(" ")
        }
        println()
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
    WallService.add(Post(1, 2, 1, 3, "Hello!", "hi", 3, 33, 3, 2, numbersOfComments))
    WallService.printPosts()
    WallService.update(Post(1, 2, 1, 3, "Kira", "hi!!", 3, 33, 3, 2, numbersOfComments))
    WallService.printPosts()
}
