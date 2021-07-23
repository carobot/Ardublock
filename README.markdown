CarduBlock EDU
======
A lightweight ArduBlock fork with various updates including blocks for the CAROBOT SwissCHEESE board.

Installation
----
The project is managed by Apache Maven. For installation instructions, see https://maven.apache.org/install.html.

ArduBlock and all its forks are dependent on openblocks. Thus, you must run install_openblocks.sh.

After checking out the source for the first time, one should run the following to install Arduino's pde.jar into the local repository:

	$ mvn validate

Development and Usage
----
Change the /src/main/resources/com/ardublock/block/ardublock.xml to add new blocks to CarduBlock.

To package CarduBlock EDU to a .jar file, run:

	$ mvn clean package

For use in the Arduino IDE, copy the target/cardublock_edu-all.jar to Arduino/tools/CardublockTool/tool.

If we run:

	$ mvn compile exec:java -Dexec.mainClass="com.ardublock.Main"

The Visual Block environment should show up. Happy Hacking! ;)


ArduBlock
======

ArduBlock is a Block Programming Language for Arduino. The language and functions model closely to [Arduino Language Reference](http://arduino.cc/en/Reference/HomePage)

Authors
----
* David Li taweili@gmail.com
* HE Qichen heqichen@gmail.com

ToDo
----
* Integrate the [scripting engine](http://java.sun.com/developer/technicalArticles/J2SE/Desktop/scripting/) into the language blocks for code generation

Installation Tips
----
1. Make sure you have Java 9.0.4 and add it to the path as %JAVA_HOME%
2. Pull the Cardublock and openblocks from the Carobot github and store it in documents for convinience purposes
3. Install eclipse IDE and maven.
4. Add Maven to your path and make sure you can access and use it. 
5. cd into the cardublock folder and run these commands
	i. mvn validate
	ii. mvn clean
	iii. mvn eclipse:clean
	iv. mvn eclipse:eclipse
6. after this open the project in eclipse.
7. make sure that the jdk is selected in the project properties for compilation (you may need to google where to find this)
8. Next cd into the open blocks folder
9. Run mvn clean and then mvn install
10. You should now be ready for compilation

License
----

Copyright (C) 2011 David Li and He Qichen

This file is part of ArduBlock.

ArduBlock is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

ArduBlock is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with ArduBlock.  If not, see <http://www.gnu.org/licenses/>.
