package koalarange.controller.perfil;

import io.swagger.annotations.Api;
import koalarange.integration.entity.Post;
import koalarange.integration.entity.Profile;
import koalarange.integration.repository.ProfileRepository;
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

@Api("Profiles")
@RequestMapping("/profiles")
@RestController
public class ProfilesController {

    private final static Logger log = Logger.getLogger(ProfilesController.class.getName());
    private final static String LOG_PREFIX = "[PROFILE-CONTROLLER]";

    @Autowired
    ProfileRepository profileRepository;

    @CrossOrigin(origins = "http://localhost:8100")
    @RequestMapping(value = "/find-all", method = RequestMethod.GET, produces = "application/json")
    public List<Profile> findAll() {

        List<Profile> result = new ArrayList<>();
        try {
            result = profileRepository.findAll();
        } catch (Exception e) {
            log.severe("-[EXCEPTION]-[" + e.getMessage() + "]");
        }

        return result;
    }

    @CrossOrigin(origins = "http://localhost:8100")
    @RequestMapping(value = "/find-one", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Profile> findOne(@RequestParam Integer id) {

        Profile result = new Profile();
        try {
            result = profileRepository.findOne(id);
        } catch (Exception e) {
            log.severe(LOG_PREFIX + "-[EXCEPTION]-[" + e.getMessage() + "]");
        }

        return ResponseEntity.ok(result);
    }

    @CrossOrigin(origins = "http://localhost:8100")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(@RequestBody Profile profile, @RequestHeader HttpHeaders headers, HttpServletRequest httpRequest) {

        Profile newProfile = profile;

        try {
            profileRepository.save(profile);
            return ResponseEntity.ok(newProfile);
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
            profileRepository.delete(id);
        } catch (Exception e) {
            log.severe(LOG_PREFIX + "-[EXCEPTION]-[" + e.getMessage() + "]");
        }
    }
}
