import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.js.Js

class WasmPlatform: Platform {
    override val name: String = "Web with Kotlin/Wasm"
}


class WasmKtorEngineProvider : KtorEngineProvider {
    override fun getKtorEngine(): HttpClientEngineFactory<*> = Js
}


actual fun getPlatform(): Platform = WasmPlatform()

actual fun getEngine():KtorEngineProvider =  WasmKtorEngineProvider()