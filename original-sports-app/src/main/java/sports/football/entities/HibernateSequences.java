/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.football.entities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lee
 */
@Entity
@Table(name = "hibernate_sequences")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HibernateSequences.findAll", query = "SELECT h FROM HibernateSequences h")
    , @NamedQuery(name = "HibernateSequences.findBySequenceName", query = "SELECT h FROM HibernateSequences h WHERE h.sequenceName = :sequenceName")
    , @NamedQuery(name = "HibernateSequences.findByNextVal", query = "SELECT h FROM HibernateSequences h WHERE h.nextVal = :nextVal")})
public class HibernateSequences implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "sequence_name")
    private String sequenceName;
    @Column(name = "next_val")
    private BigInteger nextVal;

    public HibernateSequences() {
    }

    public HibernateSequences(String sequenceName) {
        this.sequenceName = sequenceName;
    }

    public String getSequenceName() {
        return sequenceName;
    }

    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName;
    }

    public BigInteger getNextVal() {
        return nextVal;
    }

    public void setNextVal(BigInteger nextVal) {
        this.nextVal = nextVal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sequenceName != null ? sequenceName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HibernateSequences)) {
            return false;
        }
        HibernateSequences other = (HibernateSequences) object;
        if ((this.sequenceName == null && other.sequenceName != null) || (this.sequenceName != null && !this.sequenceName.equals(other.sequenceName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sports.football.entities.HibernateSequences[ sequenceName=" + sequenceName + " ]";
    }
    
}
