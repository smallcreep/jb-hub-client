package com.github.smallcreep.jb.hub.api;

/**
 * JetBrains Hub User API.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @see <a href="https://www.jetbrains.com/help/hub/HUB-REST-API_Users_Get-User.html">User API</a>
 * @since 0.1
 */
public final class RtUsers implements Users {

    @Override
    public User self() {
        return null;
    }
}
