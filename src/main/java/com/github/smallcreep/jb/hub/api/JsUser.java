package com.github.smallcreep.jb.hub.api;

import java.io.IOException;
import javax.json.JsonObject;

/**
 * Return User from encapsulated json.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
 */
public final class JsUser implements User {

    /**
     * Users.
     */
    private final Users users;

    /**
     * Json for this user.
     */
    private final JsonObject jsn;

    /**
     * Ctor.
     * @param users Users
     * @param json Json for this user
     */
    public JsUser(final Users users, final JsonObject json) {
        this.users = users;
        this.jsn = json;
    }

    @Override
    public Users users() {
        return this.users;
    }

    @Override
    public JsonObject json() throws IOException {
        return this.jsn;
    }
}
