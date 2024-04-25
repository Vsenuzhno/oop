import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class WallServiceTest {
    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun addPost() {
        val post = WallService.add(Post(1, 2, 1, 3, "Hello!", "hi", 3, 33, 3, 2))
        assertTrue(post.id != 0)
    }

    @Test
    fun updateExisting() {
        WallService.add(Post(1, 2, 1, 3, "Hello!", "hi", 3, 33, 3, 2))
        val post = Post(1, 2, 1, 3, "Kira", "hi!!", 3, 33, 3, 2)
        assertTrue(WallService.update(post))
    }

    @Test
    fun updateNonExisting() {
        val post = Post(1, 2, 1, 3, "Kira", "hi!!", 3, 33, 3, 2)
        assertFalse(WallService.update(post))
    }

    @Test
    fun createCommentCorrectPost() {
        val post = WallService.add(Post(1, 2, 1, 3, "Hello!", "hi", 3, 33, 3, 2))
        val comment = Comment(1, 2, 1, "3", 3, 3)
        WallService.createComment(post.id, comment)
    }

    @Test(expected = PostNotFoundException::class)
    fun createComment_nonExistingPost() {
        val comment = Comment(1, 2, 1, "Comment text", 3, 33)
        WallService.createComment(100, comment)
    }

}