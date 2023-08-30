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
) {
    fun copy(
        data: T? = this.data,
        meta: MetaDTO? = null,
        status: StatusDTO? = null
    ): ResponseDTO<T> {
        return ResponseDTO(
            data ?: this.data,
            meta ?: this.meta,
            status ?: this.status
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
