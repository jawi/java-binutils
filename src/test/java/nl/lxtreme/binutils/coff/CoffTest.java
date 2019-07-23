/*
 * BinUtils - access various binary formats from Java
 *
 * (C) Copyright 2016 - JaWi - j.w.janssen@lxtreme.nl
 *
 * Licensed under Apache License v2. 
 */
package nl.lxtreme.binutils.coff;


import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import nl.lxtreme.binutils.AbstractTestCase;


public class CoffTest extends AbstractTestCase
{

  @Test
  public void testReadECoffFile() throws Exception
  {
    try (Coff coff = new Coff( getResource( "arcdiag" ) ))
    {
      assertNotNull( coff );
    }
  }

}
