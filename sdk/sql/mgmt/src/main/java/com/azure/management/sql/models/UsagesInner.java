// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.management.sql.models;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Headers;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.PathParam;
import com.azure.core.annotation.QueryParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.PagedFlux;
import com.azure.core.http.rest.PagedIterable;
import com.azure.core.http.rest.PagedResponse;
import com.azure.core.http.rest.PagedResponseBase;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.http.rest.SimpleResponse;
import com.azure.core.management.CloudException;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in Usages. */
public final class UsagesInner {
    /** The proxy service used to perform REST calls. */
    private final UsagesService service;

    /** The service client containing this operation class. */
    private final SqlManagementClientImpl client;

    /**
     * Initializes an instance of UsagesInner.
     *
     * @param client the instance of the service client containing this operation class.
     */
    UsagesInner(SqlManagementClientImpl client) {
        this.service = RestProxy.create(UsagesService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for SqlManagementClientUsages to be used by the proxy service to perform
     * REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "SqlManagementClientU")
    private interface UsagesService {
        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @Get(
            "/subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.Sql"
                + "/instancePools/{instancePoolName}/usages")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(CloudException.class)
        Mono<SimpleResponse<UsageListResultInner>> listByInstancePool(
            @HostParam("$host") String host,
            @PathParam("resourceGroupName") String resourceGroupName,
            @PathParam("instancePoolName") String instancePoolName,
            @QueryParam("expandChildren") Boolean expandChildren,
            @PathParam("subscriptionId") String subscriptionId,
            @QueryParam("api-version") String apiVersion,
            Context context);

        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(CloudException.class)
        Mono<SimpleResponse<UsageListResultInner>> listByInstancePoolNext(
            @PathParam(value = "nextLink", encoded = true) String nextLink, Context context);
    }

    /**
     * Gets all instance pool usage metrics.
     *
     * @param resourceGroupName The name of the resource group that contains the resource. You can obtain this value
     *     from the Azure Resource Manager API or the portal.
     * @param instancePoolName The name of the instance pool to be retrieved.
     * @param expandChildren Optional request parameter to include managed instance usages within the instance pool.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return all instance pool usage metrics.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<UsageInner>> listByInstancePoolSinglePageAsync(
        String resourceGroupName, String instancePoolName, Boolean expandChildren) {
        final String apiVersion = "2018-06-01-preview";
        return FluxUtil
            .withContext(
                context ->
                    service
                        .listByInstancePool(
                            this.client.getHost(),
                            resourceGroupName,
                            instancePoolName,
                            expandChildren,
                            this.client.getSubscriptionId(),
                            apiVersion,
                            context))
            .<PagedResponse<UsageInner>>map(
                res ->
                    new PagedResponseBase<>(
                        res.getRequest(),
                        res.getStatusCode(),
                        res.getHeaders(),
                        res.getValue().value(),
                        res.getValue().nextLink(),
                        null))
            .subscriberContext(context -> context.putAll(FluxUtil.toReactorContext(this.client.getContext())));
    }

    /**
     * Gets all instance pool usage metrics.
     *
     * @param resourceGroupName The name of the resource group that contains the resource. You can obtain this value
     *     from the Azure Resource Manager API or the portal.
     * @param instancePoolName The name of the instance pool to be retrieved.
     * @param expandChildren Optional request parameter to include managed instance usages within the instance pool.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return all instance pool usage metrics.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<UsageInner> listByInstancePoolAsync(
        String resourceGroupName, String instancePoolName, Boolean expandChildren) {
        return new PagedFlux<>(
            () -> listByInstancePoolSinglePageAsync(resourceGroupName, instancePoolName, expandChildren),
            nextLink -> listByInstancePoolNextSinglePageAsync(nextLink));
    }

    /**
     * Gets all instance pool usage metrics.
     *
     * @param resourceGroupName The name of the resource group that contains the resource. You can obtain this value
     *     from the Azure Resource Manager API or the portal.
     * @param instancePoolName The name of the instance pool to be retrieved.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return all instance pool usage metrics.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<UsageInner> listByInstancePoolAsync(String resourceGroupName, String instancePoolName) {
        final Boolean expandChildren = null;
        final Context context = null;
        return new PagedFlux<>(
            () -> listByInstancePoolSinglePageAsync(resourceGroupName, instancePoolName, expandChildren),
            nextLink -> listByInstancePoolNextSinglePageAsync(nextLink));
    }

    /**
     * Gets all instance pool usage metrics.
     *
     * @param resourceGroupName The name of the resource group that contains the resource. You can obtain this value
     *     from the Azure Resource Manager API or the portal.
     * @param instancePoolName The name of the instance pool to be retrieved.
     * @param expandChildren Optional request parameter to include managed instance usages within the instance pool.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return all instance pool usage metrics.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<UsageInner> listByInstancePool(
        String resourceGroupName, String instancePoolName, Boolean expandChildren) {
        return new PagedIterable<>(listByInstancePoolAsync(resourceGroupName, instancePoolName, expandChildren));
    }

    /**
     * Gets all instance pool usage metrics.
     *
     * @param resourceGroupName The name of the resource group that contains the resource. You can obtain this value
     *     from the Azure Resource Manager API or the portal.
     * @param instancePoolName The name of the instance pool to be retrieved.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return all instance pool usage metrics.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<UsageInner> listByInstancePool(String resourceGroupName, String instancePoolName) {
        final Boolean expandChildren = null;
        final Context context = null;
        return new PagedIterable<>(listByInstancePoolAsync(resourceGroupName, instancePoolName, expandChildren));
    }

    /**
     * Get the next page of items.
     *
     * @param nextLink The nextLink parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a list of usages.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<UsageInner>> listByInstancePoolNextSinglePageAsync(String nextLink) {
        return FluxUtil
            .withContext(context -> service.listByInstancePoolNext(nextLink, context))
            .<PagedResponse<UsageInner>>map(
                res ->
                    new PagedResponseBase<>(
                        res.getRequest(),
                        res.getStatusCode(),
                        res.getHeaders(),
                        res.getValue().value(),
                        res.getValue().nextLink(),
                        null))
            .subscriberContext(context -> context.putAll(FluxUtil.toReactorContext(this.client.getContext())));
    }
}
