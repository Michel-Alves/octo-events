package br.com.michelsilves.service;

import br.com.michelsilves.model.IssuesEvent;
import br.com.michelsilves.repository.IssuesEventRepository;
import br.com.michelsilves.validator.IssuesEventValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class IssuesEventServiceImpl implements IssuesEventService {

    private IssuesEventValidator validator;
    private IssuesEventRepository issuesEventRepository;

    @Autowired
    public IssuesEventServiceImpl(IssuesEventValidator validator, IssuesEventRepository issuesEventRepository) {
        this.validator = validator;
        this.issuesEventRepository = issuesEventRepository;
    }

    @Override
    public void push(IssuesEvent issuesEvent) {
        validator.validate(issuesEvent);
        issuesEventRepository.save(issuesEvent);
        log.info("Event persisted");
    }

    @Override
    public List<IssuesEvent> getIssuesEvent(Long issueNumber) {
        validator.validateIfIsAValidIssueNumber(issueNumber);
        return issuesEventRepository.findAllByIssue_Id(issueNumber);
    }
}
