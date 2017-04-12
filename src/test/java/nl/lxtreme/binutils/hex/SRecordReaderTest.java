/*
 * BinUtils - access various binary formats from Java
 *
 * (C) Copyright 2016 - JaWi - j.w.janssen@lxtreme.nl
 *
 * Licensed under Apache License v2.
 */
package nl.lxtreme.binutils.hex;


import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

import org.junit.*;


/**
 *
 */
public class SRecordReaderTest
{
  // CONSTANTS

  private static final String SRECORD_S0_1 = "S00600004844521B";

  private static final String SRECORD_S1_1 = "S1130000285F245F2212226A000424290008237C2A";
  private static final String SRECORD_S1_2 = "S11300100002000800082629001853812341001813";
  private static final String SRECORD_S1_3 = "S113002041E900084E42234300182342000824A952";
  private static final String SRECORD_S1_4 = "S107003000144ED492";

  private static final String SRECORD_S5_1 = "S5030004F8";

  private static final String SRECORD_S9_1 = "S9030000FC";

  public static final int SRECORD_SIZE = 54;
  public static final String SRECORD_FILE = //
      "S00B000064656D6F2E686578DC\n" //
          + "S11300000AC022C021C020C01FC01EC01DC01CC009\n" //
          + "S11300101BC01AC019C011241FBECFEDCDBF10E004\n" //
          + "S1130020A0E6B0E0E4E6F0E003C0C89531960D9296\n" //
          + "S1130030A036B107D1F710E0A0E6B0E001C01D92F0\n" //
          + "S1130040A036B107E1F701C0DBCFCFEDD0E0DEBFD2\n" //
          + "S1130050CDBF8FEF87BB88BB8150E9F780E090E08C\n" //
          + "S107006000C0FFCF0A\n" //
          + "S9030000FC";
  private static final int[] SRECORD_DATA = { 0x0A, 0xC0, 0x22, 0xC0, 0x21, 0xC0, 0x20, 0xC0, 0x1F, 0xC0, 0x1E, 0xC0,
      0x1D, 0xC0, 0x1C, 0xC0, 0x1B, 0xC0, 0x1A, 0xC0, 0x19, 0xC0, 0x11, 0x24, 0x1F, 0xBE, 0xCF, 0xED, 0xCD, 0xBF, 0x10,
      0xE0, 0xA0, 0xE6, 0xB0, 0xE0, 0xE4, 0xE6, 0xF0, 0xE0, 0x03, 0xC0, 0xC8, 0x95, 0x31, 0x96, 0x0D, 0x92, 0xA0, 0x36,
      0xB1, 0x07, 0xD1, 0xF7, 0x10, 0xE0, 0xA0, 0xE6, 0xB0, 0xE0, 0x01, 0xC0, 0x1D, 0x92, 0xA0, 0x36, 0xB1, 0x07, 0xE1,
      0xF7, 0x01, 0xC0, 0xDB, 0xCF, 0xCF, 0xED, 0xD0, 0xE0, 0xDE, 0xBF, 0xCD, 0xBF, 0x8F, 0xEF, 0x87, 0xBB, 0x88, 0xBB,
      0x81, 0x50, 0xE9, 0xF7, 0x80, 0xE0, 0x90, 0xE0, 0x00, 0xC0, 0xFF, 0xCF };

  // METHODS

  @Test
  public void testReadByte() throws Exception
  {
    final SRecordReader provider = new SRecordReader( new StringReader( SRECORD_FILE ) );

    int address = 0x0000;
    int readByte;

    do
    {
      readByte = provider.readByte();
      if ( readByte == -1 )
      {
        break;
      }

      assertData( SRECORD_DATA, address, readByte );
      assertAddress( address++, provider );
    }
    while ( readByte != -1 );

    assertEquals( 100, address );
  }

  /**
   * Tests that parsing SRecords with invalid data fails.
   */
  @Test( expected = IOException.class )
  public void testS0Record_parseInvalidDataFail1() throws Exception
  {
    // S0 records should have data...
    final SRecordReader parser = new SRecordReader( new StringReader( "S0030000ab" ) );
    readAllDataBytes( parser );
  }

  /**
   * Tests that parsing SRecords with invalid data fails.
   */
  @Test( expected = IOException.class )
  public void testS0Record_parseInvalidDataFail2() throws Exception
  {
    // S0 records should have data...
    final SRecordReader parser = new SRecordReader( new StringReader( "S0030001ab" ) );
    readAllDataBytes( parser );
  }

