function loginCheck(){
	if(document.frm.id.value.length == 0){
		alert("아이디를 입력해주세요.");
		frm.id.focus();
		return false;
	}
	if(document.frm.pass.value == ""){
		alert("암호를 입력해주세요.");
		frm.pass.focus();
		return false;
	}
	console.log("로그인 체크 완료");
	return true;
}
