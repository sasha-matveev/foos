package org.am.mongo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "foos")
@SuppressWarnings("FieldCanBeLocal")
public class FooDocument {
    @Id private String id;
    private String fooName;

    public FooDocument() {
    }

    public FooDocument setId(String id) {
        this.id = id;
        return this;
    }

    public FooDocument setFooName(String fooName) {
        this.fooName = fooName;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
