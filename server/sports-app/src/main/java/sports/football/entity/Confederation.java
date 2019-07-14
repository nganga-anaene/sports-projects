package sports.football.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the confederation database table.
 * 
 */
@Entity
@NamedQuery(name="Confederation.findAll", query="SELECT c FROM Confederation c")
public class Confederation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String abb;

	private String name;

	@Column(name="region_name")
	private String regionName;

	//bi-directional many-to-one association to Association
	@OneToMany(mappedBy="confederation")
	private List<Association> associations;

	public Confederation() {
	}

	public Confederation(int id, String name, String regionName, String abb) {
		this.id = id;
		this.name = name;
		this.regionName = regionName;
		this.abb = abb;
	}
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAbb() {
		return this.abb;
	}

	public void setAbb(String abb) {
		this.abb = abb;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegionName() {
		return this.regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public List<Association> getAssociations() {
		return this.associations;
	}

	public void setAssociations(List<Association> associations) {
		this.associations = associations;
	}

	public Association addAssociation(Association association) {
		getAssociations().add(association);
		association.setConfederation(this);

		return association;
	}

	public Association removeAssociation(Association association) {
		getAssociations().remove(association);
		association.setConfederation(null);

		return association;
	}
	
	@Override
	public String toString() {
		return String.format("Confederation[id=%d, name=%s, region name=%s, abb=%s]", 
				id, name, regionName, abb);
	}

}