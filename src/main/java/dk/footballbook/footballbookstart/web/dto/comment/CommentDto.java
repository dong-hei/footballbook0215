package dk.footballbook.footballbookstart.web.dto.comment;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CommentDto {

    @NotBlank // 빈값이거나 null 체크, 빈공백(space)까지 체크
    private String content;

    @NotNull // null값 체크
//    @NotEmpty 빈값이거나 Null 체크
    private Integer imageId;

    //toEntity가 필요 없다
}