  /**
   * Tests that an SRecord with a valid checksum is detected as such.
   */
  @Test( expected = IOException.class )
  public void testS0Record_parseValidChecksumFail() throws Exception
  {
    // Wrong checksum (0x1A), should be 0x1B...
    SRecordReader parser = new SRecordReader( new StringReader( "S00600004844521A" ) );

    readAllDataBytes( parser );
  }

  /**
   * Tests that an SRecord with a valid checksum is detected as such.
   */
  @Test
  public void testS0Record_parseValidChecksumOk() throws Exception
  {
    SRecordReader parser = new SRecordReader( new StringReader( "S00600004844521B" ) );

    readAllDataBytes( parser );
  }

  /**
   * Tests that parsing SRecords with invalid data fails.
   *
   * @throws FileFormatException
   * @throws IOException
   * @throws NoSuchElementException
   */
  @Test
  public void testS0Record_parseValidDataOk() throws Exception
  {
    SRecordReader parser = new SRecordReader( new StringReader( "S004000012e9" ) );

    // S0 records should have data...
    readAllDataBytes( parser );
  }

  /**
   * Tests that parsing a valid S0 record works correctly.
   *
   * @throws FileFormatException
   * @throws IOException
   * @throws NoSuchElementException
   */
  @Test
  public void testS0RecordParseOk() throws Exception
  {
    SRecordReader parser = new SRecordReader( new StringReader( SRECORD_S0_1 ) );
    readAllDataBytes( parser );
  }

  /**
   * Tests that parsing SRecords with invalid data fails.
   *
   * @throws FileFormatException
   * @throws IOException
   * @throws NoSuchElementException
   */
  @Test( expected = IOException.class )
  public void testS1Record_parseInvalidDataFail() throws Exception
  {
    // S1 records should have data...
    SRecordReader parser = new SRecordReader( new StringReader( "S1030000ab" ) );
    readAllDataBytes( parser );
  }

  /**
   * Tests that parsing SRecords with invalid data fails.
   *
   * @throws FileFormatException
   * @throws IOException
   * @throws NoSuchElementException
   */
  @Test
  public void testS1Record_parseValidDataOk() throws Exception
  {
    // S1 records should have data...
    SRecordReader parser = new SRecordReader( new StringReader( "S104000012e9" ) );
    readAllDataBytes( parser );
  }

  /**
   * Tests that a correct S1-record is parsed correctly.
   *
   * @throws FileFormatException
   * @throws IOException
   * @throws NoSuchElementException
   */
  @Test
  public void testS1RecordParseOk1() throws Exception
  {
    SRecordReader parser = new SRecordReader( new StringReader( SRECORD_S1_1 ) );

    parser.readByte();
    assertAddress( 0x0000, parser );

    readAllDataBytes( parser );
  }

  /**
   * Tests that a correct S1-record is parsed correctly.
   *
   * @throws FileFormatException
   * @throws IOException
   * @throws NoSuchElementException
   */
  @Test
  public void testS1RecordParseOk2() throws Exception
  {
    SRecordReader parser = new SRecordReader( new StringReader( SRECORD_S1_2 ) );

    parser.readByte();
    assertAddress( 0x0010, parser );

    readAllDataBytes( parser );
  }

  /**
   * Tests that a correct S1-record is parsed correctly.
   *
   * @throws FileFormatException
   * @throws IOException
   * @throws NoSuchElementException
   */
  @Test
  public void testS1RecordParseOk3() throws Exception
  {
    SRecordReader parser = new SRecordReader( new StringReader( SRECORD_S1_3 ) );

    parser.readByte();
    assertAddress( 0x0020, parser );

    readAllDataBytes( parser );
  }

  /**
   * Tests that a correct S1-record is parsed correctly.
   *
   * @throws FileFormatException
   * @throws IOException
   * @throws NoSuchElementException
   */
  @Test
  public void testS1RecordParseOk4() throws Exception
  {
    SRecordReader parser = new SRecordReader( new StringReader( SRECORD_S1_4 ) );

    parser.readByte();
    assertAddress( 0x0030, parser );

    readAllDataBytes( parser );
  }

  /**
   * Tests that parsing SRecords with invalid data fails.
   *
   * @throws FileFormatException
   * @throws IOException
   * @throws NoSuchElementException
   */
  @Test( expected = IOException.class )
  public void testS2Record_parseInvalidDataFail() throws Exception
  {
    // S2 records should have data...
    SRecordReader parser = new SRecordReader( new StringReader( "S204000000ab" ) );
    parser.readByte();
  }

