package toy.blog.be.infra;

import java.util.UUID;

public class IdGenerator {
    public static String newId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
