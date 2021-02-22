package io.sketchware.api.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class SnsSerializer(
    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("snsSerializer")
) : KSerializer<Boolean> {
    override fun deserialize(decoder: Decoder): Boolean {
        return decoder.decodeString() == "Y"
    }

    override fun serialize(encoder: Encoder, value: Boolean) {
        encoder.encodeString(
            if (value) "Y"
            else "N"
        )
    }
}