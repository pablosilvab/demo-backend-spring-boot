package cl.pablosilvab.demobackendspringboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Message {
    private String name;
    private String contact;
    private String subject;
    private String text;
}
