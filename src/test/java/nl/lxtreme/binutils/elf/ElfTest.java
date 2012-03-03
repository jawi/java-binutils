/*******************************************************************************
 * Copyright (c) 2011 - J.W. Janssen
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     J.W. Janssen - Cleanup and some small API changes.
 *******************************************************************************/
package nl.lxtreme.binutils.elf;


import static org.junit.Assert.*;

import java.io.*;
import java.net.*;

import org.junit.*;


/**
 * Provides test cases for {@link Elf}.
 */
public class ElfTest
{
  // METHODS

  /**
   * Test method for {@link nl.lxtreme.binutils.elf.Elf#Elf(java.lang.String)}.
   */
  @Test
  public void testReadDynamicElfFileOk() throws Exception
  {
    File resource = getResource("ts_print");
    Elf e = new Elf(resource);
    assertNotNull(e);

    e.loadSymbols();

    Attribute attributes = e.getAttributes();
    assertNotNull(attributes);

    Section[] sections = e.getSections();
    assertNotNull(sections);

    ProgramHeader[] programHeaders = e.getProgramHeaders();
    assertNotNull(programHeaders);

    dumpProgramHeaders(programHeaders);
  }

  /**
   * Test method for {@link nl.lxtreme.binutils.elf.Elf#Elf(java.lang.String)}.
   */
  @Test
  public void testReadOtherDynamicElfFileOk() throws Exception
  {
    File resource = getResource("con_flash");
    Elf e = new Elf(resource);
    assertNotNull(e);

    e.loadSymbols();

    Attribute attributes = e.getAttributes();
    assertNotNull(attributes);

    Section[] sections = e.getSections();
    assertNotNull(sections);

    ProgramHeader[] programHeaders = e.getProgramHeaders();
    assertNotNull(programHeaders);

    dumpProgramHeaders(programHeaders);
    
    ElfHeader header = e.getHeader();
    assertNotNull(header);
    
    System.out.printf("Entry point: 0x%x\n", header.getEntryPoint());
  }

  /**
   * Test method for {@link nl.lxtreme.binutils.elf.Elf#Elf(java.lang.String)}.
   */
  @Test
  public void testReadStaticElfFileOk() throws Exception
  {
    File resource = getResource("miniBench_neon");
    Elf e = new Elf(resource);
    assertNotNull(e);

    e.loadSymbols();

    Attribute attributes = e.getAttributes();
    assertNotNull(attributes);

    Section[] sections = e.getSections();
    assertNotNull(sections);

    ProgramHeader[] programHeaders = e.getProgramHeaders();
    assertNotNull(programHeaders);

    dumpProgramHeaders(programHeaders);
    
    ElfHeader header = e.getHeader();
    assertNotNull(header);
    
    System.out.printf("Entry point: 0x%x\n", header.getEntryPoint());
  }

  /**
   * @param aProgramHeaders
   */
  private void dumpProgramHeaders(ProgramHeader[] aProgramHeaders)
  {
    for (ProgramHeader ph : aProgramHeaders)
    {
      System.out.printf("Type:\t\t %s\n", ph.getTypeName());
      System.out.printf("Virtual address: 0x%x\n", ph.getVirtualAddress());
      System.out.printf("Physical address:0x%x\n", ph.getPhysicalAddress());
      System.out.printf("Memory size:\t 0x%x\n", ph.getMemorySize());
      System.out.println();
    }
  }

  /**
   * @param aName
   * @return
   * @throws URISyntaxException
   */
  private File getResource(String aName) throws Exception
  {
    URL url = getClass().getClassLoader().getResource(aName);
    if ((url != null) && "file".equals(url.getProtocol()))
    {
      return new File(url.getPath()).getCanonicalFile();
    }
    fail("Resource " + aName + " not found!");
    return null; // to keep compiler happy...
  }
}
