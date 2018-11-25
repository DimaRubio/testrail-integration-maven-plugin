/***
 * Примеры по работе с *.properties : https://www.mkyong.com/java/java-properties-file-examples/
 */
package com.github.dimarubio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesHelper {

    public void saveProperties(String tagName, String tagValue) throws IOException {

        Properties prop = new Properties();
        OutputStream output = null;
        try {
            output = new FileOutputStream("testCaseIDFromRUN.properties");
            prop.setProperty(tagName, tagValue);
            prop.store(output, null); // save properties to project root folder
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
