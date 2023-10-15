package com.geekster.InstagramClone.repository;

import com.geekster.InstagramClone.model.Post;
import com.geekster.InstagramClone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPostRepository extends JpaRepository<Post,Integer> {

    List<Post> findByUser(User user);
}
