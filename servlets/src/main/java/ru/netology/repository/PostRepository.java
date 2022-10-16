package ru.netology.repository;

import ru.netology.model.Post;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

// Stub
public class PostRepository {
    private static ConcurrentHashMap<Long, Post> historyMsg = new ConcurrentHashMap<>();
    private static long counterID = 0;

    public List<Post> all() {
        return historyMsg.values().stream()
                .collect(Collectors.toList());
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(historyMsg.get(id));
    }

    public Post save(Post post) {
        if (post.getId() != 0) {
            historyMsg.put(post.getId(), post);
        } else {
            counterID++;
            post.setId(counterID);
            historyMsg.put(counterID, post);
        }
        return post;
    }

    public void removeById(long id) {
        historyMsg.remove(id);
    }
}
