package com.cookierdelivery.cdk.database;

import java.util.Collections;
import software.amazon.awscdk.core.CfnOutput;
import software.amazon.awscdk.core.CfnParameter;
import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.SecretValue;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
import software.amazon.awscdk.services.ec2.ISecurityGroup;
import software.amazon.awscdk.services.ec2.InstanceClass;
import software.amazon.awscdk.services.ec2.InstanceSize;
import software.amazon.awscdk.services.ec2.InstanceType;
import software.amazon.awscdk.services.ec2.Peer;
import software.amazon.awscdk.services.ec2.Port;
import software.amazon.awscdk.services.ec2.SecurityGroup;
import software.amazon.awscdk.services.ec2.SubnetSelection;
import software.amazon.awscdk.services.ec2.Vpc;
import software.amazon.awscdk.services.rds.Credentials;
import software.amazon.awscdk.services.rds.DatabaseInstance;
import software.amazon.awscdk.services.rds.DatabaseInstanceEngine;
import software.amazon.awscdk.services.rds.IInstanceEngine;
import software.amazon.awscdk.services.rds.PostgresEngineVersion;
import software.amazon.awscdk.services.rds.PostgresInstanceEngineProps;

public class MsProductsDatabaseStack extends Stack {

  private static final InstanceType INSTANCE_TYPE =
      InstanceType.of(InstanceClass.BURSTABLE2, InstanceSize.MICRO);

  private static final int DATABASE_PORT = 5432;
  private static final IInstanceEngine DATABASE_ENGINE =
      DatabaseInstanceEngine.postgres(
          PostgresInstanceEngineProps.builder().version(PostgresEngineVersion.VER_12).build());

  public MsProductsDatabaseStack(final Construct scope, final String id, Vpc vpc) {
    this(scope, id, null, vpc);
  }

  public MsProductsDatabaseStack(
      final Construct scope, final String id, final StackProps props, Vpc vpc) {
    super(scope, id, props);

    CfnParameter databasePassword =
        CfnParameter.Builder.create(this, "databasePassword")
            .type("String")
            .description("The RDS instance password")
            .build();

    CfnParameter databaseUsername =
        CfnParameter.Builder.create(this, "databaseUsername")
            .type("String")
            .description("The RDS instance username")
            .build();

    ISecurityGroup securityGroup =
        SecurityGroup.fromSecurityGroupId(this, id, vpc.getVpcDefaultSecurityGroup());

    securityGroup.addIngressRule(Peer.anyIpv4(), Port.tcp(DATABASE_PORT));

    DatabaseInstance database =
        DatabaseInstance.Builder.create(this, "RDS-Products")
            .instanceIdentifier("ms-products-db")
            .engine(DATABASE_ENGINE)
            .databaseName(databaseUsername.getValueAsString())
            .vpc(vpc)
            .credentials(
                Credentials.fromPassword(
                    databaseUsername.getValueAsString(),
                    SecretValue.plainText(databasePassword.getValueAsString())))
            .instanceType(INSTANCE_TYPE)
            .multiAz(false)
            .allocatedStorage(10)
            .securityGroups(Collections.singletonList(securityGroup))
            .vpcSubnets(SubnetSelection.builder().subnets(vpc.getPrivateSubnets()).build())
            .build();

    CfnOutput.Builder.create(this, "ms-products-db-url")
        .exportName("ms-products-db-url")
        .value(database.getDbInstanceEndpointAddress())
        .build();

    CfnOutput.Builder.create(this, "ms-products-db-username")
        .exportName("ms-products-db-username")
        .value(databaseUsername.getValueAsString())
        .build();

    CfnOutput.Builder.create(this, "ms-products-db-password")
        .exportName("ms-products-db-password")
        .value(databasePassword.getValueAsString())
        .build();
  }
}
