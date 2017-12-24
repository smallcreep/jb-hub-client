package com.github.smallcreep.jb.hub.api;

import java.io.IOException;
import javax.json.JsonObject;

/**
 * Return Permission from encapsulated json.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
 */
final class JsPermission implements Permission {

    /**
     * Encapsulated json.
     */
    private final JsonObject json;

    /**
     * Ctor.
     * @param json Permission json
     */
    JsPermission(final JsonObject json) {
        this.json = json;
    }

    @Override
    public JsonObject json() throws IOException {
        return this.json;
    }
}
