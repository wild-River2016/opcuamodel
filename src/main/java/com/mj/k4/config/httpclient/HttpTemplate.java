package com.mj.k4.config.httpclient;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.*;

import java.net.URI;
import java.util.Collections;

/**
 *
 */
public class HttpTemplate extends RestTemplate {
    public static final int MAX_ATTEMPTS = 3;
    public static final long BACK_OFF_PERIOD = 500;

    private String apiHost;
    private int apiPort;
    private String apiHttpSchemeHierarchical;

    // retry template
    private final RetryTemplate retryTemplate = new RetryTemplate();

    public static int getMaxAttempts() {
        return MAX_ATTEMPTS;
    }

    public static long getBackOffPeriod() {
        return BACK_OFF_PERIOD;
    }

    public String getApiHost() {
        return apiHost;
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    public int getApiPort() {
        return apiPort;
    }

    public void setApiPort(int apiPort) {
        this.apiPort = apiPort;
    }

    public String getApiHttpSchemeHierarchical() {
        return apiHttpSchemeHierarchical;
    }

    public void setApiHttpSchemeHierarchical(String apiHttpSchemeHierarchical) {
        this.apiHttpSchemeHierarchical = apiHttpSchemeHierarchical;
    }

    public RetryTemplate getRetryTemplate() {
        return retryTemplate;
    }

    public HttpTemplate(ClientHttpRequestFactory clientHttpRequestFactory,
                        String apiHost,
                        int apiPort) {
        super(clientHttpRequestFactory);
        this.apiHost = apiHost;
        this.apiPort = apiPort;
        this.apiHttpSchemeHierarchical = "http://" + this.apiHost + ":" + this.apiPort;

        // retry policy
        SimpleRetryPolicy policy = new SimpleRetryPolicy(MAX_ATTEMPTS,
            Collections.<Class<? extends Throwable>, Boolean>singletonMap(ResourceAccessException.class, true));
        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(BACK_OFF_PERIOD);
        retryTemplate.setRetryPolicy(policy);
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);
    }

    @Override
    protected <T> T doExecute(URI url, HttpMethod method, RequestCallback requestCallback,
                              ResponseExtractor<T> responseExtractor) throws RestClientException {
        return retryTemplate.execute(
            context -> HttpTemplate.super.doExecute(url, method, requestCallback, responseExtractor));
    }
}
