package com.cookierdelivery.cdk;

import com.cookierdelivery.cdk.clusters.ClusterStack;
import com.cookierdelivery.cdk.database.MsProductsDatabaseStack;
import com.cookierdelivery.cdk.services.MsProductsServiceStack;
import com.cookierdelivery.cdk.vpc.VpcStack;
import software.amazon.awscdk.core.App;

public class CdkApp {
  public static void main(final String[] args) {
    App app = new App();

    // VPC
    VpcStack vpcStack = new VpcStack(app, "Vpc");

    // Cluster
    ClusterStack cluster = new ClusterStack(app, "Cluster", vpcStack.getVpc());
    cluster.addDependency(vpcStack);

    // Database ms-products
    MsProductsDatabaseStack msProductsDatabase =
        new MsProductsDatabaseStack(app, "DB-MS-Products", vpcStack.getVpc());
    msProductsDatabase.addDependency(vpcStack);

    // Microservice ms-products
    MsProductsServiceStack msProductsService =
        new MsProductsServiceStack(app, "MS-Products", cluster.getCluster());
    msProductsService.addDependency(cluster);
    msProductsService.addDependency(msProductsDatabase);

    app.synth();
  }
}
