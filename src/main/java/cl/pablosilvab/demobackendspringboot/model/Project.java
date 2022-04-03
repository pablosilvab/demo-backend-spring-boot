package cl.pablosilvab.demobackendspringboot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Project {
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("url")
    private String url;
}
