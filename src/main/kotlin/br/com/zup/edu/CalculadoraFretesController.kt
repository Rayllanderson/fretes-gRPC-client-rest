package br.com.zup.edu

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue

@Controller("/api/fretes")
class CalculadoraFretesController(
    val grpcClient: FretesServiceGrpc.FretesServiceBlockingStub
) {

    @Get
    fun calcula(@QueryValue cep: String): FreteResponse {

        val request = CalculaFreteRequest.newBuilder()
            .setCep(cep)
            .build()

        val response = grpcClient.calculaFrete(request)

        return FreteResponse(cep = response.cep, valor = response.valor)
    }
}

data class FreteResponse(
    val cep: String,
    val valor: Double
)
