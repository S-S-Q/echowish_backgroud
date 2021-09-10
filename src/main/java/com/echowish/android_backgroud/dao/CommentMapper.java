package com.echowish.android_backgroud.dao;

import com.echowish.android_backgroud.pojo.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentMapper {

    void insertNewComment(Comment comment);

    void deleteComment(Comment comment);
}
