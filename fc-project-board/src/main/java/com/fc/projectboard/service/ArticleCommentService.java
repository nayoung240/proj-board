package com.fc.projectboard.service;

import com.fc.projectboard.domain.ArticleComment;
import com.fc.projectboard.repository.ArticleCommentRepository;
import com.fc.projectboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleCommentService {
    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    @Transactional(readOnly = true)
    public List<ArticleComment> searchArticleComment(Long articleId) {
        return List.of();
    }
}


