package com.project.retroboard.service;

import com.project.retroboard.model.Comment;
import com.project.retroboard.model.CommentType;
import com.project.retroboard.repo.CommentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
public class CommentServiceTest {

    CommentService commentService;
    @MockBean
    CommentRepository commentRepository;

    @Before
   public void setUp() {
        commentService = new CommentService(commentRepository);
    }

    @Test
    public void saveAll() {
        //PREPARATION/STUB
        Comment comment = new Comment();
        comment.setComment("Test Comment");
        comment.setCommentType(CommentType.PLUS);
        comment.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        Comment comment1 = new Comment();
        comment1.setComment("Test Comment 1");
        comment1.setCommentType(CommentType.DELTA);
        comment1.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        List<Comment> commentList =Arrays.asList(comment, comment1);

        Mockito.when(commentRepository.saveAll(commentList)).thenReturn(commentList);


        //ACTION
        List<Comment> savedComments = commentService.saveComments(commentList);

        // verify if the saveAll method too is called when saveComments is called
        verify(commentRepository, times(1)).saveAll(commentList);

        //ASSERTIONS
        assertEquals("Test Comment", savedComments.get(0).getComment());
        assertEquals(CommentType.DELTA, savedComments.get(1).getCommentType());
        assertThat(savedComments).isNotEmpty();

    }

    @Test
    public void getCommentsForToday() {
        //PREPARATION/STUB
        Comment comment = new Comment();
        comment.setComment("Test Comment 2");
        comment.setCommentType(CommentType.STAR);
        comment.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        List<Comment> comments = Arrays.asList(comment);
        LocalDate localDate = LocalDate.now();
        Mockito.when(commentRepository.findByCreatedYearAndMonthAndDay(localDate.getYear(), localDate.getMonth().getValue(),localDate.getDayOfMonth()
                )).thenReturn(comments);

        //ACTION
        List<Comment> queriedList = commentService.getCommentsForToday();

        //ASSERTIONS
        assertEquals(comments,queriedList);
        verify(commentRepository, times(1)).findByCreatedYearAndMonthAndDay(localDate.getYear(), localDate.getMonth().getValue(),localDate.getDayOfMonth()
        );
    }
}