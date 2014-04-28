package controllers;

import play.mvc.*;

public class Application extends Controller {

	public static Result index() {
		return redirect(routes.IngresscoreController.listIngresscores());
	}

//	public static void login(String login, String password) {
//		User user = User.find("byLoginAndPassword", login, password).
//				first();
//		notFoundIfNull(user);
//		session.put("login", user.login);
//	}
}
