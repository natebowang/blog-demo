package ml.zouren.blogdemo.repository

import ml.zouren.blogdemo.entity.Blog
import org.springframework.data.repository.CrudRepository

interface BlogRepository: CrudRepository<Blog, Int>