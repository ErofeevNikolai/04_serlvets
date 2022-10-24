package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;


@Repository
public class PostRepository {
    private static ConcurrentHashMap<Long, Post> historyMsg = new ConcurrentHashMap<>();
    // Т.к. к переменной counterID могут обратиться одновременно из нескольких потоков нужно предусмотреть потокобезопасноть
    private static AtomicLong counterId = new AtomicLong(0);

    public List<Post> all() {
        return historyMsg.values().stream()
                .collect(Collectors.toList());
    }

    public Optional<Post> getById(long id) {
        try {
           return Optional.ofNullable(historyMsg.get(id));
        } catch (NotFoundException e) {
           e.printStackTrace();
           e.set
        }
        return null;
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            post.setId(counterId.incrementAndGet());
            historyMsg.put(post.getId(), post);
        } else {
            if (historyMsg.containsKey(post.getId())) {
                historyMsg.replace(post.getId(), post);
            } else throw new NotFoundException("Cannot update");
        }
        return post;
    }

    public void removeById(long id) {
      historyMsg.remove(id);
    }
}

