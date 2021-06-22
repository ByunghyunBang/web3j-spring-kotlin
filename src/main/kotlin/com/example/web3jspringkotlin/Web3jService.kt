package com.example.web3jspringkotlin

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.methods.response.Web3ClientVersion
import java.util.concurrent.Future

@Service
class Web3jService(
    private val web3j: Web3j
) {
    fun getClientVersionSync(): Web3ClientVersion {
        return web3j.web3ClientVersion().send()
    }

    suspend fun getClientVersionCoroutine(): Web3ClientVersion {
        return withContext(Dispatchers.IO) {
            web3j.web3ClientVersion().send()
        }
    }

    suspend fun getClientVersionCoroutineWithSomeHack(): Web3ClientVersion {
        return web3j.web3ClientVersion().sendAsync().awaitOrNull() ?: throw Exception("timeout exception")
    }
}

suspend fun <T> Future<T>.awaitOrNull(timeoutMs: Int = 60000): T? {
    val start = System.currentTimeMillis()
    while (!isDone) {
        if (System.currentTimeMillis() - start > timeoutMs)
            return null
        delay(1)
    }
    return get()
}
