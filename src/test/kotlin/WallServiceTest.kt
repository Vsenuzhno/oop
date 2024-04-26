import WallService.createReport
import org.junit.Assert.*
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
    fun createCommentNonExistingPost() {
        val comment = Comment(1, 2, 1, "Comment text", 3, 33)
        WallService.createComment(100, comment)
    }

    @Test
    fun testCreateReportInvalidReason() {
        try {
            createReport(1, 1, 9)
            fail("Некорректная причина")
        } catch (e: IllegalArgumentException) {
            assertEquals("Причина жалобы не существует: 9", e.message)
        }
    }

    @Test
    fun testCreateReportNonExistingComment() {
        try {
            createReport(1, 2, 1)
            fail("Некорректный идентификатор комментария")
        } catch (e: CommentNotFoundException) {
            assertEquals("Комментарий с id 2 не найден", e.message)
        }
    }
}