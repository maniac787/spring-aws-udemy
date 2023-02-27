package ec.com.nwi.springaws.rest.filter;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonFilter("SomeBeanFilter")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SomeBean {
    private String field1;

    private String field2;

    //@JsonIgnore
    private String field3;

    @Override
    public String toString() {
        return "SomeBean [field1=" + field1 + ", field2=" + field2 + ", field3=" + field3 + "]";
    }

}