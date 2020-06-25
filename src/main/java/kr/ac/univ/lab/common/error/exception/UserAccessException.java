package kr.ac.univ.lab.common.error.exception;

import kr.ac.univ.lab.common.error.ErrorCode;

public class UserAccessException extends BusinessException {
    public UserAccessException() {
        super(ErrorCode.USER_ACCESS_DENIED);
    }
    
}