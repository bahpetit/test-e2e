# test-e2e
## Comment ça marche
### cloner le projet à traver la cmd ci-dessous:

git clone https://github.com/bahpetit/test-e2e.git

#### Ouvrir le projet avec un EDI (intelliJ par exemple)

#### Installer chromeDriver sous : 
  /usr/bin/chromedriver

### Les keywords sont ecrits en java dans le package: 
src/test/java/robotframework/GettingStarted.java [voir](src/test/java/robotframework/GettingStarted.java)

### Le fichier robot 
src/test/robotframework/testSuite.robot [voir](src/test/robotframework/testSuite.robot)

### Pour executer cliquez sur maven verify ou la cmd: mvn clean verify

### Resultat Antendu 
Voir le fichier : report target/robotframework-reports/report.html [voir](target/robotframework-reports/report.html) 
et log target/robotframework-reports/log.html [voir](target/robotframework-reports/log.html)

# NB: Si un keywork est faild c' est tous le cas de test qui failed
