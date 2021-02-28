package com.project.retroboard.service;

import com.project.retroboard.model.Comment;
import com.project.retroboard.repo.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Service
@Transactional(readOnly = true)
public class CommentService {
    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);

    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Comment> saveAll(List<Comment> commentList){
        logger.info("saving ", commentList);
       return commentRepository.saveAll(commentList);
    }

    public List<Comment> getCommentsForToday(){
        LocalDate localDate = LocalDate.now();
        return commentRepository.findByCreatedYearAndMonthAndDay(localDate.getYear(),
                localDate.getMonth().getValue(), localDate.getDayOfMonth());
    }
}
