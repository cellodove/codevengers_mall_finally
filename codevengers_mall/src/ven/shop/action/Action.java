package ven.shop.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ven.shop.command.ActionCommand;


public interface Action {
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
