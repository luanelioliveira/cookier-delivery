# format project
fmt:
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

# run database application with local-container
run-database:
	docker-compose -f docker-compose.development.yml up

# execute build without tests and run the application with local-container profile in debug mode.
debug:
	mvn spring-boot:run -Dspring-boot.run.profiles=development -Dagentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000

# Build application
docker-build:
	mvn docker:build -f pom.xml

# Push application to docker hub
docker-push:
	mvn docker:push
