/*
 * BinUtils - access various binary formats from Java
 *
 * (C) Copyright 2016 - JaWi - j.w.janssen@lxtreme.nl
 *
 * Licensed under Apache License v2. 
 */
package nl.lxtreme.binutils.coff;


import static org.junit.Assert.*;

import java.io.*;
import java.net.*;

import org.junit.*;


public class CoffTest
{

  @Test
  public void testReadECoffFile() throws Exception
  {
    try (Coff coff = new Coff( getResource( "arcdiag" ) ))
    {
      assertNotNull( coff );
    }
  }

  private File getResource( String aName ) throws Exception
  {
    URL url = getClass().getClassLoader().getResource( aName );
    if ( ( url != null ) && "file".equals( url.getProtocol() ) )
    {
      return new File( url.getPath() ).getCanonicalFile();
    }
    fail( "Resource " + aName + " not found!" );
    return null; // to keep compiler happy...
  }
}
