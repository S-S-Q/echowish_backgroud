package com.echowish.android_backgroud.service;

import com.echowish.android_backgroud.pojo.Comment;

public interface CommentService {

    public String publishNewComment(Comment comment);

    public String deleteMyComment(Comment comment);
}
