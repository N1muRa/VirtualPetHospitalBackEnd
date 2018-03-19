package com.hospital.entity;

import javax.persistence.*;

/**
 * Created by WangCheng on 2018/3/19.
 */

@Entity
public class Medicine {
    private int id;
    private String medicineName;
    private Double medicinePrice;
    private Integer medicineType;
    private String description;

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "medicine_name", nullable = true)
    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    @Basic
    @Column(name = "medicine_price", nullable = true)
    public Double getMedicinePrice() {
        return medicinePrice;
    }

    public void setMedicinePrice(Double medicinePrice) {
        this.medicinePrice = medicinePrice;
    }

    @Basic
    @Column(name = "medicine_type", nullable = true)
    public Integer getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(Integer medicineType) {
        this.medicineType = medicineType;
    }

    @Basic
    @Column(name = "desciption", nullable = true)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (! (o instanceof Medicine) ) return false;

        final Medicine medicine = (Medicine) o;

        if (medicine.getId() != getId()) return false;
        if (!medicine.getMedicineName().equals(getMedicineName())) return false;
        if (!medicine.getMedicinePrice().equals(getMedicinePrice())) return false;
        if (!medicine.getMedicineType().equals(getMedicineType())) return false;
        if (!medicine.getDescription().equals(getDescription())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 29 * result + (medicineName != null ? medicineName.hashCode() : 0);
        result = 29 * result + (medicinePrice != null ? medicinePrice.hashCode() : 0);
        result = 29 * result + (medicineType != null ? medicineType.hashCode() : 0);
        result = 29 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
