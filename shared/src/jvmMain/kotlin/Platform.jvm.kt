import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.apache.Apache

class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JVMPlatform()


class KtorEngineProviderImpl : KtorEngineProvider {
    override fun getKtorEngine(): HttpClientEngineFactory<*> = Apache
}

actual fun getEngine(): KtorEngineProvider {
    return KtorEngineProviderImpl()
}