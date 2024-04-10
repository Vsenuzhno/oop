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
        val post = WallService.add(Post( 1, 2,1,3, "Hello!", "hi", 3,33,3,2))
        assertTrue(post.id != 0)
    }

    @Test
    fun updateExisting() {
        WallService.add(Post( 1, 2,1,3, "Hello!", "hi", 3,33,3,2))
        val post = Post(1,2,1,3,"Kira", "hi!!",3,33,3,2)
        assertTrue(WallService.update(post))
    }

    @Test
    fun updateNonExisting() {
        val post = Post(1,2,1,3,"Kira", "hi!!",3,33,3,2)
        assertFalse(WallService.update(post))
    }
}