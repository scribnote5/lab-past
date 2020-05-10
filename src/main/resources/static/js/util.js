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

/* 파일 용량 변환 */
function convertFileSize(fileSize) {
	var retFormat = "0";
	var s = ['bytes', 'KB', 'MB', 'GB', 'TB', 'PB'];
	var e = Math.floor(Math.log(fileSize) / Math.log(1024));
	
	if (fileSize != 0) { 
		retFormat = (fileSize / Math.pow(1024, e)).toFixed(2) + " " + s[e];
	} else {
		retFormat = fileSize + " " + s[0];
	}
	
	return retFormat;
};