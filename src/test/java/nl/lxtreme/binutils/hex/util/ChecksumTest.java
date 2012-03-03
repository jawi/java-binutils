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
public class ChecksumTest
{
  // CONSTANTS

  private static final byte[] SET1 = { 0x01, 0x02, 0x03, 0x04, 0x40, 0x30, 0x20, 0x10 };
  private static final byte[] SET2 = { 0x01, 0x02, 0x03, 0x04, 0x05, 0x06 };
  private static final int[] SET3 = { 0x01, 0x02, 0x03, 0x04, 0x40, 0x30, 0x20, 0x10 };

  // METHODS

  /**
   * Test method for {@link nl.lxtreme.cpemu.util.Checksum#calculate(byte[])}.
   */
  @Test
  public void testCalculateOnesComplementByteArray()
  {
    assertEquals((byte) 0x55, Checksum.ONES_COMPLEMENT.calculate(SET1));
    assertEquals((byte) 0xEA, Checksum.ONES_COMPLEMENT.calculate(SET2));
  }

  /**
   * Test method for {@link nl.lxtreme.cpemu.util.Checksum#calculate(int[])}.
   */
  @Test
  public void testCalculateOnesComplementIntArray()
  {
    assertEquals(0xFF55, Checksum.ONES_COMPLEMENT.calculate(SET3));
  }

  /**
   * Test method for {@link nl.lxtreme.cpemu.util.Checksum#calculate(byte[])}.
   */
  @Test
  public void testCalculateTwosComplementByteArray()
  {
    assertEquals((byte) 0x56, Checksum.TWOS_COMPLEMENT.calculate(SET1));
    assertEquals((byte) 0xEB, Checksum.TWOS_COMPLEMENT.calculate(SET2));
  }

  /**
   * Test method for {@link nl.lxtreme.cpemu.util.Checksum#calculate(int[])}.
   */
  @Test
  public void testCalculateTwosComplementIntArray()
  {
    assertEquals(0xFF56, Checksum.TWOS_COMPLEMENT.calculate(SET3));
  }

} /* ChecksumTest */

/* EOF */
