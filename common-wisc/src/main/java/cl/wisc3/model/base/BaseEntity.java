package cl.wisc3.model.base;

import cl.wisc3.config.JPA;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.StandardToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseEntity.class);

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @TableGenerator(name = "appSeqStore", table = "app_seq_store", pkColumnName = "APP_SEQ_NAME",
            pkColumnValue = "APP_SEQ_GEN", valueColumnName = "APP_SEQ_VALUE", initialValue = 1, allocationSize = 1)
    protected Long id;
    protected String altKey;

    //<editor-fold desc="Getters and Setters">
    @PrePersist
    public void generateAltKey() {
        this.altKey = UUID.randomUUID().toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAltKey() {
        return altKey;
    }

    public void setAltKey(String altKey) {
        this.altKey = altKey;
    }
    //</editor-fold>

    //<editor-fold desc="Methods">
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, new StandardToStringStyle());
    }
    //</editor-fold>

    public boolean save() {
        try {
            if (!JPA.em().contains(this)) {
                JPA.em().persist(this);
            } else {
                JPA.em().flush();
            }
            LOGGER.info(String.format("Modified entity %s", this));
            return true;
        } catch (Exception e) {
            LOGGER.error("Exception modifying order", e);
        }
        return false;
    }
}
