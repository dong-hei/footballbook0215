package dk.footballbook.footballbookstart.svc;

import dk.footballbook.footballbookstart.domain.comment.Comment;
import dk.footballbook.footballbookstart.domain.comment.CommentRepo;
import dk.footballbook.footballbookstart.domain.img.Image;
import dk.footballbook.footballbookstart.domain.user.User;
import dk.footballbook.footballbookstart.domain.user.UserRepository;
import dk.footballbook.footballbookstart.handler.ex.CustomApiEx;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepo commentRepo;
    private final UserRepository userRepository;

    @Transactional
    public Comment writeComment(String content,int imageId, int userId){

        //객체를 만들때 아이디 값만 담아서 insert 단 return 시 img 객체와 id값만 가지고 있는 빈 객체를 받는다.
        Image image = new Image();
        image.setId(imageId);

        User userEntity = userRepository.findById(userId).orElseThrow(() -> {
            throw new CustomApiEx("유저 아이디를 찾을 수 없습니다.");
        });

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setImage(image);
        comment.setUser(userEntity);

        return commentRepo.save(comment);
    }

    @Transactional
    public void deleteComment(int id){
        try{
            commentRepo.deleteById(id);
        }catch (Exception e){
            throw new CustomApiEx(e.getMessage());
        }
    }
}
