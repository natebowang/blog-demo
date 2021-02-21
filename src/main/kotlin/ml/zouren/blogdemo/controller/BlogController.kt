package ml.zouren.blogdemo.controller

import ml.zouren.blogdemo.entity.Blog
import ml.zouren.blogdemo.entity.BlogInput
import ml.zouren.blogdemo.service.BlogService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import javax.annotation.Resource


@RestController
class BlogController {
    @Resource
    lateinit var blogService: BlogService

    // curl -v -d '{ "title":"ttt", "content": "ccc"}' -H "Content-Type:application/json" http://localhost:8080/blogs
    // 201
    // curl -v -d '{ "title":"ttt"}' -H "Content-Type:application/json" http://localhost:8080/blogs
    // 422
    @PostMapping("/blogs")
    fun createBlog(@RequestBody blogInput: BlogInput): ResponseEntity<Blog> {
        val blog = blogInput.toBlog()
        if (blog != null) {
            return ResponseEntity(blogService.save(blog), HttpStatus.CREATED)
        } else {
            throw ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, "Missing non-null fields");
        }
    }

    // curl -v "http://localhost:8080/blogs/1"
    // 200
    // 404
    @GetMapping("/blogs/{id}")
    fun getBlogById(@PathVariable id: Int): Blog {
        if (blogService.findByIdOrNull(id) != null) {
            return blogService.findByIdOrNull(id)!!
        } else {
            throw ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Blog Not Found");
        }
    }

    // curl -v "http://localhost:8080/blogs"
    // 200
    @GetMapping("/blogs")
    fun getAllBlogs(): List<Blog> {
        return blogService.findAll()
    }

    // curl -vX DELETE "http://localhost:8080/blogs/1"
    // 204
    // 404
    @DeleteMapping("/blogs/{id}")
    fun deleteBlog(@PathVariable id: Int):ResponseEntity<Void> {
        if (blogService.findByIdOrNull(id) != null) {
            blogService.deleteById(id)
            return ResponseEntity(HttpStatus.NO_CONTENT)
        } else {
            throw ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Blog Not Found");
        }
    }
}