package ru.netology.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public List<Post> all() {
        return service.all();
    }

    @GetMapping("/{id}")
    public Post getById(@PathVariable long id) throws ServiceException{
        try{
        return service.getById(id);
    } catch(NotFoundException e){
        throw new ServiceException(e.toString());
    }
    }

    @PostMapping
    public Post save(@RequestBody Post post) {
        return service.save(post);
    }

    @DeleteMapping("/{id}")
    public void removeById(@PathVariable long id)  {
        service.removeById(id);
    }

  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public class ServiceException extends Exception {

    public ServiceException(String message) {
      super(message);
    }

  }
}