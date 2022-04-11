package com.unobank.servicehub.platform.commonlib.client;

import org.springframework.http.HttpEntity;

import java.net.URI;

/**
 * @author shreyas kekre
 */
public interface ApiClient {

    <R> R getOperation(URI uri, Class<R> rclass, String baseUrl);

    <R> R postOperation(URI postUri, Object requestObject, Class<R> rClasss, String baseUrl);

    <R> R postOperationWithoutIdempotencyKey(URI postUri, Object requestObject, Class<R> rClasss, String baseUrl);

    <R> R getOperationById(URI uri, String id, HttpEntity<?> entity, Class<R> rclass, String baseUrl);

    <R> R postOperationById(URI postUri, Object requestObject, String id, Class<R> rClasss, String baseUrl);

    void deleteOperation(URI deleteUri, String id, HttpEntity<?> entity, String baseUrl);

    <R> R patchOperation(URI uri, String id, HttpEntity<?> entity, Class<R> rClasss, String baseUrl);
}
