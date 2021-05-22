#!/bin/sh
set -e

java $JAVA_OPTS -jar /ms-products/app.jar \
	--spring.datasource.url="$DATASOURCE_URL" \
	--spring.datasource.username="$DATASOURCE_USERNAME" \
	--spring.datasource.password="$DATASOURCE_PASSWORD" \
	--spring.profiles.active="$ENVIRONMENT_PROFILE"
