package br.com.michelsilves.service;

import br.com.michelsilves.exception.BusinessException;
import br.com.michelsilves.model.IssuesEvent;
import br.com.michelsilves.repository.IssuesEventRepository;
import br.com.michelsilves.service.helper.MessageHelper;
import br.com.michelsilves.utils.MockitoExtension;
import br.com.michelsilves.validator.IssuesEventValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
@DisplayName("Issues Event Test")
public class IssuesEventServiceTest {

    @Mock
    private IssuesEventValidator issuesEventValidator;

    @Mock
    private IssuesEventRepository issuesEventRepository;

    @InjectMocks
    private IssuesEventServiceImpl issuesEventService;

    private static final String ACTION_REQUIRED_REASON = MessageHelper.formatMessage(
            MessageHelper.FIELD_REQUIRED_REASON,
            "action");

    private static final String ISSUE_REQUIRED_REASON = MessageHelper.formatMessage(
            MessageHelper.FIELD_REQUIRED_REASON,
            "issue");

    private static final String NOT_NULL_EVENT_REQUIRED = MessageHelper.NOT_NULL_EVENT_REQUIRED_REASON;

    private static final String EVENT_COULD_NOT_BE_SAVED = MessageHelper.EVENT_NOT_SAVED_MESSAGE;

    @Test
    @DisplayName("Given a invalid null event when When Push Event_ should throw a business exception")
    void givenInvalidNullEvent_WhenPushEvent_ThenShouldThrowBussinessException() {
        IssuesEvent issuesEvent = null;

        doThrow(new BusinessException(EVENT_COULD_NOT_BE_SAVED,
                Arrays.asList(NOT_NULL_EVENT_REQUIRED)))
                .when(issuesEventValidator)
                .validate(issuesEvent);

        BusinessException businessException = assertThrows (
                BusinessException.class,
                ()-> issuesEventService.push(issuesEvent),
                "Should throw a BusinessException"
        );

        assertAll(
                () -> assertThat(businessException.getMessage(), is(equalTo(EVENT_COULD_NOT_BE_SAVED))),
                () -> assertThat(businessException.getReasons(), hasSize(1)),
                () -> assertThat(businessException.getReasons(), contains(NOT_NULL_EVENT_REQUIRED))
        );
    }

    @Test
    @DisplayName("Given an invalid event when When Push Event should throw a business exception")
    void givenInvalidEvent_WhenPushEvent_ThenShouldThrowBussinessException() {


        IssuesEvent issuesEvent = IssuesEvent.builder().build();

        doThrow(new BusinessException(EVENT_COULD_NOT_BE_SAVED,
                Arrays.asList(ISSUE_REQUIRED_REASON, ACTION_REQUIRED_REASON)))
                .when(issuesEventValidator)
                .validate(issuesEvent);

        BusinessException businessException = assertThrows (
                BusinessException.class,
                ()-> issuesEventService.push(issuesEvent),
                "Should throw a BusinessException"
        );

        assertAll(
                () -> assertThat(businessException.getMessage(), is(equalTo(EVENT_COULD_NOT_BE_SAVED))),
                () -> assertThat(businessException.getReasons(), hasSize(2)),
                () -> assertThat(businessException.getReasons(),
                        containsInAnyOrder(ACTION_REQUIRED_REASON, ISSUE_REQUIRED_REASON
                        ))
        );

    }

    @Test
    @DisplayName("Given a valid event When Push Event should not throw an exception")
    void givenValidEvent_WhenPushEvent_ThenShouldNotThrowException() {

        IssuesEvent aValidIssuesEvent = IssuesEvent.builder().build();

        issuesEventService.push(aValidIssuesEvent);

        verify(issuesEventRepository).save(aValidIssuesEvent);
    }

    @Test
    @DisplayName("Given an ivalid issue id When Get Items Then Should throw a Bussiness Exception")
    void givenIvalidIssueId_WhenGetEvents_ThenShouldThrowBussinessException() {

        Long issuesEventInvalidNumber = -1L;

        doThrow(new BusinessException(MessageHelper.COULD_NOT_GET_THE_EVENTS_MESSAGE,
                MessageHelper.FIELD_NUMBER_RANGE_DOMAIN_REQUIRED_REASON))
                .when(issuesEventValidator).validateIfIsAValidIssueNumber(issuesEventInvalidNumber);

        BusinessException businessException =
                assertThrows(BusinessException.class,
                        ()-> issuesEventService.getIssuesEvent(issuesEventInvalidNumber),
                        "May throw a Bussiness Exception");

        assertAll(
                () -> assertThat(businessException.getMessage(),
                        is(equalTo(MessageHelper.COULD_NOT_GET_THE_EVENTS_MESSAGE))),
                () -> assertThat(businessException.getReasons(), hasSize(1)),
                () -> assertThat(businessException.getReasons(),
                        contains(MessageHelper.FIELD_NUMBER_RANGE_DOMAIN_REQUIRED_REASON)));
    }

    @Test
    @DisplayName("Given an issue id valid When Get Items Then Should Not throw Exception")
    void givenValidIssueId_WhenGetEvents_ThenShouldGetTheExpectedItems() {
        Long issuesValidNumber = -1L;

        List<IssuesEvent> expectedIssuesEvent
                = Arrays.asList(IssuesEvent.builder().build());

        when(issuesEventRepository.findAllByIssue_Id(issuesValidNumber))
                .thenReturn(expectedIssuesEvent);

        List<IssuesEvent> obteinedIssuesEvents
                = issuesEventService.getIssuesEvent(issuesValidNumber);

        verify(issuesEventRepository)
                .findAllByIssue_Id(issuesValidNumber);
        assertAll(
                () -> assertThat(obteinedIssuesEvents.size(), is(equalTo(expectedIssuesEvent.size()))),
                () -> assertThat(obteinedIssuesEvents, is(equalTo(expectedIssuesEvent)))
        );

    }

}
