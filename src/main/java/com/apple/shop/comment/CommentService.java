package com.apple.shop.comment;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public void saveComment(String username, String content, Long parentId){
        Comment addComment = new Comment();
        addComment.setContent(content);
        addComment.setUsername(username);
        addComment.setParentId(parentId);
        commentRepository.save(addComment);
    }

    public List<Comment> parentId(@PathVariable Long id){
        return commentRepository.findByParentId(id);
    }
}
