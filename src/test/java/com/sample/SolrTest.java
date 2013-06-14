package com.sample;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sample.domain.Core0;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext-test.xml")
public class SolrTest {

    @Autowired
    private SolrServerFactory solrServerFactory;
    
    @Test
    public void test_index_and_search() throws Exception {
        SolrServer server = solrServerFactory.getMasterServer();
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
