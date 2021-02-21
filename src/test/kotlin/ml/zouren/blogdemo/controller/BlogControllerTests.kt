package ml.zouren.blogdemo.controller

import ml.zouren.blogdemo.entity.Blog
import ml.zouren.blogdemo.entity.BlogInput
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters

@SpringBootTest
class BlogControllerTests {
    val testClient: WebTestClient =
            WebTestClient.bindToServer().baseUrl("http://localhost:8080").build()

    @Test
    fun `Test all APIs`() {
        testClient.get().uri("/blogs").exchange().expectStatus().isOk
        testClient.delete().uri("/blogs/1").exchange()
        testClient.delete().uri("/blogs/1").exchange().expectStatus().isNotFound
        testClient.get().uri("/blogs/1").exchange().expectStatus().isNotFound
        testClient.post().uri("/blogs")
                .contentType(MediaType.APPLICATION_JSON)
                .body((BodyInserters.fromObject(BlogInput(title = "t", content = "c"))))
                .exchange().expectStatus().isCreated
        testClient.post().uri("/blogs")
                .contentType(MediaType.APPLICATION_JSON)
                .body((BodyInserters.fromObject(BlogInput(title = "t"))))
                .exchange().expectStatus().isEqualTo(422)
        testClient.post().uri("/blogs")
                .contentType(MediaType.APPLICATION_JSON)
                .body((BodyInserters.fromObject(BlogInput(content = "c"))))
                .exchange().expectStatus().isEqualTo(422)
    }
}