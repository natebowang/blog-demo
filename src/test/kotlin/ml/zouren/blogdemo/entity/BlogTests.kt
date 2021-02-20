package ml.zouren.blogdemo.entity

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BlogTests {
    @Test
    fun equalsTest(){
        val b1 = Blog(1, "a", "c")
        val b2 = Blog(1, "a", "c")
        assertTrue(b1 == b2)

        val b3 = Blog(2, "a", "c")
        val b4 = Blog(1, "b", "c")
        val b5 = Blog(1, "a", "d")
        assertFalse(b1 == b3)
        assertFalse(b1 == b4)
        assertFalse(b1 == b5)
    }

    @Test
    fun hashCodeTest(){
        val b1 = Blog(1, "a", "c")
        assertEquals(b1.hashCode(), b1.id)

        val b2 = Blog(title = "a", content = "c")
        assertEquals(b2.hashCode(), -1)
    }
}
