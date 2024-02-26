package nl.nickwillems.koffiegrinder;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class KoffieGrinderController {
    private final KoffieGrinderService koffieGrinderService;

    public KoffieGrinderController(final KoffieGrinderService koffieGrinderService) {
        this.koffieGrinderService = koffieGrinderService;
    }

    @PostMapping("/grind")
    public ResponseEntity<Object> grindKoffie(@RequestBody final Boon boon) {
        System.out.println();
        final boolean success = koffieGrinderService.grindKoffie(boon);
        System.out.println();
        System.out.println();
        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
