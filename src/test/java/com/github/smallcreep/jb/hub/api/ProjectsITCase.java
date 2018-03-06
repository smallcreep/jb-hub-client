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
                        "2f185454-8be6-459e-bb8c-def23a607ded"
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
                    "2f185454-8be6-459e-bb8c-def23a607ded"
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
                                    "ca54fdd7-296d-4aed-be79-4c284b0c0508"
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
                                    "618499ff-0e18-490e-856d-7cb55dca8e2b"
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