  /**
   * Tests that parsing SRecords with invalid data fails.
   *
   * @throws FileFormatException
   * @throws IOException
   * @throws NoSuchElementException
   */
  @Test
  public void testS2Record_parseValidDataOk() throws Exception
  {
    // S2 records should have data...
    SRecordReader parser = new SRecordReader( new StringReader( "S20500000012ab" ) );
    assertNotNull( parser.readByte() );
  }

  /**
   * Tests that parsing SRecords with invalid data fails.
   *
   * @throws IOException
   * @throws NoSuchElementException
   */
  @Test( expected = IOException.class )
  public void testS3Record_parseInvalidDataFail() throws Exception
  {
    // S3 records should have data...
    SRecordReader parser = new SRecordReader( new StringReader( "S30500000000ab" ) );
    parser.readByte();
  }

  /**
   * Tests that parsing SRecords with invalid data fails.
   *
   * @throws FileFormatException
   * @throws IOException
   * @throws NoSuchElementException
   */
  @Test
  public void testS3Record_parseValidDataOk() throws Exception
  {
    // S3 records should have data...
    SRecordReader parser = new SRecordReader( new StringReader( "S3060000000012ab" ) );
    assertNotNull( parser.readByte() );
  }

  /**
   * Tests that parsing SRecords with invalid types fails.
   *
   * @throws IOException
   * @throws NoSuchElementException
   */
  @Test( expected = IOException.class )
  public void testS4Record_parseInvalidTypeFail() throws Exception
  {
    // S4 is not a valid SRecord type (it is not defined as such)...
    SRecordReader parser = new SRecordReader( new StringReader( "S403000000" ) );
    parser.readByte();
  }

  /**
   * Tests that parsing SRecords with invalid data fails.
   *
   * @throws IOException
   * @throws NoSuchElementException
   */
  @Test( expected = IOException.class )
  public void testS5Record_parseInvalidDataFail() throws Exception
  {
    // S5 records should have NO data...
    SRecordReader parser = new SRecordReader( new StringReader( "S5060000000012ab" ) );
    parser.readByte();
  }

  /**
   * Tests that parsing SRecords with invalid data fails.
   *
   * @throws FileFormatException
   * @throws IOException
   * @throws NoSuchElementException
   */
  @Test
  public void testS5Record_parseValidDataOk() throws Exception
  {
    // S5 records should have NO data...
    SRecordReader parser = new SRecordReader( new StringReader( "S5030000fc" ) );
    assertNotNull( parser.readByte() );
  }

  /**
   * @throws FileFormatException
   * @throws IOException
   * @throws NoSuchElementException
   */
  @Test
  public void testS5RecordParseOk() throws Exception
  {
    SRecordReader parser = new SRecordReader( new StringReader( SRECORD_S5_1 ) );

    parser.readByte();
    assertAddress( 0x0004, parser );

    readAllDataBytes( parser );
  }

  /**
   * Tests that parsing SRecords with invalid types fails.
   *
   * @throws IOException
   * @throws NoSuchElementException
   */
  @Test( expected = IOException.class )
  public void testS6Record_parseInvalidTypeFail() throws Exception
  {
    // S6 is not a valid SRecord type (it is not defined as such)...
    SRecordReader parser = new SRecordReader( new StringReader( "S603000000" ) );
    parser.readByte();
  }

  /**
   * @throws FileFormatException
   * @throws IOException
   * @throws NoSuchElementException
   */
  @Test
  public void testS9RecordParseOk() throws Exception
  {
    SRecordReader parser = new SRecordReader( new StringReader( SRECORD_S9_1 ) );

    parser.readByte();
    assertAddress( 0x0000, parser );

    readAllDataBytes( parser );
  }

  /**
   * Tests that parsing SRecords with invalid types fails.
   *
   * @throws IOException
   * @throws NoSuchElementException
   */
  @Test
  public void testSRecord_parseInvalidTypeFail() throws Exception
  {
    SRecordReader parser = new SRecordReader( new StringReader( "AB03000000" ) );
    assertEquals( -1, parser.readByte() );
  }

  private void readAllDataBytes( final SRecordReader aProvider ) throws IOException
  {
    int ch;
    do
    {
      ch = aProvider.readByte();
    }
    while ( ch != -1 );
  }

  private static void assertAddress( int address, SRecordReader hexParser ) throws IOException
  {
    assertEquals( "Mismatch at 0x" + Integer.toHexString( address ), address, hexParser.getAddress() );
  }

  private static void assertData( int[] expectedData, int address, int dataByte )
  {
    assertTrue(
        String.format( "Mismatch at 0x%04x; expected %02x but was %02x", address, expectedData[address], dataByte ),
        expectedData[address] == dataByte );
  }
} /* SRecordDataProviderTest */

/* EOF */
