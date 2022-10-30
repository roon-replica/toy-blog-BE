package toy.blog.be.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.blog.be.controller.PagedRequest;
import toy.blog.be.controller.PagedResponse;
import toy.blog.be.controller.post.dto.response.CommentResponse;
import toy.blog.be.domain.entity.Comment;
import toy.blog.be.infra.IdGenerator;
import toy.blog.be.repository.CommentRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    private Function<Comment, CommentResponse> converter = comment -> CommentResponse.builder()
            .id(comment.getId())
            .content(comment.getContent())
            .writerId(comment.getWriterId())
            .createdAt(comment.getCreatedAt())
            .modifiedAt(comment.getModifiedAt())
            .build();

    @Transactional
    public String createComment(String content, String writerId) {
        var comment = Comment.builder()
                .id(IdGenerator.newId())
                .content(content)
                .writerId(writerId)
                .build();

        return commentRepository.save(comment).getId();
    }

    @Transactional
    public String updateComment(String id, String content, String writerId) {
        var comment = commentRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        comment.update(content, writerId);

        return id;
    }

    @Transactional
    public void deleteComment(String id) {
        var comment = commentRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        commentRepository.delete(comment);
    }

    public CommentResponse getComment(String id) {
        var comment = commentRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return converter.apply(comment);
    }

    public PagedResponse<CommentResponse, Comment> getComments(PagedRequest pagedRequest) {
        var pageable = pagedRequest.getPageable(Sort.by("modifiedAt").descending());
        var response = commentRepository.findAll(pageable);

        return new PagedResponse<>(response, converter);
    }

}
