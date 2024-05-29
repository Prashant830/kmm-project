import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

class Greeting {
    private val platformName = getPlatform()
    private val engineProvider = getEngine()
    private val client = createHttpClient(engineProvider)

    fun greet(): String {
        return "Hello, $platformName!"
    }

    fun greetWithPosts(callback: (String) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            val message = withContext(Dispatchers.Default) {
                val response = fetchPosts()
                if (response.status == HttpStatusCode.OK) {
                    val posts: List<Post> = response.body()
                    val firstPost = posts.toString()
                    "\nHere's the first post from Api response -> ${firstPost.toString()}"
                } else {
                    "Hello, $platformName! Failed to fetch posts."
                }
            }
            callback(message)
        }
    }

    private suspend fun fetchPosts(): HttpResponse {
        return client.get("https://jsonplaceholder.typicode.com/posts")
    }


}

// Example usage (adjust as necessary depending on your context):
// greeting.greetWithPosts { message -> println(message) }
