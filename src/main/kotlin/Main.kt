import javax.xml.stream.events.Comment

data class Post(
    val id: Int,
    val ownerId: Int,
    val fromId: Int,
    val createdBy: Int,
    val authorName: String,
    val text: String,
    val replyOwnerId: Int,
    val replyPostId: Int,
    val published: Long,
    val likes: Int,
)


object WallService {
    var posts = emptyArray<Post>()
    private var nextId = 0

    fun add(post: Post): Post {
        posts += post.copy(id = ++nextId)
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

    fun printPosts(){
        for (post in posts){
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

    WallService.add(Post( 1, 2,1,3, "Hello!", "hi", 3,33,3,2))
    WallService.printPosts()
    WallService.update(Post(1,2,1,3,"Kira", "hi!!",3,33,3,2))
    WallService.printPosts()
}
