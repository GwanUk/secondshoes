package com.market.secondshoes.repository;

import com.market.secondshoes.domain.item.*;
import com.market.secondshoes.dto.item.ItemConditionDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

import static com.market.secondshoes.domain.item.QItem.item;

public class ItemRepositoryImpl implements ItemRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public ItemRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Item> search(ItemConditionDto condition, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        if (StringUtils.hasText(condition.getSearch())) {
            String[] searches = condition.getSearch().trim().split(" ");
            Arrays.asList(searches).forEach(s -> {
                builder.or(item.title.contains(s));
                builder.or(item.explain.contains(s));
            });
        }
        QueryResults<Item> results = queryFactory.select(item)
                .where(builder,
                        genderEq(condition.getGender()),
                        priceGoe(condition.getPriceGoe()),
                        priceLoe(condition.getPriceLoe()),
                        sizesIn(condition.getSizes()),
                        brandsIn(condition.getBrands()),
                        categories(condition.getCategories()))
                .from(item)
                .fetchResults();
        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    private BooleanExpression genderEq(Gender gender) {
        return gender != null ? item.gender.eq(gender) : null;
    }

    private BooleanExpression priceGoe(Integer priceGoe) {
        return priceGoe != null ? item.price.goe(priceGoe) : null;
    }

    private BooleanExpression priceLoe(Integer priceLoe) {
        return priceLoe != null ? item.price.loe(priceLoe) : null;
    }

    private BooleanExpression sizesIn(List<Size> sizes) {
        return !sizes.isEmpty() ? item.size.in(sizes) : null;
    }

    private BooleanExpression brandsIn(List<Brand> brands) {
        return !brands.isEmpty() ? item.brand.in(brands) : null;
    }

    private BooleanExpression categories(List<Category> categories) {
        return !categories.isEmpty() ? item.category.in(categories) : null;
    }




}
