/*
 * BinUtils - access various binary formats from Java
 *
 * (C) Copyright 2018 - Matthias Bläsing - mblaesing@doppel-helix.eu
 *
 * Licensed under Apache License v2.
 */
package nl.lxtreme.binutils.elf;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ArmEabiAttributes
{
    private Map<ArmAeabiAttributesTag, Object> fileAttributes = Collections.EMPTY_MAP;

    public ArmEabiAttributes(ByteBuffer buffer) {
        while (buffer.position() < buffer.limit()) {
            int pos = buffer.position();
            int subsectionTag = ArmParameterType.ULEB128.readFromByteBuffer(buffer).intValue();
            int length = buffer.getInt();
            if (subsectionTag ==  ArmAeabiAttributesTag.File.getValue()) {
                fileAttributes = parseFileAttribute(buffer);
            }
            buffer.position(pos + length);
        }
    }

    public Map<ArmAeabiAttributesTag, Object> getFileAttributes() {
        return fileAttributes;
    }

    public <T> T getFileAttribute(ArmAeabiAttributesTag<T> tag) {
        return (T) fileAttributes.get(tag);
    }

    private static Map<ArmAeabiAttributesTag, Object> parseFileAttribute(ByteBuffer bb) {
        Map<ArmAeabiAttributesTag, Object> result = new HashMap<>();
        while (bb.position() < bb.limit()) {
            int tagValue = ArmParameterType.ULEB128.readFromByteBuffer(bb).intValue();
            ArmAeabiAttributesTag tag = ArmAeabiAttributesTag.getByValue(tagValue);
            result.put(tag, tag.getParameterType().readFromByteBuffer(bb));
        }
        return result;
    }
}
