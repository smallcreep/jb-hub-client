package com.github.smallcreep.jb.hub.api;

/**
 * JetBrains Hub Permissions.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @see <a href="https://www.jetbrains.com/help/hub/HUB-REST-API_Permissions_Get-All-Permissions.html">JetBrains Hub Permissions</a>
 * @since 0.2.0
 */
public interface Permissions extends Iterable<Permission>,
    Partial<Permissions>, Sortable<Permissions>, Searchable<Permissions>,
    Subset<Permissions> {
}
