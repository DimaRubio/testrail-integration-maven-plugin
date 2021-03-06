testrail-integration-maven-plugin
============================

Create a property file with name 'testCaseIDFromRUN.properties' in project build directory, which contains tests tags for run

Quickstart
----------

1) Add the following to your POM file:
```xml
    <pluginRepositories>
        <pluginRepository>
            <id>testrail-integration-maven-plugin</id>
            <url>https://github.com/DimaRubio/testrail-integration-maven-plugin/raw/mvn-repo/</url>
            <releases>
                <enabled>true</enabled>
                <!--<updatePolicy>always</updatePolicy>-->
            </releases>
            <snapshots>
                <enabled>true</enabled>
                <!--<updatePolicy>always</updatePolicy>-->
            </snapshots>
        </pluginRepository>
    </pluginRepositories>
```  
```xml
            <plugin>
                <groupId>com.github.dimarubio</groupId>
                <artifactId>testrail-integration-maven-plugin</artifactId>
                <version>0.0.2-SNAPSHOT</version>
                <configuration>
                    <!-- URL of your TestRail server -->
                    <testRailURL>https://testrail.server/</testRailURL>
                    <!-- User email in TestRail  -->
                    <userEmail>test@test.com</userEmail>
                    <!-- User password in TestRail -->
                    <password>123456</password>
                    <!-- Number of run in TestRail, with the default values = 0 -->
                    <!--<numberOfTestRailRun></numberOfTestRailRun>-->
                    <!--<outputDirectory>${project.basedir}</outputDirectory>-->
                    <!--<tagFormatPattern>C</tagFormatPattern>-->
                    <!--<keyPropertyTitle>tags</keyPropertyTitle>-->
                </configuration>
                <executions>
                    <execution>
                        <phase>validate</phase>
                        <goals>
                            <goal>getTestCasesIDFromRun</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
```
2) Create Run in TestRail and get its ID (Example: 1).

3) All configuration parameters can be set from the command line by referencing a system property that the user sets via the -D option.
Example: mvn compile -DnumberOfTestRailRun=1

After that you can see in terminal something like that:
```xml
[INFO] --- testrail-integration-maven-plugin:0.0.2-SNAPSHOT:getTestCasesIDFromRun (default) @ vdr-tests ---
[INFO] Start preparing property file with testcases ID
[INFO] Testrail run number: 1
[INFO] File testCaseIDFromRUN.properties is generated to D:\ProjectBaseDir
```

