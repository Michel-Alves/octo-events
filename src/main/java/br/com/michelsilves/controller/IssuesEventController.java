package br.com.michelsilves.controller;

import br.com.michelsilves.model.IssuesEvent;
import br.com.michelsilves.service.IssuesEventService;
import br.com.michelsilves.service.IssuesEventServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@RestController
public class IssuesEventController implements IssuesEventRestContract {

    private IssuesEventService issuesEventService;

    @Autowired
    public IssuesEventController(IssuesEventServiceImpl issuesEventService) {
        this.issuesEventService = issuesEventService;
    }

    @Override
    public ResponseEntity<Void> registryEvent(@RequestBody IssuesEvent event) {
        issuesEventService.push(event);
        Long issueNumber = event.getIssueId();
        return ResponseEntity
                .created(buildURI("/issues/" + issueNumber + "/events"))
                .build();
    }

    @Override
    public ResponseEntity<List<IssuesEvent>> getEvent(@PathVariable("issueNumber") Long issueNumber) {
        List<IssuesEvent> issuesEvent = issuesEventService.getIssuesEvent(issueNumber);
        return ResponseEntity
                    .ok(issuesEvent);
    }

    private URI buildURI(String uriPath) {
        URI uri = null;
        try {
            uri = new URI(uriPath);
        } catch (URISyntaxException e) {
            log.warn("Could not write the path to the resource after the post: " + e.getMessage());
        }
        return uri;
    }
}
