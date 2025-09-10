# SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
# SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
# SPDX-License-Identifier: Apache-2.0

Running the JSR-286 TCK against Plutonium 2.0+ Portal Driver

1) Setup the Portlet TCK and assemble Plutonium TCK test wars (only needs to be done once)

  a) Download the Portlet TCK
  
     Go to http://jcp.org/aboutJava/communityprocess/final/jsr286/index.html
     The TCK download link is provided there, which (currently) is at: 
     
       http://hnsp.inf-bb.uni-jena.de/tck/

     Note: this is a license agreement click-through link
     
     Save the downloaded portlet-tck070508.zip at a location of your convenience.
     
  b) Create a directory for the TCK and extract the downloaded portlet-tck070508.zip *inside* that directory.

  c) Add an environment variable pointing to your TCK installation directory called TS_HOME
     For example on Linux, add the following to your ~/.bashrc:
     
     export TS_HOME=~/portlet-tck070508
     
     Note: the TCK itself requires this TS_HOME environment variable.

  d) If on Linux, make the extracted $TS_HOME/bin/tsant shell script executable:

     chmod +x $TS_HOME/bin/tsant
     
  e) Configure $TS_HOME/bin/build.properties:

     Set your local timezone in $TS_HOME/bin/build.properties (default is US/Eastern).
     For example, if you are in Amsterdam, use:
   
       tz=Europe/Amsterdam

     Replace:
     
       j2ee.home.ri=${env.J2EE_HOME}
       j2ee.classes.ri=${j2ee.home.ri}/lib/j2ee.jar

     with:
     
       j2ee.home.ri=${TS_HOME}
       j2ee.classes.ri=${j2ee.home.ri}/lib/j2ee_1_3.jar
       
  f) Copy the plutonium-assemble-tck286-wars-pom.xml to $TS_HOME
  
  g) from $TS_HOME, run mvn -f plutonium-assemble-tck286-wars-pom.xml
  
     This will run the maven-plutonium-plugin against the tck test wars and write them out to $TS_HOME/plutonium-assembled-wars

2) Build and setup a clean Plutonium/Tomcat installation for testing

3) Running the TCK

  a) copy all wars from $TS_HOME/plutonium-assembled-wars to your Plutonium/Tomcat webapps folder

  b) Startup and stop Tomcat once to ensure the TCK portlet applications are predeployed

  c) Start Tomcat again
  
  d) Start the TCK gui:
  
     $TS_HOME/bin/tsant gui
     
  c) In the gui, create a new TCK work directory somewhere
  
       Menu: File|New Work Directory
       
     All test output will be stored there, you can later on reload it again to review the outcome.
  
  d) Finally, in the gui run the TCK
  
      Menu: Run Tests|Start
  
  As a shortcut for step c+d above right after starting the TCK gui you can just do step d.
  The gui will first ask where to create or reload a work directory and start executing right after.

