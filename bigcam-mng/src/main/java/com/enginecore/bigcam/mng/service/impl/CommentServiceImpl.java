package com.enginecore.bigcam.mng.service.impl;

import com.enginecore.bigcam.core.dao.CommentDao;
import com.enginecore.bigcam.core.dao.UserPraiseCommentDao;
import com.enginecore.bigcam.dto.beans.Comment;
import com.enginecore.bigcam.mng.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yyam on 14-11-19.
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private UserPraiseCommentDao userPraiseCommentDao;
    @Override
    public List<Comment> refresh(Integer userId, Integer videoId, Integer limit) {
        List<Comment> list = commentDao.refresh(videoId, limit);
        praised(list, userId);
        return list;
    }
    @Override
    public List<Comment> load(Integer userId, Integer videoId, Integer commentId, Integer limit) {
        List<Comment> list = commentDao.load(videoId, commentId, limit);
        praised(list, userId);
        return list;
    }

    private void praised(List<Comment> comments, Integer userId) {
        if (userId == null) {
            return;
        }
        for (Comment comment : comments) {
            comment.setPraised(userPraiseCommentDao.praised(userId, comment.getCommentId()));
        }
    }

    @Override
    public void praise(Integer userId, Integer commentId) {
        if (userId != null) {
            userPraiseCommentDao.praise(userId, commentId);
        }
        commentDao.praise(commentId, userId);
    }

    @Override
    public void unPraise(Integer userId, Integer commentId) {
        commentDao.unPraise(commentId, userId);
        userPraiseCommentDao.unPraise(userId, commentId);
    }
}