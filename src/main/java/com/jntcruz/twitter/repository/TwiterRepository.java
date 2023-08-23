package com.jntcruz.twitter.repository;

import com.jntcruz.twitter.entity.Twitter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TwiterRepository extends JpaRepository<Twitter, Long> {
}
