package com.unrc.app;

import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;

public class ElasticSearch {
    public static final Node node = org.elasticsearch.node
                                        .NodeBuilder
                                        .nodeBuilder()
                                        .clusterName("carsapp")
                                        .local(true)
                                        .node();
    public static Client client(){
        return node.client();
    }

}