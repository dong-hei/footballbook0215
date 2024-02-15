package dk.footballbook.footballbookstart.web.dto;

import dk.footballbook.footballbookstart.domain.img.Image;
import dk.footballbook.footballbookstart.domain.user.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class ImageUploadDto {
    private MultipartFile File;
    private String caption;

    public Image toEntity(String postImageUrl, User user){
        return Image.builder()
                .caption(caption)
                .postImgUrl(postImageUrl)
                .user(user)
                .build();
    }
}
