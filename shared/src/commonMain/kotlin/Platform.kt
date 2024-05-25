import io.ktor.client.engine.HttpClientEngineFactory

interface Platform {
    val name: String
}

interface KtorEngineProvider {
    fun getKtorEngine(): HttpClientEngineFactory<*>
}


expect fun getPlatform(): Platform

expect fun getEngine(): KtorEngineProvider


