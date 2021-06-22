package com.example.web3jspringkotlin

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.methods.response.Web3ClientVersion

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
}
