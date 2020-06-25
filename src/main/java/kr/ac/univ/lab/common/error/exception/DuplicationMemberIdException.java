package kr.ac.univ.lab.common.error.exception;

import kr.ac.univ.lab.common.error.ErrorCode;

public class DuplicationMemberIdException extends BusinessException {
    public DuplicationMemberIdException() {
    	super(ErrorCode.MEMBER_ID_DUPLICATION);
    }
    
}