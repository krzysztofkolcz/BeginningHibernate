package chapter07.lifecycle;

import chapter07.Thing;
import org.jboss.logging.Logger;
import javax.persistence.*;
import java.util.BitSet;

@Entity
public class LifecycleThing {

    static Logger logger = Logger.getLogger(LifecycleThing.class);
    static BitSet lifecycleCalls = new BitSet();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LifecycleThing)) return false;

        LifecycleThing that = (LifecycleThing) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    private void log(String method, int index) {
        lifecycleCalls.set(index, true);
        logger.errorf("%12s: %s (%s)", method, this.getClass()
                .getSimpleName(), this.toString());
    }

    @PostLoad
    public void postLoad() {
        log("postLoad", 0);
    }
    @PrePersist
    public void prePersist() {
        log("prePersist", 1);
    }
    @PostPersist
    public void postPersist() {
        log("postPersist", 2);
    }
    @PreUpdate
    public void preUpdate() {
        log("preUpdate", 3);
    }
    @PostUpdate
    public void postUpdate() {
        log("postUpdate", 4);
    }

    @PreRemove
    public void preRemove() {
        log("preRemove", 5);
    }
    @PostRemove
    public void postRemove() {
        log("postRemove", 6);
    }
}
