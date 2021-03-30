package com.project.forumapi.model.request;

import com.project.forumapi.model.VoteType;
import lombok.Data;

@Data
public class VoteRequest {
    private Long postId;
    private VoteType voteType;
}
