# Hepsiburada General and Shipping Option tests

Lely automated test cases using Selenium Java and Gauge

1 - Search a keyword and check all results contains the keyword
-----------
* Go to Lely homepage
* Search product using keyword "happy"
* Check all results contains searched keyword

2 - Select and download machine catalog on TechDocs Page
-----------
* Go to Lely TechDocs
* Select "Luna EUR" from machine list
* Check product catalog is displayed
* View document on the list
* Check valid document is opened in new tab
* Return main tab
* Download product catalog
* Check catalog is downloaded

## Required  

Project requires Gauge and Maven installed and must be set in PATH

1 - Download Gauge on Windows for Java https://docs.gauge.org/getting_started/installing-gauge.html and select the language say Java

Add Gauge installation path to Enviroment PATH : 

![Uygulama Ekran Görüntüsü](https://github.com/LambdaTest/gauge-selenium-sample/raw/master/Tutorial-Images/Environment%20Variable.png?raw=true)

2 -  You can download the maven latest version using the link: https://maven.apache.org/download.cgi . After the installation is over, you can then configure maven by setting MAVEN_HOME in the environment variables

![Uygulama Ekran Görüntüsü](https://github.com/LambdaTest/gauge-selenium-sample/raw/master/Tutorial-Images/Maven%20Installation.png?raw=true)

## Get tests to your local

Clone project

```bash
  git clone https://github.com/cuzun-net/LelyWeb-Selenium.git
```

Navite project folder

```bash
  cd LelyGeneralTests
```

Run initial

```bash
  mvn clean install
```

  
## How to Run Test

- Run all tests in /specs folder

```javascript
mvn gauge:execute -DspecsDir=specs
```

- Run single test 

```javascript
mvn gauge:execute -DspecsDir=specs/LelyGeneralTests.spec
```

- Run tests in parallel sessions

```javascript
mvn gauge:execute -DspecsDir=specs -DinParallel=true
```

## Vieving Test Runner Reports

Testrunner reports are generated under /report/html-reports/index.html

![Uygulama Ekran Görüntüsü](https://www.linkpicture.com/q/lelySS.png)
