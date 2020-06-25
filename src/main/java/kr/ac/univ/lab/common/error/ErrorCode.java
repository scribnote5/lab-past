package kr.ac.univ.lab.common.error;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400, "C001", "The input value is invalid."),
    INVALID_TYPE_VALUE(400, "C002", " Invalid Type Value"),
    METHOD_NOT_ALLOWED(405, "C003", " The Method is not allowed"),
    HANDLE_ACCESS_DENIED(403, "C004", "Access is Denied"),
    INTERNAL_SERVER_ERROR(500, "C005", "Internal Server Error"),
    
    
    // File
    FILE_SIZE_ERROR(500, "F001", "The file size is larger than 20 MB."),
    FILE_TYPE_ERROR(500, "F002", "The file type is supposed to dangerous and malicious."),

    // BussinessLogic
    // Member
    MEMBER_ID_DUPLICATION(400, "M001", "The member Id is duplicatied"),
    LOGIN_INPUT_INVALID(400, "M002", "The login input is invalid"),
    ANONYMOUS_USER_ACCESS_DENIED(400, "M003", "Annonymous user access is denied."),
    USER_ACCESS_DENIED(400, "M004", "User access is denied."),
    ;
	
    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }


}