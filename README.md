testrail-integration-maven-plugin
============================

Create a property file with name 'testCaseIDFromRUN.properties' in project build directory, which contains tests tags for run

Quickstart
----------

Add the following to your POM file, updating the `<glue>` definition to point to your glue code packages:
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
        <version>0.0.1</version>
        <configuration>
            <!-- URL of your TestRail server -->
            <testRailURL>https://example.com/</testRailURL>
            <!-- User email in TestRail  -->
            <userEmail>test@examole.com</userEmail>
            <!-- User password in TestRail -->
            <password>12345678</password>
            <!-- Number of run in TestRail, with the default values = 0 -->
            <!--<numberOfTestRailRun></numberOfTestRailRun>-->
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
