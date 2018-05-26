/*
 * BinUtils - access various binary formats from Java
 *
 * (C) Copyright 2018 - Matthias Bläsing - mblaesing@doppel-helix.eu
 *
 * Licensed under Apache License v2.
 */
package nl.lxtreme.binutils.elf;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

class ArmAttributes {
    /**
     * Name of pseudo vendor for ARM Eabi attributes
     */
    public static final String AEABI = "aeabi";
    private Map<String,ByteBuffer> attributeBuffers = new HashMap<>();

    public ArmAttributes(ByteBuffer bb) throws IOException
    {
        byte format = bb.get();
        if (format != 0x41)
        {
            // Version A
            // Not supported
            throw new IOException(String.format("Unrecognized format: %02x", format));
        }

        Map<String,Map<Integer,Map<ArmAeabiAttributesTag,Object>>> result = new HashMap<>();
        while (bb.position() < bb.limit()) {
            int posSectionStart = bb.position();
            int sectionLength = bb.getInt();
            if (sectionLength <= 0) {
                // Fail!
                break;
            }

            String vendorName = ArmParameterType.NTBS.readFromByteBuffer(bb);

            ByteBuffer sectionData = bb.slice();
            sectionData.order(bb.order());
            sectionData.limit(sectionLength - (bb.position() - posSectionStart));

            attributeBuffers.put(vendorName, sectionData);

            bb.position(posSectionStart + sectionLength);
        }
    }

    public boolean hasVendorName(String name) {
        return attributeBuffers.containsKey(name);
    }

    public ByteBuffer getByVendorName(String name) {
        return attributeBuffers.get(name);
    }

}
