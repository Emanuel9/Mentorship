package com.orange.otheatre.service;

import com.orange.otheatre.entities.Review;
import com.orange.otheatre.model.Mark;
import com.orange.otheatre.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    public Review saveReview(Review review, String mark){
        addMark(review, mark);

        return reviewRepository.saveAndFlush(review);
    }

    private void addMark(Review review, String mark) {
        switch (Integer.parseInt(mark)){
            case 1: review.setUserScore(Mark.AWFUL);
                break;
            case 2: review.setUserScore(Mark.BAD);
                break;
            case 3: review.setUserScore(Mark.ACCEPTABLE);
                break;
            case 4: review.setUserScore(Mark.GOOD);
                break;
            case 5: review.setUserScore(Mark.EXCELLENT);
                break;
            default: review.setUserScore(Mark.ACCEPTABLE);
        }
    }
}
