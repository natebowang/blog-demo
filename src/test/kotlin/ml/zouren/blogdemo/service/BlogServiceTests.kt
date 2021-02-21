package ml.zouren.blogdemo.service

import ml.zouren.blogdemo.entity.Blog
import ml.zouren.blogdemo.repository.BlogRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class BlogServiceTests {
    // @Mock
    // lateinit var blogRespository: BlogRepository
    private var mockBlogRepository: BlogRepository = mock(BlogRepository::class.java)

    @InjectMocks
    var blogService = BlogService()


    val blogRecord1 = Blog(id = 1, title = "Kotlin programming language",
            content = "Modern, concise and safe programming language")
    val blogRecord2 = Blog(id = 2, title = "JavaScript programming language",
            content = "Lightweight, interpreted, or just-in-time compiled programming language")
    var blogs = mutableListOf(blogRecord1, blogRecord2)

    @Test
    fun findAllTest() {
        Mockito.`when`(mockBlogRepository.findAll()).thenReturn(blogs)
        assertEquals(blogService.findAll().size, 2)
        assertEquals(blogService.findAll()[0], blogRecord1)
    }

    // todo: Mock findByIdOrNull by MockK
}
