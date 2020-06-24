package com.application.asseco.web.rest.util;

import com.application.asseco.config.security.SecurityConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.application.asseco.config.security.SecurityConstants.AUTHORIZATION_HEADER;
import static com.application.asseco.config.security.SecurityConstants.TOKEN_PREFIX;

public final class HeaderUtil {

    private static Logger LOG = LoggerFactory.getLogger(HeaderUtil.class);

    private static final String APPLICATION_NAME = "Asseco";
    private static final String X_TOTAL_COUNT = "X-Total-Count";

    private HeaderUtil() {
    }

    public static HttpHeaders createAlert(final String message, final String param) {
        final HttpHeaders headers = new HttpHeaders();
        headers.add("X-4trApp-alert", message);
        headers.add("X-4trApp-params", param);
        return headers;
    }

    public static HttpHeaders createEntityCreationAlert(final String entityName, final String param) {
        return createAlert(APPLICATION_NAME + "." + entityName + ".created", param);
    }

    public static HttpHeaders createEntityUpdateAlert(final String entityName, final String param) {
        return createAlert(APPLICATION_NAME + "." + entityName + ".updated", param);
    }

    public static HttpHeaders createEntityDeletionAlert(final String entityName, final String param) {
        return createAlert(APPLICATION_NAME + "." + entityName + ".deleted", param);
    }

    public static HttpHeaders createFailureAlert(
            final String entityName, final String errorKey, final String defaultMessage) {
        LOG.error("Entity processing failed, {}", defaultMessage);
        final HttpHeaders headers = new HttpHeaders();
        headers.add("X-4trApp-error", "error." + errorKey);
        headers.add("X-4trApp-params", entityName);
        return headers;
    }


    public static Long getXTotalCount(HttpHeaders headers) {
        if (headers != null && headers.get(X_TOTAL_COUNT) != null) {
            final List<String> totalCountValues = headers.get(X_TOTAL_COUNT);
            if (!CollectionUtils.isEmpty(totalCountValues) && totalCountValues.get(0) != null) {
                try {
                    return Long.valueOf(totalCountValues.get(0));
                } catch (NumberFormatException e) {
                    LOG.debug("Could not parse header X-Total-Count[{}]", totalCountValues.get(0));
                }
            }
        }
        return null;
    }

    public static HttpHeaders createHeaders(String name, List<String> values) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put(name, values);
        return httpHeaders;
    }

    public static void setJWTHeader(HttpServletResponse response, String jwt) {
        response.addHeader(AUTHORIZATION_HEADER, TOKEN_PREFIX + jwt);
    }

    public static String getJWTHeader(HttpServletRequest request) {
        final String token = request.getHeader(AUTHORIZATION_HEADER);
        return token != null ? token.replace(TOKEN_PREFIX, "") : null;
    }
}
