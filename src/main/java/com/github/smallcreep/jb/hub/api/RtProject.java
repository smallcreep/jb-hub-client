package com.github.smallcreep.jb.hub.api;

import com.jcabi.http.Request;
import java.io.IOException;
import javax.json.JsonObject;

/**
 * JetBrains HUB Project.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @see <a href="https://www.jetbrains.com/help/hub/HUB-REST-API_Projects_Get-Project.html">JetBrains HUB Project</a>
 * @since 0.2.0
 */
final class RtProject implements Project {

    /**
     * Request.
     */
    private final Request req;

    /**
     * Ctor.
     * @param req Request
     * @param id Project id
     */
    RtProject(final Request req, final String id) {
        this.req = req.uri()
            .path("/" + id)
            .back();
    }

    @Override
    public JsonObject json() throws IOException {
        return new RtJson(this.req).fetch();
    }
}
