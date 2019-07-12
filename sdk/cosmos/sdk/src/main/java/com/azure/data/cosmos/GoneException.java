/*
 * The MIT License (MIT)
 * Copyright (c) 2018 Microsoft Corporation
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.azure.data.cosmos;

import com.azure.data.cosmos.internal.HttpConstants;
import com.azure.data.cosmos.internal.RMResources;
import com.azure.data.cosmos.internal.Strings;
import com.azure.data.cosmos.internal.directconnectivity.HttpUtils;
import com.azure.data.cosmos.internal.http.HttpHeaders;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class GoneException extends CosmosClientException {

    public GoneException(String msg) {
        this(msg, null);
    }
    public GoneException() {
        this(RMResources.Gone, null);
    }

    public GoneException(CosmosError cosmosError, long lsn, String partitionKeyRangeId, Map<String, String> responseHeaders) {
        super(HttpConstants.StatusCodes.GONE, cosmosError, responseHeaders);
        BridgeInternal.setLSN(this, lsn);
        BridgeInternal.setPartitionKeyRangeId(this, partitionKeyRangeId);
    }

    public GoneException(String message, String requestUri) {
        this(message, null, new HashMap<>(), requestUri);
    }

    GoneException(String message,
                         Exception innerException,
                         URI requestUri,
                         String localIpAddress) {
        this(message(localIpAddress, message), innerException, null, requestUri);
    }

    GoneException(Exception innerException) {
        this(RMResources.Gone, innerException, new HashMap<>(), null);
    }

    public GoneException(String message, HttpHeaders headers, URI requestUrl) {
        super(message, null, HttpUtils.asMap(headers), HttpConstants.StatusCodes.GONE, requestUrl != null ? requestUrl.toString() : null);
    }

    GoneException(String message, HttpHeaders headers, String requestUriString) {
        super(message, null, HttpUtils.asMap(headers), HttpConstants.StatusCodes.GONE, requestUriString);
    }

    public GoneException(String message,
                         Exception innerException,
                         HttpHeaders headers,
                         URI requestUrl) {
        super(message, innerException, HttpUtils.asMap(headers), HttpConstants.StatusCodes.GONE, requestUrl != null ? requestUrl.toString() : null);
    }

    public GoneException(String message,
                         Exception innerException,
                         Map<String, String> headers,
                         String requestUriString) {
        super(message, innerException, headers, HttpConstants.StatusCodes.GONE, requestUriString);
    }

    GoneException(CosmosError cosmosError, Map<String, String> headers) {
        super(HttpConstants.StatusCodes.GONE, cosmosError, headers);
    }

    private static String message(String localIP, String baseMessage) {
        if (!Strings.isNullOrEmpty(localIP)) {
            return String.format(
                RMResources.ExceptionMessageAddIpAddress,
                baseMessage,
                localIP);
        }

        return baseMessage;
    }
}