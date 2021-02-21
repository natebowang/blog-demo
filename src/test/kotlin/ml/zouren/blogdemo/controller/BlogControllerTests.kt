package ml.zouren.blogdemo.controller

import ml.zouren.blogdemo.entity.BlogInput
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BlogControllerTests {
    @LocalServerPort
    val port: Int = 0

    private lateinit var testClient: WebTestClient

    @BeforeAll
    fun initClient() {
        testClient =
                WebTestClient.bindToServer().baseUrl("http://localhost:$port").build()
    }

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