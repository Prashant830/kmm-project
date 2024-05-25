import io.ktor.client.HttpClient
import io.ktor.client.call.body

import io.ktor.client.*
import io.ktor.client.engine.HttpClientEngineFactory

import io.ktor.client.plugins.contentnegotiation.ContentNegotiation

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)

fun createHttpClient(engineProvider: KtorEngineProvider): HttpClient {
    return HttpClient(engineProvider.getKtorEngine()) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
    }
}

class Greeting()  {
    private val platformName = getPlatform()
    private val engineProvider = getEngine()

    private val client = createHttpClient(engineProvider)

    fun greet(): String {
        return "Hello, $platformName!"
    }

    fun greetWithPosts(): String = runBlocking {
        val response: HttpResponse = fetchPosts()
        if (response.status == HttpStatusCode.OK) {
            val posts: List<Post> = response.body()
            val firstPost = posts.firstOrNull()
            if (firstPost != null) {
                "\nHere's the first post: from Api response -> ${firstPost.title}"
            } else {
                "Hello, $platformName! No posts found."
            }
        } else {
            "Hello, $platformName! Failed to fetch posts."
        }
    }

    private suspend fun fetchPosts(): HttpResponse {
        return client.get("https://jsonplaceholder.typicode.com/posts")
    }

/**/
}