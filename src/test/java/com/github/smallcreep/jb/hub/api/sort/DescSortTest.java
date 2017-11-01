package com.github.smallcreep.jb.hub.api.sort;

import org.cactoos.ScalarHasValue;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * Test Case for {@link DescSort}.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
 */
public final class DescSortTest {

    /**
     * Check desc sort return encapsulated field name
     * with order suffix.
     *
     * @throws Exception If fails
     */
    @Test
    public void orderDescField() throws Exception {
        MatcherAssert.assertThat(
            "Desc sort doesn't return field name with other suffix!",
            new DescSort("first"),
            new ScalarHasValue<>("first:desc")
        );
    }
}
