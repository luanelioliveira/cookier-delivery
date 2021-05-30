# format project
fmt:
	mvn fmt:format -f pom.xml

# execute tests
build:
	mvn clean install -Dmaven.test.skip=true

# execute tests
test:
	mvn clean test

# execute build without tests and run the application with local-container profile
run:
	mvn spring-boot:run -Dspring-boot.run.profiles=development

localstack:
	docker run --rm -p 4566:4566 -p 4571:4571 localstack/localstack -e "SERVICES=sns,sqs,dynamodb,s3"

# run database application with local-container
compose-up:
	docker-compose -f docker-compose.development.yml up

# run database application with local-container
compose-down:
	docker-compose -f docker-compose.development.yml down

# execute build without tests and run the application with local-container profile in debug mode.
debug:
	mvn spring-boot:run -Dspring-boot.run.profiles=development -Dagentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000

# Build application
docker-build:
	mvn docker:build -f pom.xml

# Push application to docker hub
docker-push:
	mvn docker:push
