package io.sketchware.api.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class StringLongSerializer(
    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("stringLongSerializer")
) : KSerializer<Long> {
    override fun deserialize(decoder: Decoder): Long {
        return decoder.decodeString().toLong()
    }

    override fun serialize(encoder: Encoder, value: Long) {
        encoder.encodeString(value.toString())
    }
}