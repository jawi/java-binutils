/*******************************************************************************
 * Copyright (c) 2011, J.W. Janssen
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     J.W. Janssen - Cleanup and make API more OO-oriented.
 *******************************************************************************/
package nl.lxtreme.binutils.hex.util;


import static org.junit.Assert.*;

import org.junit.*;


/**
 * 
 */
public class HexUtilsTest
{
  // CONSTANTS

  private static final String TEST_SET1 = "0123456789abcdef";

  // METHODS

  /**
   * Test method for {@link HexUtils#parseHexByte(java.lang.CharSequence, int)}.
   */
  @Test
  public void testParseHexByte() throws Exception
  {
    assertEquals(0x01, HexUtils.parseHexByte(TEST_SET1, 0));
    assertEquals(0x12, HexUtils.parseHexByte(TEST_SET1, 1));
    assertEquals(0x23, HexUtils.parseHexByte(TEST_SET1, 2));
  }

  /**
   * Test method for
   * {@link HexUtils#parseHexBytes(java.lang.CharSequence, int, int)}.
   */
  @Test
  public void testParseHexBytes() throws Exception
  {
    assertArrayEquals(new int[] { 0x01, 0x23, 0x45, 0x67, 0x89, 0xab, 0xcd, 0xef }, HexUtils.parseHexBytes(TEST_SET1,
        0, 8));
  }

  /**
   * Test method for {@link HexUtils#parseHexWord(java.lang.CharSequence, int)}.
   */
  @Test
  public void testParseHexWord() throws Exception
  {
    assertEquals(0x0123, HexUtils.parseHexWord(TEST_SET1, 0));
    assertEquals(0x2345, HexUtils.parseHexWord(TEST_SET1, 2));
  }

} /* HexUtilsTest */

/* EOF */
