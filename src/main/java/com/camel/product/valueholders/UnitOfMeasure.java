package com.camel.product.valueholders;

import com.camel.common.object.BaseObject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "unit_of_measure")
public class UnitOfMeasure extends BaseObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Enumerated(EnumType.STRING)
    @Column(name = "item_unit")
    private ITEMUNIT unit;
    @Column(name = "unit_price")
    private double unitPrice;
    @OneToOne(mappedBy = "itemUnit")
    private Product product;

    public UnitOfMeasure(){}

    public int getId() {
        return id;
    }



    public void setId(int id) {
        this.id = id;
    }



    public ITEMUNIT getUnit() {
        return unit;
    }



    public void setUnit(ITEMUNIT unit) {
        this.unit = unit;
    }



    public double getUnitPrice() {
        return unitPrice;
    }



    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }



    public enum ITEMUNIT{
        KILLOGRAM("killo","kg"),PAIR("Pair","pair");

        private String name;
        private String display;
        ITEMUNIT(String name,String dispaly){
            this.display=dispaly;
            this.name=name;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getDisplay() {
            return display;
        }
        public void setDisplay(String display) {
            this.display = display;
        }

        
    }

    
}
