/*
 * BinUtils - access various binary formats from Java
 *
 * (C) Copyright 2016 - JaWi - j.w.janssen@lxtreme.nl
 *
 * Licensed under Apache License v2.
 */
package nl.lxtreme.binutils.hex.util;


import static org.junit.Assert.*;

import org.junit.*;


/**
 *
 */
public class HexUtilsTest
{
  // METHODS

  /**
   * Test method for {@link HexUtils#parseHexByte(java.lang.CharSequence, int)}.
   */
  @Test
  public void testParseHexByte() throws Exception
  {
    assertEquals( ( byte )0x01, HexUtils.parseHexByte( new char[] { '0', '1' } ) );
    assertEquals( ( byte )0x12, HexUtils.parseHexByte( new char[] { '1', '2' } ) );
    assertEquals( ( byte )0x23, HexUtils.parseHexByte( new char[] { '2', '3' } ) );
    assertEquals( ( byte )0xFF, HexUtils.parseHexByte( new char[] { 'F', 'f' } ) );
  }

} /* HexUtilsTest */

/* EOF */
