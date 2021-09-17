package com.market.secondshoes;

import com.market.secondshoes.domain.item.*;
import com.market.secondshoes.domain.member.Member;
import com.market.secondshoes.dto.item.ItemAddDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TestData {

    private final InitService initService;

//    @PostConstruct
    public void init() {
        initService.init();
    }

    @Component
    static class InitService {
        @PersistenceContext
        private EntityManager em;

        @Transactional
        public void init() {

            for (int i = 0; i < 10; i++) {
                Member member = Member.createMember();
                member.change("test" + i + "@test.com", "test" + i, "test!" + i);
                em.persist(member);

                for (int j = 0; j < 10; j++) {
                    ItemAddDto itemAddDto = new ItemAddDto();
                    List<UploadImage> uploadImages = new ArrayList<>();

                    itemAddDto.setTitle("title"+(i*10+j));
                    if (j % 2 == 0) {
                        itemAddDto.setGender(Gender.FEMALE);
                    } else {
                        itemAddDto.setGender(Gender.MALE);
                    }

                    if (j % 3 == 0) {
                        itemAddDto.setSize(Size.SIZE220);
                        itemAddDto.setBrand(Brand.ADIDAS);
                        itemAddDto.setCategory(Category.DERBY);

                        UploadImage uploadImage0 = UploadImage.createUploadImage("test0.png", "test0.png");
                        uploadImages.add(uploadImage0);
                    } else if (j % 3 == 1) {
                        itemAddDto.setSize(Size.SIZE230);
                        itemAddDto.setBrand(Brand.NIKE);
                        itemAddDto.setCategory(Category.OXFORD);

                        itemAddDto.setSize(Size.SIZE240);
                        itemAddDto.setBrand(Brand.PUMA);
                        itemAddDto.setCategory(Category.WING_TIP);

                        UploadImage uploadImage1 = UploadImage.createUploadImage("test1.jpg", "test1.jpg");
                        UploadImage uploadImage2 = UploadImage.createUploadImage("test2.jpg", "test2.jpg");
                        uploadImages.add(uploadImage1);
                        uploadImages.add(uploadImage2);
                    } else {
                        itemAddDto.setSize(Size.SIZE250);
                        itemAddDto.setBrand(Brand.ADIDAS);
                        itemAddDto.setCategory(Category.DERBY);

                        itemAddDto.setSize(Size.SIZE260);
                        itemAddDto.setBrand(Brand.NIKE);
                        itemAddDto.setCategory(Category.OXFORD);

                        itemAddDto.setSize(Size.SIZE270);
                        itemAddDto.setBrand(Brand.PUMA);
                        itemAddDto.setCategory(Category.WING_TIP);

                        UploadImage uploadImage1 = UploadImage.createUploadImage("test3.png", "test3.png");
                        UploadImage uploadImage2 = UploadImage.createUploadImage("test4.png", "test4.png");
                        UploadImage uploadImage3 = UploadImage.createUploadImage("test5.png", "test5.png");
                        uploadImages.add(uploadImage1);
                        uploadImages.add(uploadImage2);
                        uploadImages.add(uploadImage3);
                    }
                    itemAddDto.setPrice(j*1000);
                    itemAddDto.setExplain("itemTest"+j);

                    Item item = Item.createItem();
                    item.change(member, itemAddDto, uploadImages);
                    em.persist(item);
                }
            }
        }
    }
}
