package controllers;

import play.data.Form;
import play.mvc.*;
import models.IngresscoreEntity;

public class IngresscoreController extends Controller {

	public static Result listIngresscores() {
		return ok(views.html.ingresscore.list.render("Stocks -> List", IngresscoreEntity.all(), flash("message")));
	}

	public static Result newIngresscore() {
		return ok(views.html.ingresscore.update.render("Stocks -> New", Form.form(IngresscoreEntity.class), 0L));
	}

	public static Result showIngresscore(Long id) {
		return ok(views.html.ingresscore.show.render("Stocks -> Show", IngresscoreEntity.get(id)));
	}

	public static Result createIngresscore() {
		Form<IngresscoreEntity> filledForm = Form.form(IngresscoreEntity.class).bindFromRequest();
		if(filledForm.hasErrors()) {
			return badRequest(views.html.ingresscore.update.render("Stocks -> New", filledForm, 0L));
		} 
		else {
			IngresscoreEntity.create(filledForm.get());
			flash("message", "Stock " + filledForm.get().agent + " was created!");
			return redirect(routes.IngresscoreController.listIngresscores());
		}
	}

	public static Result editIngresscore(Long id) {
		IngresscoreEntity stock = IngresscoreEntity.get(id);
		Form<IngresscoreEntity> form = Form.form(IngresscoreEntity.class).fill(new IngresscoreEntity(stock.agent, stock.color, stock.score, stock.silver, stock.gold, stock.platinum, stock.black));
		return ok(views.html.ingresscore.update.render("Stocks -> Update", form, stock.id));
	}

	public static Result updateIngresscore(Long id) {
		Form<IngresscoreEntity> filledForm = Form.form(IngresscoreEntity.class).bindFromRequest();
		if(filledForm.hasErrors()) {
			return badRequest(views.html.ingresscore.update.render("Stocks -> New", filledForm, id));
		}
		else {
			IngresscoreEntity.update(id, filledForm.get());
			flash("message", "Stock " + filledForm.get().agent + " was updated!");
			return redirect(routes.IngresscoreController.listIngresscores());
		}
	}

	public static Result deleteIngresscore(Long id) {
		String label = IngresscoreEntity.get(id).agent;
		IngresscoreEntity.delete(id);
		flash("message", "Stock " + label + " was deleted!");
		return redirect(routes.IngresscoreController.listIngresscores());
	}
}