#!/usr/bin/env python

import urllib
import sgmllib

#Step 1 - Connect To Site
homeURL = urllib.urlopen("http://beecloudproject.appspot.com/")
print result

#Step 2 - Recieve Data.
sampleData = "id=1,lat=37.4,long=94.4,weight=300,intTemp=70.5,extTemp=70.5"

#Step 3 - Look For ID
if sampleData.find("id") == -1:
	print 'Cannot Find ID
else:
	print 'ID found'

#Step 4 - Parse Data

#Step 5 - Create Link
#Link must look like this:  http://beecloudproject.appspot.com/uploadHive?hiveID=1&temperature_interior=72&temperature_exterior=50&weight=200&location_lat=42.57&location_long=72.53


#Step 6 - Connect To Link

#Step ? - Shut Down & Close Connections
homeURL.close()


#When we add a REAL Step2 we will have to:
#	(1)  Trash Data if no ID at Step3
#	(2)  Create a "Listening" method between steps 2-3

#When all this is completed we have to make this work for multiple incoming transmissions (threads)
