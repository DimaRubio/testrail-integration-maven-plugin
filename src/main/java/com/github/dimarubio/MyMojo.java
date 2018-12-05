package com.github.dimarubio;


import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Goal and phase.
 */
@Mojo( name = "getTestCasesIDFromRun", defaultPhase = LifecyclePhase.COMPILE )
public class MyMojo
    extends AbstractMojo
{
    @Parameter(defaultValue = "${project.basedir}")
    private File outputDirectory;

    @Parameter( property = "testRailURL", required = true )
    private String testRailURL;

    @Parameter( property = "userEmail", required = true )
    private String userEmail;

    @Parameter( property = "password", required = true )
    private String password;

    @Parameter( defaultValue = "0", property = "numberOfTestRailRun", required = false )
    private String numberOfTestRailRun;

    public void execute()
        throws MojoExecutionException
    {
        getLog().info("Start preparing property file with testcases ID");
        getLog().info("Testrail run number: " + numberOfTestRailRun);
        if (!outputDirectory.exists()) {
            outputDirectory.mkdirs();
        }
        try
        {
            TestRailHelper testRailHelper = new TestRailHelper( testRailURL, userEmail, password, numberOfTestRailRun);
            String testCasesTags = "";
            for (Long id: testRailHelper.getTestCasesIDFromRun()) {
                testCasesTags = String.format("%s@C%s,", testCasesTags, id);
            }
            saveProperties("testCasesTags", testCasesTags);
            getLog().info("File testCaseIDFromRUN.properties is generated to " + outputDirectory.toString());
        }
        catch ( Exception e )
        {
            getLog().error(e.fillInStackTrace());
            throw new MojoExecutionException( "Error ", e );
        }
    }

    public void saveProperties(String tagName, String tagValue) throws IOException {
        Properties prop = new Properties();
        OutputStream output = null;
        try {
            File propertyFile = new File( outputDirectory,"testCaseIDFromRUN.properties");
            output = new FileOutputStream(propertyFile);
            prop.setProperty(tagName, tagValue);
            prop.store(output, null);
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
