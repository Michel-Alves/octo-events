package br.com.michelsilves.validator;

import br.com.michelsilves.exception.BusinessException;
import br.com.michelsilves.model.*;
import br.com.michelsilves.service.helper.MessageHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Issues Event Validator Test")
class IssuesEventValidatorTest {

    private IssuesEventValidator issuesEventValidator
            = new IssuesEventValidator();

    private static final String ACTION_REQUIRED_REASON = MessageHelper.formatMessage(
            MessageHelper.FIELD_REQUIRED_REASON,
            "action");

    private static final String ISSUE_REQUIRED_REASON = MessageHelper.formatMessage(
            MessageHelper.FIELD_REQUIRED_REASON,
            "issue");

    private static final String NOT_NULL_EVENT_REQUIRED = MessageHelper.NOT_NULL_EVENT_REQUIRED_REASON;

    private static final String EVENT_COULD_NOT_BE_SAVED = MessageHelper.EVENT_NOT_SAVED_MESSAGE;

    @Test
    @DisplayName("Given a valid event entry should not throw an exception")
    void givenValidEvent_WhenValidated_ThenShouldNotThrowException() {
        IssuesEvent validIssuesEvent =
                IssuesEvent.builder().action("someAction")
                        .issue(IssueSnapshot
                                .builder()
                                .id(1L)
                                .build())
                        .changes(ChangesSnapshot.builder().build())
                        .changesTitleFrom("someString")
                        .changesBodyFrom("someString")
                        .assignee(new GithubUserSnapshot())
                        .label(new LabelSnapshot())
                        .build();

        issuesEventValidator.validate(validIssuesEvent);
    }

    @Test
    @DisplayName("Given an invalid event when validated should throw a business exception")
    void givenInvalidEvent_WhenValidated_ThenShouldThrowBussinessException() {
        IssuesEvent invalidIssuesEvent
                = IssuesEvent.builder().build();

        BusinessException businessException = Assertions.assertThrows (
                BusinessException.class,
                () -> issuesEventValidator.validate(invalidIssuesEvent),
                "Should throw a BussinessException");

        assertAll(
                () -> assertThat(businessException.getMessage(), is(equalTo(EVENT_COULD_NOT_BE_SAVED))),
                () -> assertThat(businessException.getReasons(), hasSize(2)),
                () -> assertThat(businessException.getReasons(),
                        containsInAnyOrder(ACTION_REQUIRED_REASON, ISSUE_REQUIRED_REASON
                        ))
        );
    }


    @Test
    @DisplayName("Given a invalid null event when validated should throw a business exception")
    void givenInvalidNullEvent_WhenValidated_ThenShouldThrowBussinessException() {
        IssuesEvent nullIssuesEvent = null;

        BusinessException businessException = Assertions.assertThrows (
                BusinessException.class,
                () -> issuesEventValidator.validate(nullIssuesEvent),
                "May throw a Exception");

        assertAll(
                () -> assertThat(businessException.getMessage(), is(equalTo(EVENT_COULD_NOT_BE_SAVED))),
                () -> assertThat(businessException.getReasons(), hasSize(1)),
                () -> assertThat(businessException.getReasons(), contains(NOT_NULL_EVENT_REQUIRED))
        );
    }


    @Test
    @DisplayName("Given an issue id valid When validated Then Should Not throw Exception")
    void givenValidIssueId_WhenValidated_ThenShouldNoThrowException() {
        Long validIssueNumber = 1L;
        issuesEventValidator.validateIfIsAValidIssueNumber(validIssueNumber);
    }

    @Test
    @DisplayName("Given an ivalid issue id When validated Then Should throw a Bussiness Exception")
    void givenIvalidIssueId_WhenValidated_ThenShouldThrowBussinessException() {
        Long invalidIssueNumber = -1L;

        BusinessException businessException = Assertions.assertThrows (
                BusinessException.class,
                () -> issuesEventValidator.validateIfIsAValidIssueNumber(invalidIssueNumber),
                "May throw a Exception");

        assertAll(
                () -> assertThat(businessException.getMessage(),
                        is(equalTo(MessageHelper.COULD_NOT_GET_THE_EVENTS_MESSAGE))),
                () -> assertThat(businessException.getReasons(), hasSize(1)),
                () -> assertThat(businessException.getReasons(),
                        contains(MessageHelper.FIELD_NUMBER_RANGE_DOMAIN_REQUIRED_REASON)));
    }


}
