/*
 * BinUtils - access various binary formats from Java
 *
 * (C) Copyright 2016 - JaWi - j.w.janssen@lxtreme.nl
 *
 * Licensed under Apache License v2. 
 */
package nl.lxtreme.binutils.elf;


import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import nl.lxtreme.binutils.AbstractTestCase;


/**
 * Provides test cases for {@link Elf}.
 */
public class ElfTest extends AbstractTestCase
{
  // METHODS

  @Test
  public void testReadDynamicElfFileOk() throws Exception
  {
    doTestReadElfObject( getResource( "ts_print" ) );
  }

  @Test
  public void testReadOtherDynamicElfFileOk() throws Exception
  {
    doTestReadElfObject( getResource( "con_flash" ) );
  }

  @Test
  public void testReadStaticElfFileOk() throws Exception
  {
    doTestReadElfObject( getResource( "miniBench_neon" ) );
  }

  @Test
  public void testReadOtherStaticElfFileOk() throws Exception
  {
    doTestReadElfObject( getResource( "helloWorld_static" ) );
  }

  private void doTestReadElfObject( File resource ) throws Exception
  {
    Elf e = new Elf( resource );
    assertNotNull( e );

    SectionHeader[] sections = e.sectionHeaders;
    assertNotNull( sections );

    ProgramHeader[] programHeaders = e.programHeaders;
    assertNotNull( programHeaders );

    dumpProgramHeaders( programHeaders );

    Header header = e.header;
    assertNotNull( header );

    System.out.printf( "Entry point: 0x%x\n", header.entryPoint );
  }

  /**
   * @param aProgramHeaders
   */
  private void dumpProgramHeaders( ProgramHeader[] aProgramHeaders )
  {
    for ( ProgramHeader ph : aProgramHeaders )
    {
      System.out.printf( "Type:\t\t %s\n", ph.type );
      System.out.printf( "Virtual address: 0x%x\n", ph.virtualAddress );
      System.out.printf( "Physical address:0x%x\n", ph.physicalAddress );
      System.out.printf( "Memory size:\t 0x%x\n", ph.segmentMemorySize );
      System.out.println();
    }
  }

}
