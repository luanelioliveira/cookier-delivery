# format project
format:
	mvn fmt:format -f pom.xml

# execute tests
build:
	mvn clean install

# execute tests
test:
	mvn clean test

# execute build without tests and run the application with local-container profile
run:
	mvn spring-boot:run -Dspring-boot.run.profiles=development

# execute build without tests and run the application with local-container profile in debug mode.
debug:
	mvn spring-boot:run -Dspring-boot.run.profiles=development -Dagentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000

# Build application
docker-build:
	mvn docker:build -f pom.xml

# Push application to docker hub
docker-push:
	docker push luanelioliveira/cookierdelivery-ms-products
