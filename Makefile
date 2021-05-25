test:
	mvn test

build:
	mvn clean package

run:
	mvn spring-boot:run -Pprofiles=dev

fmt:
	mvn fmt:format -f pom.xml
