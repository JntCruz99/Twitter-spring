package com.jntcruz.twitter.repository;

import com.jntcruz.twitter.entity.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepository extends JpaRepository<Feed, Long> {
}
