package com.project.retroboard.repo;

import com.project.retroboard.model.Comment;
import com.project.retroboard.model.CommentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepoTest {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void findByCreatedYearAndMonthAndDay_HappyPath_ShouldReturn1Comment(){
        //given
        Comment comment = new Comment();
        comment.setComment("Test");
        comment.setCommentType(CommentType.PLUS);
        comment.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        testEntityManager.persist(comment);
        testEntityManager.flush();

        //when
        LocalDate localDate = LocalDate.now();
        List<Comment> commentList = commentRepository.findByCreatedYearAndMonthAndDay(localDate.getYear(),
                localDate.getMonth().getValue(),localDate.getDayOfMonth());

        //then
        assertThat(commentList).hasSize(1);
        assertThat(commentList.get(0)).hasFieldOrPropertyWithValue("comment","Test");
    }
    @Test
    public void save_HappyPath_Save1Comment(){
        //given
        Comment comment = new Comment();
        comment.setComment("Test");
        comment.setCommentType(CommentType.PLUS);
        comment.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        //when
        Comment savedComment=commentRepository.save(comment);

        //then
        assertThat(testEntityManager.find(Comment.class, savedComment.getId())).isEqualTo(savedComment);
    }
}
