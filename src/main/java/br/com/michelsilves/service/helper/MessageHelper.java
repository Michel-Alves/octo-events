package br.com.michelsilves.service.helper;

public class MessageHelper {
    public final static String EVENT_NOT_SAVED_MESSAGE = "Event couldn't be saved";
    public final static String COULD_NOT_GET_THE_EVENTS_MESSAGE = "Could not get the events";

    public final static String NOT_NULL_EVENT_REQUIRED_REASON = "A not null Event is required";
    public final static String FIELD_REQUIRED_REASON = "The field '%s' is required";
    public final static String
            FIELD_NUMBER_RANGE_DOMAIN_REQUIRED_REASON =
                                        "Invalid issue number - " +
                                                "only positive and non-empty integer values are accepted";

    public static String formatMessage(String message, String param) {
        return String.format(message, param);
    }

}
