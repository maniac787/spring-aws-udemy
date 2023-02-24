package ec.com.nwi.springaws.bean;

import lombok.Data;
import lombok.ToString;

@Data
@ToString()
public class HelloWorldBean {
    private final String message;

    public HelloWorldBean(String s) {
        this.message = s;
    }
}
