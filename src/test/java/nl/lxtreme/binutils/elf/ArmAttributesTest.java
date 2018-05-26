/*
 * BinUtils - access various binary formats from Java
 *
 * (C) Copyright 2018 - Matthias Bläsing - mblaesing@doppel-helix.eu
 *
 * Licensed under Apache License v2.
 */
package nl.lxtreme.binutils.elf;

import java.io.File;
import java.net.URL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class ArmAttributesTest
{

    @Test
    public void testReadingBuildFlags() throws Exception
    {
        Elf elf = new Elf(getResource("ts_print"));
        for (SectionHeader sh : elf.sectionHeaders)
        {
            if (".ARM.attributes".equals(sh.getName()))
            {
                ArmAttributes armAttributes = new ArmAttributes(elf.getSection(sh));
                if (armAttributes.hasVendorName(ArmAttributes.AEABI))
                {
                    ArmEabiAttributes eabi = new ArmEabiAttributes(armAttributes.getByVendorName(ArmAttributes.AEABI));
                    assertEquals(0x04, eabi.getFileAttribute(ArmAeabiAttributesTag.CPU_arch).intValue());
                    assertEquals(0x01, eabi.getFileAttribute(ArmAeabiAttributesTag.ARM_ISA_use).intValue());
                }
            }
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
