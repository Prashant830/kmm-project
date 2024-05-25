import android.os.Build
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.cio.CIO

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

class AndroidKtorEngineProvider : KtorEngineProvider {
    override fun getKtorEngine(): HttpClientEngineFactory<*> = CIO
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun getEngine(): KtorEngineProvider = AndroidKtorEngineProvider()
