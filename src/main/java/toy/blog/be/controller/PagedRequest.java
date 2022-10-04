package toy.blog.be.controller;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
public class PagedRequest {
    private int page;
    private int size;

    public PagedRequest(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page, size, sort);
    }
}
