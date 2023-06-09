package com.demo.postgresqlspring.domain.article.service;

import com.demo.postgresqlspring.domain.article.dto.ArticleDto;
import com.demo.postgresqlspring.domain.article.entity.ArticleEntity;
import com.demo.postgresqlspring.domain.article.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    public ArticleDto<ArticleEntity> saveArticle(ArticleEntity article){
        ArticleDto<ArticleEntity> responseStructure = new ArticleDto<ArticleEntity>();

        if(article.getTitle() == "") {
            responseStructure.setData(null);
            responseStructure.setStatusCode(HttpStatus.NO_CONTENT.value());
            responseStructure.setMessage("Article has failed to save");
        }else{
            ArticleEntity article1 = articleRepository.save(article);
            responseStructure.setData(article1);
            responseStructure.setStatusCode(HttpStatus.CREATED.value());
            responseStructure.setMessage("Article saved successfully");
        }
        return responseStructure;
    }

    public ArticleDto<ArticleEntity> getArticleById(Integer id){
        ArticleDto<ArticleEntity> responseStructure = new ArticleDto<ArticleEntity>();
        ArticleEntity article = articleRepository.findByID(id);

        if(article != null) {
            responseStructure.setData(article);
            responseStructure.setStatusCode(HttpStatus.CREATED.value());
            responseStructure.setMessage("Article got by id");
        } else {
            responseStructure.setData(null);
            responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Article don't exists");
        }
        return responseStructure;
    }

    public ArticleDto<ArticleEntity> updateStudent(ArticleEntity article, Integer id){
        ArticleDto<ArticleEntity> responseStructure = new ArticleDto<ArticleEntity>();

        ArticleEntity existingarticle = articleRepository.findByID(id);

        if(existingarticle != null) {
            existingarticle.setTitle(article.getTitle());
            existingarticle.setDescription(article.getDescription());
            ArticleEntity article1 = articleRepository.save(existingarticle);
            responseStructure.setData(article1);
            responseStructure.setStatusCode(HttpStatus.CREATED.value());
            responseStructure.setMessage("Article updated successfully");
        } else {
            responseStructure.setData(null);
            responseStructure.setStatusCode(HttpStatus.CREATED.value());
            responseStructure.setMessage("Article don't exists");
        }
        return responseStructure;
    }


    public ArticleDto<String> deleteArticle(Integer id){
        ArticleDto<String> responseStructure = new ArticleDto<String>();

        ArticleEntity found = articleRepository.findByID(id);

        if (found != null){
            articleRepository.delete(found);
            responseStructure.setData("Article selected");
            responseStructure.setStatusCode(HttpStatus.OK.value());
            responseStructure.setMessage("Article deleted successfully");
        }else{
            responseStructure.setData("Article not selected");
            responseStructure.setStatusCode(HttpStatus.NO_CONTENT.value());
            responseStructure.setMessage("Article has failed to get delete");
        }
        return responseStructure;
    }

    public ArticleDto<List<ArticleEntity>> getAllArticles(){
        ArticleDto<List<ArticleEntity>> responseStructure = new ArticleDto<List<ArticleEntity>>();

        List<ArticleEntity> articles = articleRepository.findAll();
        if(articles.size() > 0) {
            responseStructure.setData(articles);
            responseStructure.setStatusCode(HttpStatus.CREATED.value());
            responseStructure.setMessage("Here are the list of all Articles");
        } else {
            responseStructure.setData(null);
            responseStructure.setStatusCode(HttpStatus.CREATED.value());
            responseStructure.setMessage("No article record exists in database");
        }
        return responseStructure;
    }

}
