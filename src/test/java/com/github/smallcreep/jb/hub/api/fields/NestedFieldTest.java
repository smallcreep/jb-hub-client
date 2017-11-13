package com.github.smallcreep.jb.hub.api.fields;

import com.github.smallcreep.jb.hub.api.Field;
import org.cactoos.ScalarHasValue;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * Test Case for {@link NestedField}.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
 */
public final class NestedFieldTest {

    /**
     * Check nested field
     * return child's field following the parent's field through the slash.
     *
     * @throws Exception If fails
     */
    @Test
    public void nestedField() throws Exception {
        MatcherAssert.assertThat(
            "Nested field doesn't return child's field following "
                + "the parent's field through the slash!",
            new NestedField(
                new Field.Simple("first"),
                new Field.Simple("second")
            ),
            new ScalarHasValue<>("first/second")
        );
    }

}
