package com.github.smallcreep.jb.hub.api.sort;

import org.cactoos.ScalarHasValue;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * Test Case for {@link AscSort}.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
 */
public final class AscSortTest {

    /**
     * Check asc sort return encapsulated field name
     * with order suffix.
     *
     * @throws Exception If fails
     */
    @Test
    public void orderAscField() throws Exception {
        MatcherAssert.assertThat(
            "Asc sort doesn't return field name with other suffix!",
            new AscSort("first"),
            new ScalarHasValue<>("first:asc")
        );
    }
}
