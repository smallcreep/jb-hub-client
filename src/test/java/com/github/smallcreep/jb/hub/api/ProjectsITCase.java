package com.github.smallcreep.jb.hub.api;

import com.github.smallcreep.jb.hub.api.fields.MultipleFields;
import com.github.smallcreep.jb.hub.api.fields.NestedField;
import com.github.smallcreep.jb.hub.api.fields.SubFields;
import com.github.smallcreep.jb.hub.api.integration.DefaultAuthByPass;
import com.github.smallcreep.jb.hub.api.query.Or;
import com.github.smallcreep.jb.hub.api.sort.AscSort;
import com.github.smallcreep.json.ObjectHasJson;
import javax.json.Json;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Integration Test Case for {@link Projects}.
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
 * @checkstyle ClassDataAbstractionCouplingCheck (3 lines)
 */
public final class ProjectsITCase {

    /**
     * Integration test case for projects,
     * check Sort, Partial request, Fields Syntax.
     * @throws Exception If fails
     */
    @Test
    @SuppressWarnings("unchecked")
    public void integrationCheckProjects() throws Exception {
        final Iterable<Project> projects = new DefaultAuthByPass()
            .hub()
            .projects()
            .max(1)
            .sort(
                new AscSort(
                    "key"
                )
            )
            .search(
                new Or(
                    new com.github.smallcreep.jb.hub.api.query.Field(
                        "key",
                        "GLBL"
                    ),
                    new com.github.smallcreep.jb.hub.api.query.Field(
                        "id",
                        "b41c4407-3349-4e57-9c69-1ddcf622545b"
                    )
                )
            )
            .fields(
                new MultipleFields(
                    new Field.Simple(
                        "key"
                    ),
                    new Field.Simple(
                        "id"
                    ),
                    new NestedField(
                        new Field.Simple(
                            "team"
                        ),
                        new Field.Simple(
                            "type"
                        )
                    ),
                    new SubFields(
                        new Field.Simple(
                            "resources"
                        ),
                        new Field.Simple(
                            "id"
                        ),
                        new Field.Simple(
                            "key"
                        )
                    )
                )
            );
        final ObjectHasJson second = new ObjectHasJson(
            Json.createObjectBuilder()
                .add(
                    "id",
                    "b41c4407-3349-4e57-9c69-1ddcf622545b"
                )
                .add(
                    "key",
                    "TEST"
                )
                .add(
                    "team",
                    Json.createObjectBuilder().add(
                        "type",
                        "projectTeam"
                    )
                )
                .build()
        );
        final ObjectHasJson first = new ObjectHasJson(
            Json.createObjectBuilder()
                .add(
                    "id",
                    "0"
                )
                .add(
                    "key",
                    "GLBL"
                )
                .add(
                    "resources",
                    Json.createArrayBuilder()
                        .add(
                            Json.createObjectBuilder()
                                .add(
                                    "id",
                                    "8b0503be-b322-49ee-99c3-a2461932e24d"
                                )
                                .add(
                                    "key",
                                    "All Users"
                                )
                        )
                        .add(
                            Json.createObjectBuilder()
                                .add(
                                    "id",
                                    "bbca3a28-3061-4190-ba9e-eb93a3727918"
                                )
                                .add(
                                    "key",
                                    "Registered Users"
                                )
                        )
                )
                .add(
                    "team",
                    Json.createObjectBuilder().add(
                        "type",
                        "projectTeam"
                    )
                )
                .build()
        );
        MatcherAssert.assertThat(
            "Integration request for get projects doesn't correct!",
            projects,
            Matchers.contains(
                first,
                second
            )
        );
    }
}
