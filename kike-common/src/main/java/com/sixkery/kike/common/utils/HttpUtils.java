package com.sixkery.kike.common.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.UncheckedIOException;

/**
 * @author liugang
 * @date 2020/7/9
 */
public final class HttpUtils {

    /**
     * 发送 GET 请求
     *
     * @param request 请求数据
     * @return response
     */
    private static HttpEntity doGet(HttpGet request) {
        HttpResponse response;
        try {
            response = HttpClients.createDefault().execute(request);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return response.getEntity();

    }

    /**
     * 发送 POST 请求
     *
     * @param request     请求体
     * @param requestData 请求的数据
     * @return 返回 response
     */
    private static HttpEntity doPost(HttpPost request, Object requestData) {
        HttpResponse response;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String s = objectMapper.writer().writeValueAsString(requestData);
            StringEntity strEntity = new StringEntity(s, ContentType.APPLICATION_JSON);
            request.setEntity(strEntity);
            response = HttpClients.createDefault().execute(request);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return response.getEntity();
    }
}
