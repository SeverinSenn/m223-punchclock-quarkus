# M223 Punchclock Backend

Folgende Schritte sind notwendig um die Applikation zu erstellen und zu starten: 
1. Stellen Sie sicher, dass OpenJDK 11 oder höher installiert und JAVA_HOME korrekt gesetzt ist.  
2. Installieren Sie (falls noch nicht vorhanden) Apache Maven 3.8.1 oder höher
3. Wechseln Sie auf der Kommandozeile in den Ordner dieser Applikation. 
`cd m223-punchclock-quarkus/`
4. Starten Sie die Applikation mit 
```shell script
./mvnw compile quarkus:dev
```

Folgende Dienste stehen während der Ausführung im Profil dev zur Verfügung:

Swagger API: http://localhost:8080/q/swagger-ui/

H2 Console: http://localhost:8080/h2/ 
Datenquelle: jdbc:h2:mem:punchclock
Benutzername: zli
Passwort: zli

#M223 Punchclock Frontend
## Install the dependencies
```bash
npm install
```

### Start the app in development mode
```bash
npm run dev-YourCinfiguration
```
### My prebuild Config for Backend on port 8080
```bash
npm run dev-sse
```
Wenn sie die Configuration anpassen wollen gehen sie in den Config ordner im Forntend und öffnene sie dann ihre js config und verändern sie diese .

Frontend: http://localhost:8081/

#M223 Punchclock TestDaten
###TestUser: Severin Senn:
email:severin@senn-online.ch passwort: test

###Projekte: 
JBE, JBVS, AUS

###Gruppen:
DEV, Sekretariat

