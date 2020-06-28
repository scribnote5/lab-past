/* 공백 문자 검사 */
function validationSpaceChar(str) { 
	if(str.search(/\s/) != -1) { 
		return true; 
	} else { 
		return false; 
	} 
}

/* 공백 문자 및 공란 검사 */
function validationEmpty(str) { 
	if(str.search(/\s/) != -1 || str.length == 0) { 
		return true; 
	} else { 
		return false; 
	} 
} 

/* 특수 문자 검사 */
function validationSpecialChar(str) { 
	var regExp = /[`~!@#$%^&*|\\\'\";:\/?]/gi;
	if(regExp.test(str) == true) { 
		return true; 
	} else { 
		return false; 
	} 
}

/* 바이트 수 반환 */
function getByteSize(el){
    var codeByte = 0;
    for (var idx = 0; idx < el.length; idx++) {
        var oneChar = escape(el.charAt(idx));
        if ( oneChar.length == 1 ) {
            codeByte ++;
        } else if (oneChar.indexOf("%u") != -1) {
            codeByte += 2;
        } else if (oneChar.indexOf("%") != -1) {
            codeByte ++;
        }
    }
    return codeByte;
}

/* input tag validation - 문자열 길이 */
function validationByLength(inputName, maxStrLength, title) { 
	var strLength = document.getElementsByName(inputName)[0].value.length;

	if(strLength > maxStrLength) { 
		alert(title + " is up to " + maxStrLength + "characters long." +
				"\n(Number of characters currently entered: " + strLength + ")");
		document.getElementsByName(inputName)[0].focus();
		
		return false; 
	} else if(validationEmpty(document.getElementsByName(inputName)[0].value)) {
		alert(title + " must not be blank.");
		document.getElementsByName(inputName)[0].focus();
		
		return false;	
	} else { 
		return true; 
	} 
}

/* input tag validation - 문자열 크기 */
function validationBySize(inputName, maxByteSize, title) { 
	var byteSize = getByteSize(document.getElementsByName(inputName)[0].value);
	
	if(byteSize > maxByteSize) { 
		alert(title + "is up to " + maxByteSize + " bytes size." +
				"\n(Size of characters currently entered: " + byteSize + " bytes)");
		document.getElementsByName(inputName)[0].focus();
		
		return false; 
	} else { 
		return true; 
	} 
}

/* 
 * 파일 validation - 필수 확장자
 *  
 * [전역 변수 선언 필요]
 * var totalFileSize = 0;
 */
function isImageFile(file) {	
	// file validation - 필수 확장자
	var includeArray = [ ".jpg", ".jpeg", ".png" ];
	// 파일 이름 
	var fileName = file.name;
	// 파일 확장자명(대문자를 소문자로 변경)
	var extensionName = fileName.substring( fileName.lastIndexOf(".")).toLowerCase();
	// 필수 확장자명 사용 여부 판단
	var result = false;
	// 첨부 파일 크기
	var fileSize = file.size;
	// 업로드 가능한 파일 크기: 20 MB                                                                                                                           
	var maxSize = 20 * 1024 * 1024;

	// 첨부 파일이 있는 경우
	if (fileName != "") {
		/* 확장자명 검사 */
		for (var j = 0; j < includeArray.length; j++) {
			if (extensionName == includeArray[j]) {
				result = true;
				break;
			}
		}

		if (!result) {			
			alert("The attached file only uses [" + includeArray.join(', ') + "] extension. ");
			$("#file").replaceWith($("#file").clone(true));
			$("#file").val('');
			
			return false;
		}

		/* 파일 크기 검사 */
		if (fileSize > maxSize) {
			alert("The attached file can upload within 20 MB size.");
			$("#file").replaceWith($("#file").clone(true));
			$("#file").val('');
			
			return false;
		}
		
		/* 모든 파일 크기 검사 */
		if(fileSize + totalFileSize > maxSize) {
			alert("All attached files must be within 20 MB size.");
			$("#file").replaceWith($("#file").clone(true));
			$("#file").val('');
			
			return false;
		} 
		
		totalFileSize += fileSize;  
	}
	
	return true;
}

/* 
 * 파일 validation - 유효한 파일 확장자 
 * 
 * [전역 변수 선언 필요]
 *  var totalFileSize = 0;
 */
function isValidFile(file) {
	// file validation - 제외 파일 확장자
	var excludeArray = [ ".exe", ".jar", ".js", ".swf", ".swf", ".bin", ".wmf", ".class", ".chm", ".pgm", ".pcx", ".hlp", ".acc", ".css", ".sh",
						 ".com", "bat", "cmd", ".scf", ".lnk", ".inf", ".reg" ];
	// 파일 이름 
	var fileName = file.name;
	// 파일 확장자명(대문자를 소문자로 변경)
	var extensionName = fileName.substring( fileName.lastIndexOf(".")).toLowerCase();
	// 첨부 파일 크기
	var fileSize = file.size;
	// 업로드 가능한 파일 크기: 20 MB                                                                                                                           
	var maxSize = 20 * 1024 * 1024;
                                                                                                                             
	if (fileName != "") {                                                                                                                          
		/* 확장자명 검사 */
		for (var i = 0; i < excludeArray.length; i++) {                                                                                           
			if (extensionName == excludeArray[i]) {                                                                                               
				alert("[" + extensionName + "] extension doesn't support uploading attached file." );
				$("#file").val('');                                                                                                          
				$("#file").replaceWith($("#file").clone(true));                                                                                    
				
				return false;                                                                                                                            
			}                                                                                                                                      
		} 

		/* 파일 크기 검사 */
		if (fileSize > maxSize) {
			alert("The attached file can upload within 20 MB size.");
			$("#file").replaceWith($("#file").clone(true));
			$("#file").val('');
			
			return false;
		}
		
		/* 모든 파일 크기 검사 */
		if(fileSize + totalFileSize > maxSize) {
			alert("All attached files must be within 20 MB size.");
			$("#file").replaceWith($("#file").clone(true));
			$("#file").val('');
			
			return false;
		} 
		
		totalFileSize += fileSize; 
		
	}
	
	return true;
}

/*
 * validation response message alert
 */
function validatioMessageAlert(msg) {
	console.log(msg);
	
	if(isEmpty(msg.errors)) {
		alert(msg.message);
	} 
	else {
		for(var i = 0; i < msg.errors.length; i++) {
			alert(msg.errors[i].reason);
		}
	}
}