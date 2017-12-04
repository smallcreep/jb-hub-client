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

import com.jcabi.aspects.Immutable;
import com.jcabi.aspects.Loggable;
import com.jcabi.http.Request;
import lombok.EqualsAndHashCode;

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
@EqualsAndHashCode(of = {"request", "hub"})
final class RtUsers implements Users {

    /**
     * RESTful request.
     */
    private final Request request;

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
     * @param request Entry request
     * @param hub Hub
     */
    RtUsers(final Request request, final Hub hub) {
        this.request = request.uri().path("/users").back();
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
        return new RtUser(this.request, this, id);
    }
}
