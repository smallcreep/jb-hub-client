package com.github.smallcreep.jb.hub.api;

import com.jcabi.aspects.Loggable;
import com.jcabi.http.Request;
import com.jcabi.http.response.JsonResponse;
import com.jcabi.http.response.RestResponse;
import java.io.IOException;
import java.net.HttpURLConnection;
import javax.json.JsonObject;
import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 * Hub JSON item.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@Immutable
@Loggable(Loggable.DEBUG)
final class RtJson {

    /**
     * RESTful request.
     */
    private final Request request;

    /**
     * Public ctor.
     * @param req Request
     */
    RtJson(final Request req) {
        this.request = req;
    }

    /**
     * Fetch JSON object.
     * @return JSON object
     * @throws IOException If fails
     */
    public JsonObject fetch() throws IOException {
        return this.request.fetch()
            .as(RestResponse.class)
            .assertStatus(HttpURLConnection.HTTP_OK)
            .as(JsonResponse.class)
            .json()
            .readObject();
    }
}
