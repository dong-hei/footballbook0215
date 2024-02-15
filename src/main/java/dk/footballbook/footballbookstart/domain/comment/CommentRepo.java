package dk.footballbook.footballbookstart.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

//    @Modifying
//    @Query(value = "INSERT INTO comment(content, imageId, userId, createDate) VALUES(:content, :imageId, :userId, now())", nativeQuery = true)
//    Comment writeComment(String content, int imageId, int userId);
//    이걸론 Comment 리턴을 못받음
}
