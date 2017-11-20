package com.github.smallcreep.json;

import javax.json.JsonObject;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * Matcher for the json.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
 *
 * @todo #34:15m/DEV Implement this Matcher.
 *  Matcher should encapsulate expected json.
 *  Matcher return true if encapsulate json
 *  equals returned json from {@link JsonReadable} item.
 */
public final class ObjectHasJson extends TypeSafeMatcher<JsonReadable> {

    /**
     * Expected json.
     */
    private final JsonObject expected;

    /**
     * Ctor.
     *
     * @param expected Expected json
     */
    public ObjectHasJson(final JsonObject expected) {
        this.expected = expected;
    }

    @Override
    protected boolean matchesSafely(final JsonReadable item) {
        return false;
    }

    @Override
    public void describeTo(final Description description) {

    }
}
