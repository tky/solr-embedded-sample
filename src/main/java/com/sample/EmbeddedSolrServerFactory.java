package com.sample;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.core.CoreContainer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

public class EmbeddedSolrServerFactory implements SolrServerFactory , InitializingBean {

    @Value("${solr.home}")
    private String solrHome;
    @Value("${solr.core}")
    private String core;
    
    private EmbeddedSolrServer server;
    
    @Override
    public SolrServer getSlaveServer() {
        return server;
    }

    @Override
    public SolrServer getMasterServer() {
        return server;
    }

    @Override
    protected void finalize() throws Throwable {
        server.shutdown();
    }
    
    // コンストラクタで実行するとsolrHomeやcoreがnullです。
    @Override
    public void afterPropertiesSet() throws Exception {
        System.setProperty("solr.solr.home", solrHome);
        CoreContainer.Initializer initializer = new CoreContainer.Initializer();
        CoreContainer coreContainer = initializer.initialize();
        server = new EmbeddedSolrServer(coreContainer, core);
    }
}
