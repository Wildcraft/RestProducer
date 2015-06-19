package org.wildcraft.framework;

import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Narendran Solai on 5/19/15.
 */
public class BaseBusinessObject implements Serializable{

    private static DataClassification dataClassification;
    private Date createdDate;
    private String createdBy;
    private Date changedDate;
    private String changedBy;

    @Override
    public String toString() {
        return "BaseBusinessObject{" +
                "dataClassification=" + dataClassification +
                ", createdDate=" + createdDate +
                ", createdBy='" + createdBy + '\'' +
                ", changedDate=" + changedDate +
                ", changedBy='" + changedBy + '\'' +
                '}';
    }

    public static void setDataClassification(DataClassification dataClassificationValue) {
        dataClassification = dataClassificationValue;
    }

    public static DataClassification getDataClassification() {
        return dataClassification;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getChangedDate() {
        return changedDate;
    }

    public void setChangedDate(Date changedDate) {
        this.changedDate = changedDate;
    }

    public String getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }

}
