package com.market.secondshoes;

import com.market.secondshoes.domain.item.Item;
import com.market.secondshoes.domain.item.UploadImage;
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

    @PostConstruct
    public void init() {
        initService.init();
    }

    @Component
    static class InitService {
        @PersistenceContext
        private EntityManager em;

        @Transactional
        public void init() {

            for (int i = 0; i < 5; i++) {
                Member member = Member.createMember("test" + i + "@naver.com", "test" + i, "test!" + i);
                em.persist(member);
                for (int j = 0; j < 10; j++) {
                    ItemAddDto itemAddDto = new ItemAddDto();
                    itemAddDto.setTitle("title"+j);
                    itemAddDto.setPrice(j);
                    itemAddDto.setExplain("호랑나비"+j);
                    List<UploadImage> uploadImages = new ArrayList<>();
                    Item item = Item.createItem();
                    item.change(member, itemAddDto, uploadImages);
                    em.persist(item);
                }
            }
        }
    }
}
