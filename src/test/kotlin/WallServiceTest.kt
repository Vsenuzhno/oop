import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class WallServiceTest {
    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun addPost() {
        val post = WallService.add(Post(0, 2, "Hello!", "hi", 33, 2))
        assertTrue(post.id != 0)
    }

    @Test
    fun updateExisting() {
        WallService.add(Post(0, 2, "Hello!", "hi", 33, 2))
        val post = Post(1, 2, "Kira", "hi!!", 33, 2)
        assertTrue(WallService.update(post))
    }

    @Test
    fun updateNonExisting() {
        val post = Post(100, 2, "Kira", "hi!!", 33, 2)
        assertFalse(WallService.update(post))
    }
}