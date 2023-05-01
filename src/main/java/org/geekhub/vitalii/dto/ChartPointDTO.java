package org.geekhub.vitalii.dto;

import java.math.BigDecimal;
import java.util.Calendar;

public class ChartPointDTO {

    private Calendar date;
    private BigDecimal value;

    public ChartPointDTO() {
    }

    public Calendar getDate() {
        return date;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
