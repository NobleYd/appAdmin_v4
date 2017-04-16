
set rootDir=.
set libDir=%rootDir%/libs/*
set classesDir=%rootDir%/build/classes
set mainClass=com.app.buzz.WebServer.start.AppAdminWebRun

set CLASSPATH=%classesDir%;%libDir%;%CLASS_PATH%

java %mainClass%

@pause
