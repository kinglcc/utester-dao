/**
* JetBrains Space Automation
* This Kotlin-script file lets you automate build activities
* For more info, see https://www.jetbrains.com/help/space/automation.html
*/

job("Build and run tests") {
    container(displayName = "Run mvn install", image = "maven:latest") {
        shellScript {
            content = """
	            mvn clean install
            """
        }
    }

    container(displayName = "Prepare test data", image = "gradle:6.1.1-jre11") {
        shellScript {
            content = """
                echo "Hello world!"
            """
        }
    }
}
