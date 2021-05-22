FROM openjdk:11-jdk

COPY target/ms-products*.jar /ms-products/app.jar
COPY scripts/entrypoint.sh /ms-products

ENTRYPOINT /ms-products/entrypoint.sh
