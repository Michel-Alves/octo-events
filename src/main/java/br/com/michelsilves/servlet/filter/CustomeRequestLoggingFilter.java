package br.com.michelsilves.servlet.filter;

import org.springframework.web.filter.CommonsRequestLoggingFilter;

public class CustomeRequestLoggingFilter
        extends CommonsRequestLoggingFilter {

    public CustomeRequestLoggingFilter() {
        super.setIncludeQueryString(true);
        super.setIncludePayload(true);
        super.setIncludeHeaders(true);
        super.setMaxPayloadLength(10000);
    }
}