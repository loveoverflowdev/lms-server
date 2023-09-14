package dtos.response

import com.google.gson.annotations.SerializedName

class ResponseDTO<T>(
    @SerializedName(value = "data")
    val data: T? = null,

    @SerializedName(value = "meta")
    val meta: MetaDTO = MetaDTO(
        timestamp = "",
    ),

    @SerializedName(value = "status")
    val status: StatusDTO = StatusDTO(
        code = 200,
    ),

    @SerializedName(value = "tokens")
    val tokens: TokensDTO? = null
) {
    fun copy(
        data: T? = this.data,
        meta: MetaDTO? = null,
        status: StatusDTO? = null,
        tokens: TokensDTO? = null,
    ): ResponseDTO<T> {
        return ResponseDTO(
            data ?: this.data,
            meta ?: this.meta,
            status ?: this.status,
            tokens ?: this.tokens
        )
    }
}

class StatusDTO(
    @SerializedName(value = "code")
    val code: Int,

    @SerializedName(value = "message")
    val message: String = "",
)

class MetaDTO(
    @SerializedName(value = "timestamp")
    val timestamp: String,
)

class TokensDTO(
    @SerializedName(value = "accessToken")
    val accessToken: String?,

    @SerializedName(value = "refreshToken")
    val refreshToken: String?,
)
