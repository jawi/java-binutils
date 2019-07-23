package nl.lxtreme.binutils;

import static org.junit.Assert.fail;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;

public abstract class AbstractTestCase {

	  /**
	   * @param aName
	   * @return
	   * @throws URISyntaxException
	   */
	  protected File getResource(String aName) throws Exception
	  {
	    URL url = getClass().getClassLoader().getResource(aName);
	    if ((url != null) && "file".equals(url.getProtocol()))
	    {
	    	String path = URLDecoder.decode(url.getPath(), "UTF-8");
	      return new File(path).getCanonicalFile();
	    }
	    fail("Resource " + aName + " not found!");
	    return null; // to keep compiler happy...
	  }

}
