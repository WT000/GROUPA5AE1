package org.solent.oodd.ae1.web.dao.properties;

import java.io.File;
import java.nio.file.Files;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Used to create or get properties 
 * @author cgallen
 */
public class WebObjectFactory {

    final static Logger LOG = LogManager.getLogger(WebObjectFactory.class);

    private static PropertiesDao propertiesDao = null;

    /**
     * Used to get the PropertiesDao of the application
     * @return The properties DAO
     */
    public static PropertiesDao getPropertiesDao() {
        if (propertiesDao == null) {
            synchronized (WebObjectFactory.class) {
                if (propertiesDao == null) {
                    // creates a single instance of the dao
                    String TEMP_DIR = System.getProperty("java.io.tmpdir");
                    
                    File propertiesFile = new File(TEMP_DIR + "/application.properties");
                    LOG.debug("using system temp directory: " + TEMP_DIR);
                    LOG.debug("using application properties file : " + propertiesFile.getAbsolutePath());
                    propertiesDao = new PropertiesDao(propertiesFile.getAbsolutePath());
                }
            }
        }
        return propertiesDao;
    }
}
