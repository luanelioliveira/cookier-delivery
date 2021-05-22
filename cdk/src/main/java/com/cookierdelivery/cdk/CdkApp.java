package com.cookierdelivery.cdk;

import com.cookierdelivery.cdk.clusters.ClusterStack;
import com.cookierdelivery.cdk.services.MsProductsServiceStack;
import com.cookierdelivery.cdk.vpc.VpcStack;
import software.amazon.awscdk.core.App;

public class CdkApp {
  public static void main(final String[] args) {
    App app = new App();

    // Create VPC
    VpcStack vpcStack = new VpcStack(app, "Vpc");

    // Create Cluster
    ClusterStack clusterStack = new ClusterStack(app, "Cluster", vpcStack.getVpc());
    clusterStack.addDependency(vpcStack);

    // Microservice ms-producsts
    MsProductsServiceStack msProductsServiceStack =
        new MsProductsServiceStack(app, "MS-Products", clusterStack.getCluster());
    clusterStack.addDependency(clusterStack);

    app.synth();
  }
}
