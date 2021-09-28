package com.market.secondshoes.dto.item;

import com.market.secondshoes.domain.item.*;
import com.market.secondshoes.domain.member.Member;
import com.market.secondshoes.dto.member.MemberInfoDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
public class ItemDetailDto {

    private Long id;
    private String title;
    private Gender gender;
    private Size size;
    private Brand brand;
    private Category category;
    private Integer price;
    private String explain;
    private List<UploadImage> uploadImages ;
    private MemberInfoDto memberInfoDto;
    private String createdDate;
    private String lastModifiedDate;
    private Long viewCount;
    private boolean itemWished;

    protected ItemDetailDto() {
    }

    public static ItemDetailDto createItemDetailDto(Long id, String title, Gender gender, Size size, Brand brand, Category category, Integer price, String explain, List<UploadImage> uploadImages, Member member, LocalDateTime createdDate, LocalDateTime lastModifiedDate, Long viewCount) {
        ItemDetailDto itemDetailDto = new ItemDetailDto();
        itemDetailDto.id = id;
        itemDetailDto.title = title;
        itemDetailDto.gender = gender;
        itemDetailDto.size = size;
        itemDetailDto.brand = brand;
        itemDetailDto.category = category;
        itemDetailDto.price = price;
        itemDetailDto.explain = explain;
        itemDetailDto.uploadImages = uploadImages;
        itemDetailDto.memberInfoDto = MemberInfoDto.createMemberInfoDto(member);
        itemDetailDto.createdDate = createdDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        itemDetailDto.lastModifiedDate = lastModifiedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        itemDetailDto.viewCount = viewCount;
        return itemDetailDto;
    }

    public static ItemDetailDto createItemDetailDto(Item item) {
        return createItemDetailDto(item.getId(), item.getTitle(), item.getGender(), item.getSize(), item.getBrand(), item.getCategory(), item.getPrice(), item.getItemExplain(), item.getUploadImages(), item.getMember(), item.getCreatedDate(), item.getLastModifiedDate(), item.getViewCount());
    }

    public void wished() {
        itemWished = true;
    }
}
