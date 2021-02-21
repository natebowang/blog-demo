package ml.zouren.blogdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication
class BlogDemoApplication

fun main(args: Array<String>) {
    runApplication<BlogDemoApplication>(*args)
}


