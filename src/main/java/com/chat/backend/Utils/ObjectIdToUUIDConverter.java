package com.chat.backend.Utils;

import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import java.util.UUID;

public class ObjectIdToUUIDConverter implements Converter<ObjectId, UUID> {
    @Override
    public UUID convert(ObjectId source) {
        return UUID.nameUUIDFromBytes(source.toByteArray());
    }
}

