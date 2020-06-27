package br.com.alura.forum.controller;

import br.com.alura.forum.annotation.ApiPageable;
import br.com.alura.forum.dto.input.CreateTopicDto;
import br.com.alura.forum.dto.input.TopicSearchDto;
import br.com.alura.forum.dto.output.TopicOutputDto;
import br.com.alura.forum.model.User;
import br.com.alura.forum.service.TopicService;
import br.com.alura.forum.validation.TopicSpamValidation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@AllArgsConstructor
@RequestMapping("/api/topics")
@RestController
public class TopicController {

    private TopicService topicService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @ApiPageable
    public Page<TopicOutputDto> getTopics(TopicSearchDto topicSearchDto, @ApiIgnore Pageable pageable) {
        return topicService.getTopics(topicSearchDto, pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TopicOutputDto createTopic(@Valid @RequestBody CreateTopicDto createTopicDto, @AuthenticationPrincipal User loggedUser) {
        return topicService.createTopic(createTopicDto, loggedUser);
    }

    @InitBinder("createTopicDto")
    public void initBinder(WebDataBinder binder, @AuthenticationPrincipal User loggedUser) {
        binder.addValidators(new TopicSpamValidation(topicService, loggedUser));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public TopicOutputDto getTopicById(@PathVariable Long id){
        return  topicService.getTopicDetails(id);
    }

}