package com.unobank.servicehub.platform.commonlib.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * @author shreyas kekre
 */
public class RestTemplateApiClient implements ApiClient  {



    private final RestTemplate sslDisableRestTemplate;

    public RestTemplateApiClient(RestTemplate sslDisableRestTemplate) {
        this.sslDisableRestTemplate = sslDisableRestTemplate;
    }

    @Override
    public <R> R getOperation(URI uri, Class<R> rclass, final String baseUrl) {
        URI builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path(uri.getPath())
                .query(uri.getQuery())
                .build()
                .toUri();
        return sslDisableRestTemplate.getForObject(builder, rclass);
    }

    public <R> R postOperation(URI uri, Object requestObject, Class<R> rClasss,final String baseUrl) {
        URI builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path(uri.getPath())
                .query(uri.getQuery())
                .build()
                .toUri();

        return sslDisableRestTemplate.postForObject(builder,requestObject, rClasss);
    }

    @Override
    public <R> R getOperationById(URI uri, String id, HttpEntity<?> entity, Class<R> rclass,final String baseUrl) {
        URI builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path(uri.getPath())
                .query(uri.getQuery())
                .build()
                .toUri();
        String builderResult = builder + id;
        ResponseEntity<R> respose = sslDisableRestTemplate.exchange(builderResult, HttpMethod.GET, entity, rclass);
        return  respose.getBody();
    }

    @Override
    public <R> R postOperationById(URI uri, Object requestObject, String id, Class<R> rClasss,final String baseUrl) {
        URI builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path(uri.getPath())
                .query(uri.getQuery())
                .build()
                .toUri();
        String builderResult = builder + id;

        return sslDisableRestTemplate.postForObject(builderResult, requestObject, rClasss);

    }

    @Override
    public void deleteOperation(URI deleteUri, String id, HttpEntity<?> entity, final String baseUrl) {
        URI builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path(deleteUri.getPath())
                .query(deleteUri.getQuery())
                .build()
                .toUri();
        String builderResult = builder + id;
        sslDisableRestTemplate.exchange(builderResult, HttpMethod.DELETE, entity, String.class);
    }
}
