package com.sample;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;


public class HttpSolrServerFactory implements SolrServerFactory, InitializingBean {

    @Value("${solr.master}")
    private String master;
    @Value("${solr.slave}")
    private String slave;
    @Value("${solr.core}")
    private String core;
    
    private HttpSolrServer slaveServer;
    private HttpSolrServer masterServer;
    
    public HttpSolrServerFactory() {
    }
    
    @Override
    public SolrServer getSlaveServer() {
        return this.slaveServer;
    }

    @Override
    public SolrServer getMasterServer() {
        return this.masterServer;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        slaveServer = new HttpSolrServer(slave + "/" + core);
        masterServer = new HttpSolrServer(master  + "/" + core);
    }

}
