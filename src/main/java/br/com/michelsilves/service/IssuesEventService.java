package br.com.michelsilves.service;


import br.com.michelsilves.model.IssuesEvent;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IssuesEventService {

    void push(IssuesEvent event);

    List<IssuesEvent> getIssuesEvent(Long issueNumber);
}
