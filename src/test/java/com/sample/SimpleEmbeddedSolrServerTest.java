package com.sample;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.core.CoreContainer;
import org.junit.Test;

import com.sample.domain.Core0;

public class SimpleEmbeddedSolrServerTest {

    // とにかく起動したいというせっかちな人のために
    @Test
    public void test_index_and_find() throws Exception {
        System.setProperty("solr.solr.home", "solr");
        CoreContainer.Initializer initializer = new CoreContainer.Initializer();
        CoreContainer coreContainer = initializer.initialize();
        EmbeddedSolrServer server = new EmbeddedSolrServer(coreContainer, "core0");
        server.addBean(new Core0("1", "name1"));
        server.commit();
        
        SolrQuery solrQuery = new  SolrQuery("*:*");
        QueryResponse rsp = server.query(solrQuery);
        List<Core0> results = rsp.getBeans(Core0.class);
        
        assertThat(results.size(), is(1));
        assertThat(results.get(0).getId(), is("1"));
        assertThat(results.get(0).getName(), is("name1"));
    }
}
