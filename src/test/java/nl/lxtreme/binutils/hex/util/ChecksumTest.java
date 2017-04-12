/*
 * BinUtils - access various binary formats from Java
 *
 * (C) Copyright 2017 - JaWi - j.w.janssen@lxtreme.nl
 *
 * Licensed under Apache License v2.
 */
package nl.lxtreme.binutils.hex.util;


import static org.junit.Assert.*;

import org.junit.*;


/**
 *
 */
public class ChecksumTest
{
  // CONSTANTS

  private static final byte[] SET1 = { 0x01, 0x02, 0x03, 0x04, 0x40, 0x30, 0x20, 0x10 };
  private static final byte[] SET2 = { 0x01, 0x02, 0x03, 0x04, 0x05, 0x06 };
  private static final byte[] SET3 = { 0x02, 0x00, 0x00, 0x04, 0x00, 0x01 };

  // METHODS

  @Test
  public void testCalculateOnesComplementByteArray()
  {
    assertEquals( ( byte )0x55, Checksum.ONES_COMPLEMENT.instance().add( SET1 ).getResult() );
    assertEquals( ( byte )0xEA, Checksum.ONES_COMPLEMENT.instance().add( SET2 ).getResult() );
    assertEquals( ( byte )0xF8, Checksum.ONES_COMPLEMENT.instance().add( SET3 ).getResult() );
  }

  @Test
  public void testCalculateTwosComplementByteArray()
  {
    assertEquals( ( byte )0x56, Checksum.TWOS_COMPLEMENT.instance().add( SET1 ).getResult() );
    assertEquals( ( byte )0xEB, Checksum.TWOS_COMPLEMENT.instance().add( SET2 ).getResult() );
    assertEquals( ( byte )0xF9, Checksum.TWOS_COMPLEMENT.instance().add( SET3 ).getResult() );
  }

} /* ChecksumTest */

/* EOF */
