package com.socmed.socmed.modules.post;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class PostServiceTest {


    @Test
    void testingnested() {
        PostFeedsResponse.comments comment1 = PostFeedsResponse.comments.builder()
                .content("aw").build();
        PostFeedsResponse.comments comment2 = PostFeedsResponse.comments.builder()
                .content("aw").build();
        Collection<PostFeedsResponse.comments> list = new ArrayList<>();
        list.add(comment1);
        list.add(comment2);
        PostFeedsResponse response = PostFeedsResponse.builder()
                .PostContent("ye")
                .comments(list.stream().toList()).build();
        System.out.println(response);
assertTrue(true);

    }

}