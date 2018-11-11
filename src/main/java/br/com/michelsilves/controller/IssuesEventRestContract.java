package br.com.michelsilves.controller;

import br.com.michelsilves.model.IssuesEvent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "Octo Events API", description = "Webhook for Github Issues Events")
public interface IssuesEventRestContract {

    @ApiOperation(
            value = "Store new issues events from github",
            notes = "The post endpoint to store new issues events from Github. This endpoint is a webhook to do it.",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PostMapping(path = "/events",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> registryEvent(@RequestBody IssuesEvent event);


    @ApiOperation(
            value = "Get issues events recorded",
            notes = "This get endpoint bring the issues events recordeds by thie webhook endpoint.",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @GetMapping(path = "/issues/{issueNumber}/events", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<IssuesEvent>> getEvent(@PathVariable("issueNumber") Long issueNumber);
}
