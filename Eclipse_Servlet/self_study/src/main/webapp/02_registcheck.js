function register(){
	if(document.info.name.value ==""){
		alert("이름를 입력해주세요.");
		document.info.name.focus();
		return false;
	}else if(document.info.numPre.value =="" || document.info.numPost.value ==""){
		alert("주민등록번호를 입력해주세요.");
		return false;
	}else if(isNaN(document.info.numPre.value) || isNaN(document.info.numPost.value)){
		alert("주민등록번호를 올바르게 입력해주세요.");
		return false;
	}else if(document.info.userid.value ==""){
		alert("아이디를 입력해주세요.");
		document.info.userid.focus();
		return false;
	}else if(document.info.userpwd.value ==""){
		alert("비밀번호를 입력해주세요.");
		document.info.userpwd.focus();
		return false;
	}else if(document.info.userpwd.value != document.info.userpwdcheck.value){
		alert("비밀번호가 일치하지 않습니다.");
		document.info.userpwd.focus();
		return false;
	}else if(document.info.useremailID.value ==""){
		alert("이메일을 입력해주세요.");
		document.info.useremailID.focus();
		return false;
	}else if(document.info.addrNum.value ==""){
		alert("우편번호를 입력해주세요.");
		document.info.addrNum.focus();
		return false;
	}else if(document.info.addr1.value =="" || document.info.addr2.value ==""){
		alert("주소를 입력해주세요.");
		return false;
	}else if(document.info.phone.value ==""){
		alert("핸드폰 번호를 입력해주세요.");
		document.info.phone.focus();
		return false;
	}else if(document.info.permission.value ==""){
		alert("메일 수신 여부를 체크해주세요");
		return false;
	}
	else{
		return true;
	}
}