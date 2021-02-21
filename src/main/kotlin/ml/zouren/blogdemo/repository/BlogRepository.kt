package ml.zouren.blogdemo.repository

import ml.zouren.blogdemo.entity.Blog
import org.springframework.data.repository.CrudRepository
//import org.springframework.data.rest.core.annotation.RepositoryRestResource

//@RepositoryRestResource
interface BlogRepository: CrudRepository<Blog, Int>