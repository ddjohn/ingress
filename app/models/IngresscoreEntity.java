package models;

import java.util.List;
import play.data.validation.Constraints.*;
import play.db.ebean.*;
import javax.persistence.*;

@Entity
@Table(name="Ingresscore")
public class IngresscoreEntity extends Model {
	private static final long serialVersionUID = 1L;

	@Id
	public Long id;

	@Required
	public String agent;

	@Required
	public Long score;

	@Required
	public Long silver;

	@Required
	public Long gold;

	@Required
	public Long platinum;

	@Required
	public Long black;

	public static Finder<Long, IngresscoreEntity> find = new Finder<Long, IngresscoreEntity>(Long.class, IngresscoreEntity.class);

	public IngresscoreEntity(String agent, Long score, Long silver, Long gold, Long platinum, Long black) {
		this.agent    = agent;
		this.score    = score;
		this.silver   = silver;
		this.gold     = gold;
		this.platinum = platinum;
		this.black    = black;
	}

	public static List<IngresscoreEntity> all() {
		//return find.where().orderBy("market,fullname asc").findList();
		return find.all();
	}

	public static void create(IngresscoreEntity stock) {
		stock.save();
	}

	public static void delete(Long id) {
		find.ref(id).delete();
	}

	public static IngresscoreEntity get(Long id) {
		return find.ref(id);	}

	public static void update(Long id, IngresscoreEntity stock) {
		stock.id = id;
		stock.update();		
	}

	public static void deleteAll() {
		List<IngresscoreEntity> all = IngresscoreEntity.all();
		for(IngresscoreEntity one : all) {
			IngresscoreEntity.delete(one.id);
		}
	}
}
