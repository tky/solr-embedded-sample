package com.sample;

import org.apache.solr.client.solrj.SolrServer;

public interface SolrServerFactory {
    SolrServer getSlaveServer();
    SolrServer getMasterServer();
}
