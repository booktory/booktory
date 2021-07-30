package com.ssafy.booktory.service;

import com.ssafy.booktory.domain.answer.AnswerRepository;
import com.ssafy.booktory.domain.answer.AnswerResponseDto;
import com.ssafy.booktory.domain.question.Question;
import com.ssafy.booktory.domain.question.QuestionRepository;
import com.ssafy.booktory.domain.question.QuestionResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @Transactional
    public List<QuestionResponseDto> getQuestionAnswers(Long clubId) {
        List<QuestionResponseDto> questionResponseDtos = new ArrayList<>();
        List<Question> questions = questionRepository.findByClubId(clubId);
        questions.forEach(question -> {
            List<AnswerResponseDto> answerResponseDtos = answerRepository.findByQuestionId(question.getId()).stream()
                    .map(answer -> new AnswerResponseDto(answer.getUser().getId(),
                            answer.getUser().getNickname(),
                            answer.getUser().getProfileImg(),
                            answer.getContents(),
                            question.getIsOpen()))
                    .collect(Collectors.toList());

            questionResponseDtos.add(new QuestionResponseDto(question.getUser().getId(),
                    question.getUser().getNickname(),
                    question.getUser().getProfileImg(),
                    question.getContents(),
                    question.getIsOpen(),
                    answerResponseDtos));
        });

        return questionResponseDtos;
    }
}
