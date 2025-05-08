package com.mar.api;

import com.mar.api.dto.SubscriptionsDto;
import com.mar.service.SubscriptionService;
import com.mar.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
public class SubscriptionApi {

    private final SubscriptionService subscriptionService;
    private final UserService userService;

    /**
     * Создание подписки (если необходимо) и добавление его существующему пользователю.
     *
     * @param userId id пользователя
     * @param dto    данные подписки.
     * @return информация по подписке.
     */
    @PostMapping("/users/{id}/subscriptions")
    public SubscriptionsDto addSubscription(@PathVariable("id") Long userId, @RequestBody SubscriptionsDto dto) {
        log.debug("Add subscription {} to user with id = {}", dto, userId);
        SubscriptionsDto createdDto = subscriptionService.create(dto);
        subscriptionService.link(userId, createdDto.getId());
        return createdDto;
    }

    /**
     * Получение всех подписок пользователя.
     *
     * @param userId if пользователя.
     * @return список подписок.
     */
    @GetMapping("/users/{id}/subscriptions")
    public List<SubscriptionsDto> getSubscription(@PathVariable("id") Long userId) {
        log.debug("Get user's subscriptions by user id = {}", userId);
        return subscriptionService.getByUserId(userId);
    }

    /**
     * Удалить подписку у пользователя.
     *
     * @param userId id пользователя.
     * @param subId  id подписки.
     */
    @DeleteMapping("/users/{id}/subscriptions/{sub_id}")
    public void getSubscription(@PathVariable("id") Long userId, @PathVariable("sub_id") Long subId) {
        log.debug("Delete subscriptions with id {} from user with id = {}", subId, userId);
        subscriptionService.removeLink(userId, subId);
    }

    /**
     * ТОП-3 популярных подписок.
     *
     * @return список из трех подписок с максимальным кол-вом пользователей.
     */
    @GetMapping("/subscriptions/top")
    public List<SubscriptionsDto> getTop3Subs() {
        return subscriptionService.getTop3Subscription();
    }

}
