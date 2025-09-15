package com.saeyan.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 모델을 수행하기 위한 메소드를 동일한 이름으로 접근할 수 있도록 추상메소드를 정의
public interface Action {
	public void execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException;
}
