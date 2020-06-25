package kr.ac.univ.lab.common.error.exception;

import kr.ac.univ.lab.common.error.ErrorCode;

public class AnonymousUserException extends BusinessException {
    public AnonymousUserException() {
        super(ErrorCode.ANONYMOUS_USER_ACCESS_DENIED);
    }
    
}