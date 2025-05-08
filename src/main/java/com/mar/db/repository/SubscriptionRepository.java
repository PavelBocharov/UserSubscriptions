package com.mar.db.repository;

import com.mar.db.entity.Subscriptions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscriptions, Long> {
}
