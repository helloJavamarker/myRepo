package com.test.mark.zhang.test.es.project;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="")
@XmlRootElement(name = "field")
public class Field {

    @XmlAttribute(name = "key",required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String key;

    @XmlAttribute(name = "name",required = true)
    protected String name;

    @XmlAttribute(name = "agg",required = true)
    protected String agg;

    @XmlAttribute(name = "order",required = true)
    private Integer order;

    @XmlAttribute(name = "groupName",required = true)
    private String groupName;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAgg() {
        return agg;
    }

    public void setAgg(String agg) {
        this.agg = agg;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
