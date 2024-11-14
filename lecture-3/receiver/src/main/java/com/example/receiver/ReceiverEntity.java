package com.example.receiver;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;


@Entity
class ReceiverEntity {
    @Id
    Long demoId;
    String demoText;

    Integer demoValue;

    Date demoTimestamp;


    public Long getDemoId() {
        return demoId;
    }

    public void setDemoId(Long demoId) {
        this.demoId = demoId;
    }

    public String getDemoText() {
        return demoText;
    }

    public void setDemoText(String demoText) {
        this.demoText = demoText;
    }

    public Integer getDemoValue() {
        return demoValue;
    }

    public void setDemoValue(Integer demoValue) {
        this.demoValue = demoValue;
    }

    public Date getDemoTimestamp() {
        return demoTimestamp;
    }

    public void setDemoTimestamp(Date demoTimestamp) {
        this.demoTimestamp = demoTimestamp;
    }
}
