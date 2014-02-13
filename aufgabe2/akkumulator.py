import cherrypy
import random

cherrypy.config.update(
    {'server.socket_host': '0.0.0.0'} )

acchash = {}

class Root:
    import cherrypy

class Root:
	def getID():
		if 'cookieName' in cherrypy.request.cookie:
			return self.readCookie()
		else
			return self.sendCookie()
	
	def readCookie():
		reqcookie = cherrypy.request.cookie
		wert = 0
		key = reqcookie['cookieName'].value
		key = int(key)
		return key

	def sendCookie():
		rescookie = cherrypy.response.cookie
		id = random.getrandbits(64)
		rescookie['cookieName'] = id
		rescookie['cookieName']['path'] = '/'
		rescookie['cookieName']['max-age'] = 3600000
		rescookie['cookieName']['version'] = 1
		acchash[id] = 0
		return id

    def acc(self, wert=None):
        wert = int(wert)
        reqcookie = cherrypy.request.cookie
        key = reqcookie['cookieName'].value
        key = int(key)
        if key in acchash:
            acchash[key] += wert
        else:
            acchash[key] = wert
	return calcWebsite(key)
    acc.exposed = True

	def calcWebsite(id):
		res = """<html><body>Hi."""
		return res + """<form action="acc" method="post"> 
	    <p>Wert</p><input type="text" name="wert" value=""
		size="15" maxlength="40" pattern="\d+" required/>
	    <p><input type="submit" value="Erhöhe den Wert!"/></p>
	</form>Der WERT beträgt:"""+str(acchash[key])+"""</body></html>"""



    def index(self):
	id = self.getID()
	return self.calcWebsite(id)
        #return(self.readCookie())
    index.exposed = True

cherrypy.quickstart(Root())
