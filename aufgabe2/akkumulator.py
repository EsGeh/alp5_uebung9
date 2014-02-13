import cherrypy
import random

cherrypy.config.update(
    {'server.socket_host': '0.0.0.0'} )

acchash = {}

class Root:
    import cherrypy

class Root:
    def setCookie(self):
         rescookie = cherrypy.response.cookie
         if not 'cookieName' in cherrypy.request.cookie:
            print("is in")
            rescookie['cookieName'] = random.getrandbits(64)
            rescookie['cookieName']['path'] = '/'
            rescookie['cookieName']['max-age'] = 3600000
            rescookie['cookieName']['version'] = 1
            return "<html><body>Hello, I just sent you a cookie</body></html>"
    setCookie.exposed = True

    def readCookie(self):
         reqcookie = cherrypy.response.cookie
         wert = 0
         key = reqcookie['cookieName'].value
         key = int(key)
         if key in acchash:
             acchash[key] += wert
         else:
             acchash[key] = wert
         res = """<html><body>Hallo Benutzer! Wir wünschen dir alles Gute!
                """
         return res + """<form action="acc" method="post"> 
    <p>Wert</p>
    <input type="text" name="wert" value=""
        size="15" maxlength="40"  pattern="\d+" required />
    <p><input type="submit" value="Erhöhe den Wert!"/></p>
</form>Der WERT beträgt:"""+str(acchash[key])+"""</body></html>"""
    readCookie.exposed = True


    def acc(self, wert=None):
        wert = int(wert)
        reqcookie = cherrypy.request.cookie
        key = reqcookie['cookieName'].value
        key = int(key)
        if key in acchash:
            acchash[key] += wert
        else:
            acchash[key] = wert
        res = """<html><body>Hi."""
        return res + """<form action="acc" method="post"> 
    <p>Wert</p><input type="text" name="wert" value=""
        size="15" maxlength="40" pattern="\d+" required/>
    <p><input type="submit" value="Erhöhe den Wert!"/></p>
</form>Der WERT beträgt:"""+str(acchash[key])+"""</body></html>"""
    readCookie.exposed = True
    acc.exposed = True

    def index(self):
        self.setCookie()
        return(self.readCookie())
    index.exposed = True

cherrypy.quickstart(Root())
