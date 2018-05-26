/*
 * BinUtils - access various binary formats from Java
 *
 * (C) Copyright 2018 - Matthias Bläsing - mblaesing@doppel-helix.eu
 *
 * Licensed under Apache License v2.
 */
package nl.lxtreme.binutils.elf;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.ByteBuffer;

public abstract class ArmParameterType<T>
{

    public static final ArmParameterType<Integer> UINT32 = new ArmParameterType<Integer>(Integer.class)
    {
        @Override
        public Integer readFromByteBuffer(ByteBuffer bb)
        {
            return bb.getInt();
        }
    };
    public static final ArmParameterType<String> NTBS = new ArmParameterType<String>(String.class)
    {
        @Override
        public String readFromByteBuffer(ByteBuffer bb)
        {
            int startingPos = bb.position();
            byte currentByte;
            do
            {
                currentByte = bb.get();
            } while (currentByte != '\0' && bb.position() <= bb.limit());
            int terminatingPosition = bb.position();
            byte[] data = new byte[terminatingPosition - startingPos - 1];
            bb.position(startingPos);
            bb.get(data);
            bb.position(bb.position() + 1);
            try
            {
                return new String(data, "ASCII");
            } catch (UnsupportedEncodingException ex)
            {
                throw new RuntimeException(ex);
            }
        }
    };
    public static final ArmParameterType<BigInteger> ULEB128 = new ArmParameterType<BigInteger>(BigInteger.class)
    {
        @Override
        public BigInteger readFromByteBuffer(ByteBuffer bb)
        {
            BigInteger result = BigInteger.ZERO;
            int shift = 0;
            while (true)
            {
                byte b = bb.get();
                result = result.or(BigInteger.valueOf(b & 127).shiftLeft(shift));
                if ((b & 128) == 0)
                {
                    break;
                }
                shift += 7;
            }
            return result;
        }
    };

    private final Class<T> javaRepresentation;

    public ArmParameterType(Class<T> javaRepresentation)
    {
        this.javaRepresentation = javaRepresentation;
    }

    public abstract T readFromByteBuffer(ByteBuffer bb);

}
