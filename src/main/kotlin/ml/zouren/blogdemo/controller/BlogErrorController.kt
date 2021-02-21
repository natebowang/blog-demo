package ml.zouren.blogdemo.controller

import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BlogErrorController : ErrorController {

    override fun getErrorPath(): String? {
        return "/error"
    }

    @RequestMapping("/error")
    fun error(): String? {
        return "Error"
    }
}