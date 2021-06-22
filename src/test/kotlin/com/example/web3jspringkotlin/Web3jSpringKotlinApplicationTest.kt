package com.example.web3jspringkotlin

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class Web3jSpringKotlinApplicationTest {

    @Autowired
    private lateinit var web3jService: Web3jService

    @Test
    fun contextLoads() {
    }

    @Test
    fun `getClientVersionSync()`() {
        val clientVersion = web3jService.getClientVersionSync()
        println(clientVersion.web3ClientVersion)
    }

    @Test
    fun `getClientVersionCoroutine()`() {
        val clientVersion = runBlocking {
            web3jService.getClientVersionCoroutine()
        }
        println(clientVersion.web3ClientVersion)
    }
}
