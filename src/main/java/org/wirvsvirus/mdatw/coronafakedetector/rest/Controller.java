package org.wirvsvirus.mdatw.coronafakedetector.rest;

import com.google.common.hash.Hashing;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.wirvsvirus.mdatw.coronafakedetector.articles.Article;
import org.wirvsvirus.mdatw.coronafakedetector.websites.Website;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping("/checkURL")
    public ResponseEntity<RestResponse> checkURL(@RequestParam JsonRequest URL) {
        Article article = new Article();
        article.setAuthor("Some Author");
        article.setFakeProbability(68);

        Website website = new Website();
        website.setURL("example.com");
        article.setParent(website);

        return ResponseEntity.ok(new RestResponse(article));
    }

    @GetMapping("/checkPhoto")
    public ResponseEntity<RestResponse> checkPhoto(@RequestBody JsonRequest photo) {
        String base64 = (String)photo.getPhoto();
        String hash = Hashing.sha256().hashBytes(base64.getBytes()).toString();;

        return ResponseEntity.ok(new RestResponse(hash));
    }

    @GetMapping("/checkText")
    public ResponseEntity<RestResponse> checkText(@RequestBody JsonRequest text) {

        return ResponseEntity.ok(new RestResponse(text.getText()));
    }
}
