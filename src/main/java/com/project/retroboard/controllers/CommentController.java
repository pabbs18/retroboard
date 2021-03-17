package com.project.retroboard.controllers;

import com.project.retroboard.model.Comment;
import com.project.retroboard.model.CommentType;
import com.project.retroboard.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CommentController {
    private static Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;

    @GetMapping("/get/comments")
    public List<Comment> getComments(){
        return commentService.getCommentsForToday();
    }

    @PostMapping("/create/comment")
    public ResponseEntity<String> createComment(@RequestParam(name = "plusComment", required = false) String plusComment,
                                                @RequestParam(name = "deltaComment", required = false) String deltaComment,
                                                @RequestParam(name = "starComment", required = false)String starComment){
        List<Comment> commentList = new ArrayList<>();
        if(StringUtils.isNotEmpty(plusComment)){
            commentList.add(getCommentObject(plusComment, CommentType.PLUS));
        }

        if(StringUtils.isNotEmpty(deltaComment)){
            commentList.add(getCommentObject(deltaComment,CommentType.DELTA));
        }

        if(StringUtils.isNotEmpty(starComment)){
            commentList.add(getCommentObject(starComment, CommentType.STAR));
        }

        //now persist the commentList

        if(!commentList.isEmpty()){
            logger.info("Saved", commentService.saveComments(commentList));
        }

        return ResponseEntity.ok("");
    }

    //helper method
    private Comment getCommentObject(String commentText, CommentType commentType){
        Comment comment = new Comment();
        comment.setComment(commentText);
        comment.setCommentType(commentType);
        return comment;
    }
}
