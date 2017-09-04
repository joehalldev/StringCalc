StringCalc is a program that calculates an expression string to the resulting number.
Requires Java 8
Build script is Apache Maven.
  * After Maven is installed CD to the project directroy and run "mvn install" from the command line to build with dependencies.
  * Build: mvn clean install
  * Just the test cases: mvn test
To run CD to the target directory contains the build.
  * java -jar StringCalc-1.jar "3 * 2"
  * 
  * Supports any math expression including +, -, *, /, ^
  * Also support parens.  Example: "3 * (2 + 5)"
Author: Joe Hall
