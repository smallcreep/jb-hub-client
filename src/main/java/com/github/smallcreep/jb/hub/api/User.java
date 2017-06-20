package com.github.smallcreep.jb.hub.api;

import com.github.smallcreep.json.JsonReadable;

/**
 * JetBrains Hub User API.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @see <a href="https://www.jetbrains.com/help/hub/HUB-REST-API_Users_Get-User.html">User API</a>
 * @since 0.1
 */
public interface User extends JsonReadable {

    /**
     * Get its owner.
     * @return Users
     */
    Users users();
}
