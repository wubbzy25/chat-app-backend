package com.chat.backend.Utils;

import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;

import java.util.UUID;

public class UUIDToObjectIdConverter implements Converter<UUID, ObjectId> {
    @Override
    public ObjectId convert(UUID source) {
        return new ObjectId(source.toString().replace("-", "").substring(0, 24));
    }
}
