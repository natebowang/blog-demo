package ml.zouren.blogdemo.service

import ml.zouren.blogdemo.entity.Blog
import ml.zouren.blogdemo.repository.BlogRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.annotation.Resource
import kotlin.collections.List

@Service
class BlogService {
    @Resource
    lateinit var blogRepository: BlogRepository

    fun save(blog: Blog): Blog {
        return blogRepository.save(blog)
    }

    fun deleteById(id: Int) {
        blogRepository.deleteById(id)
    }

    fun updateTitleById(id: Int, title: String): Blog? {
        val record = blogRepository.findByIdOrNull(id)
        return if (record != null) {
            record.title = title
            blogRepository.save(record)
        } else {
            null
        }
    }

    fun updateContentById(id: Int, content: String): Blog? {
        val record = blogRepository.findByIdOrNull(id)
        return if (record != null) {
            record.title = content
            blogRepository.save(record)
        } else {
            null
        }
    }

    fun findByIdOrNull(blogId: Int): Blog? {
        return blogRepository.findByIdOrNull(blogId)
    }

    fun findAll(): List<Blog> {
        val blogList = mutableListOf<Blog>()
        blogRepository.findAll().forEach { blogList.add(it) }
        return blogList
    }

}