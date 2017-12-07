/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Ilia Rogozhin (ilia.rogozhin@gmail.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.smallcreep.jb.hub.api;

import com.github.smallcreep.jb.hub.api.iterable.RtPagination;
import com.jcabi.aspects.Immutable;
import com.jcabi.aspects.Loggable;
import com.jcabi.http.Request;
import java.util.Iterator;
import javax.json.JsonObject;
import lombok.EqualsAndHashCode;
import org.cactoos.iterable.Mapped;

/**
 * JetBrains Hub User API.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @see <a href="https://www.jetbrains.com/help/hub/HUB-REST-API_Users_Get-User.html">User API</a>
 * @since 0.1
 */
@Immutable
@Loggable(Loggable.DEBUG)
@EqualsAndHashCode(of = {"req", "hub", "origin"})
final class RtUsers implements Users {

    /**
     * Entry request.
     */
    private final Request origin;

    /**
     * RESTful request.
     */
    private final Request req;

    /**
     * Hub owner.
     */
    private final Hub hub;

    /**
     * Ctor.
     * @param hub Hub
     */
    RtUsers(final Hub hub) {
        this(hub.entry(), hub);
    }

    /**
     * Ctor.
     *
     * @param request Entry req
     * @param hub Hub
     */
    RtUsers(final Request request, final Hub hub) {
        this(request, request.uri().path("/users").back(), hub);
    }

    /**
     * Ctor.
     *
     * @param origin Entry request
     * @param req RESTful request
     * @param hub Hub
     */
    private RtUsers(final Request origin, final Request req, final Hub hub) {
        this.origin = origin;
        this.req = req;
        this.hub = hub;
    }

    @Override
    public Hub hub() {
        return this.hub;
    }

    @Override
    public User self() {
        return this.user("me");
    }

    @Override
    public User guest() {
        return this.user("guest");
    }

    @Override
    public User user(final String id) {
        return new RtUser(this.req, this, id);
    }

    @Override
    public Iterator<User> iterator() {
        return new Mapped<JsonObject, User>(
            jsn -> new JsUser(this, jsn),
            new RtPagination(this.req, "users")
        ).iterator();
    }

    @Override
    public Users max(final int max) {
        return new RtUsers(
            this.origin,
            this.req
                .uri()
                .queryParam("top", max)
                .back(),
            this.hub
        );
    }

    @Override
    public Users sort(final Sort sort) throws Exception {
        return new RtUsers(
            this.origin,
            this.req
                .uri()
                .queryParam("orderBy", sort.value())
                .back(),
            this.hub
        );
    }

    @Override
    public Users fields(final Fields fields) throws Exception {
        return new RtUsers(
            this.origin,
            this.req
                .uri()
                .queryParam("fields", fields.value())
                .back(),
            this.hub
        );
    }
}
