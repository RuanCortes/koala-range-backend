package koalarange.controller.posts;

import io.swagger.annotations.Api;
import koalarange.integration.entity.Category;
import koalarange.integration.entity.Post;
import koalarange.integration.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Api("Posts")
@RequestMapping("/posts")
@RestController
public class PostsController {

    private final static Logger log = Logger.getLogger(PostsController.class.getName());
    private final static String LOG_PREFIX = "[POST-CONTROLLER]";

    @Autowired
    PostsRepository postsRepository;

    @CrossOrigin(origins = "http://localhost:8100")
    @RequestMapping(value = "/find-by-filter", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Post>> findByFilter(@RequestParam Integer category, Integer range, Long duration) {

        List<Post> result = new ArrayList<>();
        try {
            Category categoria = new Category();
            categoria.setId(category);
            result = postsRepository.findByFilter(categoria, range, duration);
        } catch (Exception e) {
            log.severe(LOG_PREFIX + "-[EXCEPTION]-[" + e.getMessage() + "]");
        }

        return ResponseEntity.ok(result);
    }

    @CrossOrigin(origins = "http://localhost:8100")
    @RequestMapping(value = "/find-all", method = RequestMethod.GET, produces = "application/json")
    public List<Post> findAll() {

        List<Post> result = new ArrayList<>();
        try {
            result = postsRepository.findAll();
        } catch (Exception e) {
            log.severe(LOG_PREFIX + "-[EXCEPTION]-[" + e.getMessage() + "]");
        }

        return result;
    }

    @CrossOrigin(origins = "http://localhost:8100")
    @RequestMapping(value = "/find-one", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Post> findOne(@RequestParam Integer id) {

        Post result = new Post();
        try {
            result = postsRepository.findOne(id);
        } catch (Exception e) {
            log.severe(LOG_PREFIX + "-[EXCEPTION]-[" + e.getMessage() + "]");
        }

        return ResponseEntity.ok(result);
    }

    @CrossOrigin(origins = "http://localhost:8100")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(@RequestBody Post post, @RequestHeader HttpHeaders headers, HttpServletRequest httpRequest) {

        Post newPost = post;
        newPost.setCreateDate(new Date());

        try {
            postsRepository.save(post);
            return ResponseEntity.ok(newPost);
        } catch (Exception e) {
            log.severe(LOG_PREFIX + "-[EXCEPTION]-[" + e.getMessage() + "]");
            ResponseEntity.BodyBuilder builder = (ResponseEntity.BodyBuilder) ResponseEntity.badRequest();
            return builder.body(e.getMessage());
        }
    }

    @CrossOrigin(origins = "http://localhost:8100")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json")
    public void delete(@RequestParam Integer id) {

        try {
            postsRepository.delete(id);
        } catch (Exception e) {
            log.severe(LOG_PREFIX + "-[EXCEPTION]-[" + e.getMessage() + "]");
        }
    }
}
