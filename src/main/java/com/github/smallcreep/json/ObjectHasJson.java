package com.github.smallcreep.json;

import javax.json.JsonObject;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsEqual;

/**
 * Matcher for the json.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
 */
public final class ObjectHasJson extends TypeSafeMatcher<JsonReadable> {

    /**
     * Json matcher.
     */
    private final Matcher<JsonObject> matcher;

    /**
     * Ctor.
     * @param expected Expected json
     */
    public ObjectHasJson(final JsonObject expected) {
        this(new IsEqual<>(expected));
    }

    /**
     * Ctor.
     * @param matcher Matcher of the json
     */
    public ObjectHasJson(final Matcher<JsonObject> matcher) {
        this.matcher = matcher;
    }

    @Override
    protected boolean matchesSafely(final JsonReadable item) {
        return matcher.matches(new UncheckedJsonReadable(item).json());
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("JsonReadable ");
        description.appendDescriptionOf(this.matcher);
    }
}
