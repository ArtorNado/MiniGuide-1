package com.example.miniguide.common.remote

import com.google.gson.*
import java.lang.reflect.Type

class ListTypeAdapter<R : BaseResponse>(private val cls: Class<R>) : JsonDeserializer<R> {
    companion object {
        const val LIST_SERIALIZED_NAME = "routes"
    }

    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement, typeOfT: Type, context: JsonDeserializationContext
    ): R = Gson().fromJson("{\"$LIST_SERIALIZED_NAME\":$json}", cls)
}