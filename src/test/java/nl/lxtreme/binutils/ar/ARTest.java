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
package nl.lxtreme.binutils.ar;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.StringWriter;
import java.util.Collection;

import org.junit.Test;

import nl.lxtreme.binutils.AbstractTestCase;


/**
 * Test cases for AR.
 */
public class ARTest extends AbstractTestCase
{
  // METHODS

  /**
   * Test method for {@link nl.lxtreme.binutils.ar.AR#AR(java.io.File)}.
   */
  @Test
  public void testAR() throws Exception
  {
    final File resource = getResource("test.ar");
    final AR ar = new AR(resource);
    assertNotNull(ar);
  }

  /**
   * Test method for {@link nl.lxtreme.binutils.ar.AR#getEntries()}.
   */
  @Test
  public void testGetEntries() throws Exception
  {
    final File resource = getResource("test.ar");
    final AR ar = new AR(resource);
    assertNotNull(ar);

    Collection<AREntry> entries = ar.getEntries();
    assertNotNull(entries);

    assertEquals(3, entries.size());

    for (AREntry entry : entries)
    {
      if ("dd".equals(entry.getFileName()))
      {
        assertEquals(54040, entry.getSize());
      }
      else if ("hello.c".equals(entry.getFileName()))
      {
        assertEquals(87, entry.getSize());
      }
      else if ("long_file_name_1234567890".equals(entry.getFileName()))
      {
        assertEquals(0, entry.getSize());
      }
    }
  }

  /**
   * Test method for {@link nl.lxtreme.binutils.ar.AR#getEntryNames()}.
   */
  @Test
  public void testGetEntryNames() throws Exception
  {
    final File resource = getResource("test.ar");
    final AR ar = new AR(resource);
    assertNotNull(ar);

    Collection<String> entries = ar.getEntryNames();
    assertNotNull(entries);

    assertEquals(3, entries.size());
    assertTrue(entries.contains("dd"));
    assertTrue(entries.contains("hello.c"));
    assertTrue(entries.contains("long_file_name_1234567890"));
  }

  /**
   * Test method for
   * {@link nl.lxtreme.binutils.ar.AR#readFile(java.io.Writer, java.lang.String)}.
   */
  @Test
  public void testReadFile() throws Exception
  {
    final File resource = getResource("test.ar");
    final AR ar = new AR(resource);
    assertNotNull(ar);

    StringWriter sw = new StringWriter();

    assertTrue(ar.readFile(sw, "hello.c"));

    String string = sw.toString();
    assertNotNull(string);
    assertEquals(87, string.length());

    sw = new StringWriter();

    assertTrue(ar.readFile(sw, "dd"));

    string = sw.toString();
    assertNotNull(string);
    assertEquals(54040, string.length());

    sw = new StringWriter();

    assertTrue(ar.readFile(sw, "long_file_name_1234567890"));

    string = sw.toString();
    assertNotNull(string);
    assertEquals(0, string.length());
  }

}
