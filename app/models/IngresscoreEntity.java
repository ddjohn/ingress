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
	public String color;

	public Integer level;

	@Required
	public Long score;

	@Required
	public Integer silver;

	@Required
	public Integer gold;

	@Required
	public Integer platinum;

	@Required
	public Integer black;

	public static Finder<Long, IngresscoreEntity> find = new Finder<Long, IngresscoreEntity>(Long.class, IngresscoreEntity.class);

	public IngresscoreEntity(String agent, String color, Long score, Integer silver, Integer gold, Integer platinum, Integer black) {
		this.agent    = agent;
		this.score    = score;
		this.color    = color;
		this.silver   = silver;
		this.gold     = gold;
		this.platinum = platinum;
		this.black    = black;
		this.level    = this.calculateLevel(score, silver, gold, platinum, black);
	}

	private Integer calculateLevel(Long score, Integer silver, Integer gold, Integer platinum, Integer black) {
	         if(score <       0) {return 0;}
	    else if(score <   10000) {return 1;}
		else if(score <   30000) {return 2;}
		else if(score <   70000) {return 3;}
		else if(score <  150000) {return 4;}
		else if(score <  300000) {return 5;}
		else if(score <  600000) {return 6;}
		else if(score < 1200000) {return 7;}
		else if(score < 2400000) {return 8;}
		else {
			     if(                            platinum >= 5 && black >= 1) {return 16;}
			else if(                            platinum >= 4              ) {return 15;}
			else if(                            platinum >= 3              ) {return 14;}
			else if(               gold >= 7 && platinum >= 2              ) {return 13;}
			else if(silver >= 8 && gold >= 6                               ) {return 12;}
			else if(silver >= 7 && gold >= 4                               ) {return 11;}
			else if(silver >= 6 && gold >= 3                               ) {return 10;}
			else if(silver >= 4 && gold >= 1                               ) {return  9;}
			else {return 0;}
		}
	}

	public static List<IngresscoreEntity> all() {
		return find.where().orderBy("level,score desc").findList();
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
