package com.example.uppercase;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

class CustomerObjectDesializer extends StdDeserializer<ObservabilityRecord> {

    protected CustomerObjectDesializer(Class<?> vc) {
        super(vc);
    }

    public CustomerObjectDesializer() {
        this(null);
    }

    @Override
    public ObservabilityRecord deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);

        return new ObservabilityRecord(null, node.get("demoText").toString().toUpperCase(), Integer.valueOf(node.get("demoValue").toString()));

    }
}
