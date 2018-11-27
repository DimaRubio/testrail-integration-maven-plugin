package com.github.dimarubio;


import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;

/**
 * Goal and phase.
 */
@Mojo( name = "getTestCasesIDFromRun", defaultPhase = LifecyclePhase.COMPILE )
public class MyMojo
    extends AbstractMojo
{
    /**
     * Location of the file.
     */
    @Parameter( defaultValue = "${project.build.directory}", property = "getTestCasesIDFromRun.outputDir", required = true )
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
        try
        {
            TestRailHelper testRailHelper = new TestRailHelper( testRailURL, userEmail, password, numberOfTestRailRun);
//            testRailHelper.setNumberOfTestRailRun();
            String testCasesTags = "";
            for (Long id: testRailHelper.getTestCasesIDFromRun()) {
//                testCasesTags = testCasesTags + "@C" + id + ",";
                testCasesTags = String.format("%s@C%s,", testCasesTags, id);
            }
            new PropertiesHelper().saveProperties("testCasesTags", testCasesTags);
        }
        catch ( Exception e )
        {
            getLog().error(e.fillInStackTrace());
            throw new MojoExecutionException( "Error ", e );
        }
        getLog().info("File testCaseIDFromRUN.properties is generated");



//        File f = outputDirectory;
//
//        if ( !f.exists() )
//        {
//            f.mkdirs();
//        }
//
//        File touch = new File( f, "touch.txt" );
//
//        FileWriter w = null;
//        try
//        {
//            w = new FileWriter( touch );
//
//            w.write( "touch.txt" );
//        }
//        catch ( IOException e )
//        {
//            throw new MojoExecutionException( "Error creating file " + touch, e );
//        }
//        finally
//        {
//            if ( w != null )
//            {
//                try
//                {
//                    w.close();
//                }
//                catch ( IOException e )
//                {
//                    // ignore
//                }
//            }
//        }
    }
}
