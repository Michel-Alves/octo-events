package br.com.michelsilves.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BusinessException extends RuntimeException {

    private List<String> reasons = new ArrayList<>();

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, List<String> reasons) {
        super(message);
        this.reasons = reasons;
    }

    public BusinessException(String message, String ...reasons) {
        super(message);
        this.reasons.addAll(Arrays.asList(reasons));
    }

    public List<String> getReasons() {
        return Collections.unmodifiableList(reasons);
    }
}
