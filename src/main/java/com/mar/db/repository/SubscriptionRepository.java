package com.mar.db.repository;

import com.mar.db.entity.Subscriptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscriptions, Long> {

    Subscriptions findByTitle(String title);

    @Query("SELECT s FROM Subscriptions s JOIN s.users u WHERE u.id = :userId")
    List<Subscriptions> findByUserId(@Param("userId") Long userId);

    @Query(value = """
            select
                subs.*
            from subscriptions subs
            join (
            	select
            		 usj.subs_id as sid,
            		 count(usj.subs_id) as cnt
            	from users_subs_join usj
            	group by usj.subs_id
            	order by cnt desc
            ) ids on subs.id = ids.sid
            limit 3
            """,
            nativeQuery = true)
    List<Subscriptions> getTop3Subscription();

}
