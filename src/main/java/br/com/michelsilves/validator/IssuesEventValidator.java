package br.com.michelsilves.validator;

import br.com.michelsilves.exception.BusinessException;
import br.com.michelsilves.model.IssuesEvent;
import br.com.michelsilves.service.helper.MessageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class IssuesEventValidator {

    public void validate(IssuesEvent issuesEvent) {
        if(ObjectUtils.isEmpty(issuesEvent)) {
            log.error("Issue Event null or empty");
            throw new BusinessException(MessageHelper.EVENT_NOT_SAVED_MESSAGE,
                    MessageHelper.NOT_NULL_EVENT_REQUIRED_REASON);
        }

        List<String> errorReasons = assertThatAreValidFields(issuesEvent);

        if(!errorReasons.isEmpty()) {
            log.error(MessageHelper.EVENT_NOT_SAVED_MESSAGE +  " Reason: \n" + errorReasons.toString());
            throw new BusinessException(MessageHelper.EVENT_NOT_SAVED_MESSAGE,
                    errorReasons);
        }
    }

    private List<String> assertThatAreValidFields(IssuesEvent issuesEvent) {
        List<String> invalidFieldProblems = new ArrayList<>();
        if(StringUtils.isEmpty(issuesEvent.getAction())) {
            String reasonMessage = MessageHelper.formatMessage(
                    MessageHelper.FIELD_REQUIRED_REASON,
                    "action");

            invalidFieldProblems.add(reasonMessage);
        }

        if(StringUtils.isEmpty(issuesEvent.getIssue()) || ObjectUtils.isEmpty(issuesEvent.getIssueId())) {
            String reasonMessage = MessageHelper.formatMessage(
                    MessageHelper.FIELD_REQUIRED_REASON,
                    "issue");

            invalidFieldProblems.add(reasonMessage);
        }
        return invalidFieldProblems;
    }

    public void validateIfIsAValidIssueNumber(Long issueInvalidNumber) {
        if(ObjectUtils.isEmpty(issueInvalidNumber) || issueInvalidNumber < 0L) {
            throw new BusinessException(MessageHelper.COULD_NOT_GET_THE_EVENTS_MESSAGE,
                    MessageHelper.FIELD_NUMBER_RANGE_DOMAIN_REQUIRED_REASON);
        }
    }
}
