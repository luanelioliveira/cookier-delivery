fmt:
	mvn fmt:format -f pom.xml

deploy:
	cdk deploy --parameters DB-MS-Products:databasePassword=postgres --parameters DB-MS-Products:databaseUsername=postgres DB-MS-Products MS-Products Vpc Cluster

destroy:
	cdk destroy DB-MS-Products MS-Products Vpc Cluster
