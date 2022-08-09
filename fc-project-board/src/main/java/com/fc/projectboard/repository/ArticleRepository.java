package com.fc.projectboard.repository;

import com.fc.projectboard.domain.Article;
import com.fc.projectboard.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>,
        QuerydslBinderCustomizer<QArticle> {
    @Override
    default void customize(QuerydslBindings bindings, QArticle root) {
        bindings.excludeUnlistedProperties(true); // 리스팅에 포함시키지 않은 property는 검색에서 제외
        bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy);
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase); // like '%s{v}%' 인덱스를 타지 못할 수 있다
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase); // like '%s{v}%' 인덱스를 타지 못할 수 있다
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase); // like '%s{v}%' 인덱스를 타지 못할 수 있다
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase); // like '%s{v}%' 인덱스를 타지 못할 수 있다

    }
}