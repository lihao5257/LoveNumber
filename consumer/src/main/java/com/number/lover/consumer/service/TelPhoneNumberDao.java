package com.number.lover.consumer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.number.lover.rabbitmq.consumer.model.TelPhoneNumberToEs;

@Repository
public class TelPhoneNumberDao {

    private final String TYPE = "_doc";

    private RestHighLevelClient restHighLevelClient;

    private ObjectMapper objectMapper;

    public TelPhoneNumberDao(ObjectMapper objectMapper, RestHighLevelClient restHighLevelClient) {
        this.objectMapper = objectMapper;
        this.restHighLevelClient = restHighLevelClient;
    }

    public TelPhoneNumberToEs insertPhoneNumber(TelPhoneNumberToEs telPhoneNumberToEs, String index) {
        telPhoneNumberToEs.setId(UUID.randomUUID().toString());
        @SuppressWarnings("unchecked")
        Map<String, Object> dataMap = objectMapper.convertValue(telPhoneNumberToEs, Map.class);
        IndexRequest indexRequest = new IndexRequest(index, TYPE, telPhoneNumberToEs.getId()).source(dataMap);
        try {
            restHighLevelClient.index(indexRequest);
        } catch (ElasticsearchException e) {
            e.getDetailedMessage();
        } catch (java.io.IOException ex) {
            ex.getLocalizedMessage();
        }
        return telPhoneNumberToEs;
    }

    /*
     * It may throw an IOException in case of either failing to parse the REST
     * response, the request times out or similar cases where there is no response
     * coming back from the server.
     */
    public List<Map<String, Object>> getAllNumbers(String index) {

        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.types(TYPE);
        SearchResponse getResponse = null;
        try {
            getResponse = restHighLevelClient.search(searchRequest);
        } catch (java.io.IOException e) {
            e.getLocalizedMessage();
        }
        SearchHit[] searchHits = getResponse.getHits().getHits();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (SearchHit sh : searchHits) {
            list.add(sh.getSourceAsMap());
        }
        return list;

    }

}
