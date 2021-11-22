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
Schauen sie das sie Im Ordner Frontend sind wenn sie diese Commands ausführen
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
Wenn sie die Configuration anpassen wollen gehen sie in den Config Ordner im Frontend und öffnene sie dann ihre js config und verändern sie diese.

Frontend: http://localhost:8081/

#M223 Punchclock TestDaten
###TestUser: Severin Senn:
email:severin@senn-online.ch passwort: test Admin:True

###Projekte: 
JBE, JBVS, AUS

###Gruppen:
DEV, Sekretariat

#M223 JPQL
Diese JPQL hollt sich eine liste von Projekt Namen die Admins zugewissen sind und eine länge von 3 haben

URL: http://localhost:8080/Project/GetProjectsNamesLen3List

Query: select p.name from Project p join p.users user join user.projects p where user.is_Admin = true group by p.id HAVING length(p.name) = 3
#M223 Abweichungen

#M223 Git

