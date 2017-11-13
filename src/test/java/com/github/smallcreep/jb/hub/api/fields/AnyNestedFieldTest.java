package com.github.smallcreep.jb.hub.api.fields;

import com.github.smallcreep.jb.hub.api.Field;
import org.cactoos.ScalarHasValue;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * Test Case for {@link AnyNestedField}.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
 */
public final class AnyNestedFieldTest {

    /**
     * Check any nested field
     * return asterisk following the parent's field through the slash.
     *
     * @throws Exception If fails
     */
    @Test
    public void anyNestedField() throws Exception {
        MatcherAssert.assertThat(
            "Any Nested field doesn't return asterisk "
                + "following the parent's field through the slash!",
            new AnyNestedField(
                new Field.Simple("first")
            ),
            new ScalarHasValue<>("first/*")
        );
    }

}
