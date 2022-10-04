package toy.blog.be.controller;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
public class PagedResponse<DTO, ENTITY> {
    private List<DTO> dtos;

    private int totalPage;
    private int nowPage;
    private int size;
    private int start, end;
    private boolean prev, next;
    private List<Integer> pageNumbers;

    private static final int PAGE_COUNT_PER_PAGE = 10;

    public PagedResponse(Page<ENTITY> result, Function<ENTITY, DTO> converter) {
        dtos = result.stream()
                .map(converter)
                .collect(Collectors.toList());

        totalPage = result.getTotalPages();
        var pageable = result.getPageable();

        nowPage = pageable.getPageNumber() + 1;
        size = pageable.getPageSize();

        int tempEnd = (int) (Math.ceil(nowPage / 10.0)) * 10;

        start = tempEnd - PAGE_COUNT_PER_PAGE - 1;
        prev = start > 1;
        next = tempEnd < totalPage;
        end = Math.min(totalPage, tempEnd);

        pageNumbers = IntStream.rangeClosed(start, end)
                .boxed()
                .collect(Collectors.toList());
    }
}
