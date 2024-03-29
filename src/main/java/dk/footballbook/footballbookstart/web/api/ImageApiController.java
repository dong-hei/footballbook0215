package dk.footballbook.footballbookstart.web.api;

import dk.footballbook.footballbookstart.config.auth.PrincipalDetails;
import dk.footballbook.footballbookstart.domain.img.Image;
import dk.footballbook.footballbookstart.svc.ImageService;
import dk.footballbook.footballbookstart.svc.LikesService;
import dk.footballbook.footballbookstart.web.dto.CMResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ImageApiController {

    private final ImageService imgSvc;
    private final LikesService likesSvc;

    @GetMapping("/api/image")
    public ResponseEntity<?> imageStory(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                        @PageableDefault(size = 3) Pageable pageable) {
        Page<Image> images = imgSvc.imgStory(principalDetails.getUser().getId(), pageable);
        return new ResponseEntity<>(new CMResDto<>(1, "성공!", images), HttpStatus.OK);
    }

    //어떤 이미지를 좋아요했는지
    @PostMapping("/api/image/{imageId}/likes")
    public ResponseEntity<?> likes(@PathVariable int imageId,
                                   @AuthenticationPrincipal PrincipalDetails principalDetails){

        likesSvc.like(imageId,principalDetails.getUser().getId());
        return new ResponseEntity<>(new CMResDto<>(1, "좋아요 성공!", null), HttpStatus.CREATED);

    }

    @DeleteMapping("/api/image/{imageId}/likes")
    public ResponseEntity<?> unlikes(@PathVariable int imageId,
                                   @AuthenticationPrincipal PrincipalDetails principalDetails){

        likesSvc.unLike(imageId,principalDetails.getUser().getId());
        return new ResponseEntity<>(new CMResDto<>(1, "좋아요 해제 성공!", null), HttpStatus.OK);

    }
}
