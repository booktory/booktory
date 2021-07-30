package com.ssafy.booktory.service;

import com.ssafy.booktory.domain.answer.Answer;
import com.ssafy.booktory.domain.answer.AnswerRepository;
import com.ssafy.booktory.domain.answer.AnswerRequestDto;
import com.ssafy.booktory.domain.answer.AnswerResponseDto;
import com.ssafy.booktory.domain.club.Club;
import com.ssafy.booktory.domain.club.ClubRepository;
import com.ssafy.booktory.domain.question.Question;
import com.ssafy.booktory.domain.question.QuestionRepository;
import com.ssafy.booktory.domain.question.QuestionRequestDto;
import com.ssafy.booktory.domain.question.QuestionResponseDto;
import com.ssafy.booktory.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final ClubRepository clubRepository;

    @Transactional
    public List<QuestionResponseDto> getQuestionAnswers(Long clubId) {
        List<QuestionResponseDto> questionResponseDtos = new ArrayList<>();
        List<Question> questions = questionRepository.findByClubId(clubId);
        questions.forEach(question -> {
            List<AnswerResponseDto> answerResponseDtos = answerRepository.findByQuestionId(question.getId()).stream()
                    .map(answer -> new AnswerResponseDto(
                            answer.getUser().getId(),
                            answer.getUser().getNickname(),
                            answer.getUser().getProfileImg(),
                            answer.getContents(),
                            question.getIsOpen()))
                    .collect(Collectors.toList());

            questionResponseDtos.add(new QuestionResponseDto(
                    question.getId(),
                    question.getUser().getId(),
                    question.getUser().getNickname(),
                    question.getUser().getProfileImg(),
                    question.getContents(),
                    question.getIsOpen(),
                    answerResponseDtos));
        });

        return questionResponseDtos;
    }

    public Question registerQuestion(User user, Long clubId, QuestionRequestDto questionRequestDto) {
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 클럽입니다."));
        Question question = Question.builder()
                .user(user)
                .club(club)
                .contents(questionRequestDto.getContents())
                .isOpen(questionRequestDto.getIsOpen())
                .build();
        return questionRepository.save(question);
    }

    public Answer registerAnswer(User user, Long questionId, AnswerRequestDto answerRequestDto) {
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 질문입니다."));
        Answer answer = Answer.builder()
                .user(user)
                .question(question)
                .contents(answerRequestDto.getContents())
                .isOpen(answerRequestDto.getIsOpen())
                .build();
        return answerRepository.save(answer);
    }
}
