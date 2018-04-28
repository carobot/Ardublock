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
