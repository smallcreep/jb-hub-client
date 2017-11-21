package com.github.smallcreep.json;

import javax.json.Json;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * Test Case for {@link ObjectHasJson}.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
 */
public final class ObjectHasJsonTest {

    /**
     * Check matcher json compare equivalent elements.
     * @throws Exception If fails
     */
    @Test
    public void checkHasJson() throws Exception {
        MatcherAssert.assertThat(
            "Matcher json doesn't compare equivalent elements!",
            () -> Json.createObjectBuilder()
                      .add("name", "value")
                      .add("int", 1)
                      .build(),
            new ObjectHasJson(
                Json.createObjectBuilder()
                    .add("name", "value")
                    .add("int", 1)
                    .build()
            )
        );
    }

    /**
     * Check matcher json throw {@link AssertionError}
     * if elements doesn't equivalent.
     *
     * @throws Exception If fails
     */
    @Test(expected = AssertionError.class)
    public void throwExceptionIfNotEquals() throws Exception {
        MatcherAssert.assertThat(
            "Matcher json compare not equivalent elements!",
            () -> Json.createObjectBuilder()
                      .add("int", 1)
                      .build(),
            new ObjectHasJson(
                Json.createObjectBuilder()
                    .add("name", "value")
                    .build()
            )
        );
    }

}
