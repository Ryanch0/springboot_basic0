package com.apple.shop.comment;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment")
    String addComment(String username, String content, Long parentId) {
        commentService.saveComment(username, content, parentId);
        return "redirect:/detail/" + parentId;
    }



}
