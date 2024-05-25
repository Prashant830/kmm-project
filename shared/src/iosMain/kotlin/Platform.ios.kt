import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

class IosKtorEngineProvider : KtorEngineProvider {
    override fun getKtorEngine(): HttpClientEngineFactory<*> = Darwin
}

actual fun getPlatform(): Platform = IOSPlatform()

actual fun getEngine(): KtorEngineProvider = IosKtorEngineProvider()

