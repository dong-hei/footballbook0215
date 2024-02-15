package dk.footballbook.footballbookstart.web.api;

import dk.footballbook.footballbookstart.config.auth.PrincipalDetails;
import dk.footballbook.footballbookstart.domain.comment.Comment;
import dk.footballbook.footballbookstart.svc.CommentService;
import dk.footballbook.footballbookstart.web.dto.CMResDto;
import dk.footballbook.footballbookstart.web.dto.comment.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/api/comment")
    public ResponseEntity<?> writeComment(@Valid @RequestBody CommentDto commentDto,
                                          BindingResult bindingResult,
                                          @AuthenticationPrincipal PrincipalDetails principalDetails){

        Comment comment = commentService.writeComment(commentDto.getContent(), commentDto.getImageId(), principalDetails.getUser().getId());
        return new ResponseEntity<>(new CMResDto<>(1, "댓글 쓰기 성공!", comment), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/comment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable int id){
        commentService.deleteComment(id);
        return new ResponseEntity<>(new CMResDto<>(1, "댓글 삭제 성공!", null), HttpStatus.OK);
    }
}
