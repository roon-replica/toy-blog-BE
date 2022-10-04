package toy.blog.be.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="sample controller", description = "sample test")
@RestController
public class SampleController {

    @Operation(summary = "return sample", description = "return sample string")
    @GetMapping("/sample")
    public String sample(){
        return "sample";
    }
}
