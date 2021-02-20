package ml.zouren.blogdemo.repository

import ml.zouren.blogdemo.entity.Blog
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BlogRepositoryTests @Autowired constructor(
        val blogRepository: BlogRepository
) {
    val blogRecord1 = Blog(id = 1, title = "Kotlin programming language",
            content = "Modern, concise and safe programming language")
    val blogRecord2 = Blog(id = 2, title = "JavaScript programming language",
            content = "Lightweight, interpreted, or just-in-time compiled programming language")

    @Test
    fun crud() {
        // Create 2 blog records
        blogRepository.save(blogRecord1)
        blogRepository.save(blogRecord2)

        // Read from db
        assertEquals(blogRepository.count(), 2)
        var blog = blogRepository.findByIdOrNull(blogRecord1.id!!)
        assertTrue(blog == blogRecord1)

        // Delete blog record 1
        blogRepository.deleteById(blogRecord1.id!!)

        // Read from db
        assertEquals(blogRepository.count(), 1)
        blog = blogRepository.findByIdOrNull(blogRecord1.id!!)
        assertThat(blog).isEqualTo(null)
    }
}