package com.cookierdelivery.cdk.clusters;

import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
import software.amazon.awscdk.services.ec2.Vpc;
import software.amazon.awscdk.services.ecs.Cluster;

public class ClusterStack extends Stack {

  public static final String CLUSTER_NAME = "cluster-01";

  private Cluster cluster;

  public ClusterStack(final Construct scope, final String id, Vpc vpc) {
    this(scope, id, null, vpc);
  }

  public ClusterStack(final Construct scope, final String id, final StackProps props, Vpc vpc) {
    super(scope, id, props);

    this.cluster = Cluster.Builder.create(this, id).clusterName(CLUSTER_NAME).vpc(vpc).build();
  }

  public Cluster getCluster() {
    return this.cluster;
  }
}
