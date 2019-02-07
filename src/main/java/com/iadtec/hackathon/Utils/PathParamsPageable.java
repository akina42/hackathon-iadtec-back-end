package com.iadtec.hackathon.Utils;

public class PathParamsPageable {

    public PathParamsPageable() {
    }

    public PathParamsPageable(Integer page, Integer size, Boolean ascendent, String fieldOrderBy) {
        this.page = page;
        this.size = size;
        this.ascendent = ascendent;
        this.fieldOrderBy = fieldOrderBy;
    }

    private Integer page;

    private  Integer size;

    private Boolean ascendent;

    private String fieldOrderBy;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Boolean getAscendent() {
        return ascendent;
    }

    public void setAscendent(Boolean ascendent) {
        this.ascendent = ascendent;
    }

    public String getFieldOrderBy() {
        return fieldOrderBy;
    }

    public void setFieldOrderBy(String fieldOrderBy) {
        this.fieldOrderBy = fieldOrderBy;
    }

    public void setDefaultValuesIfNull(){
        if (this.getPage() == null) {
            this.setPage(0);
        }
        if (this.getSize() == null) {
            this.setSize(5);
        }
        if (this.getAscendent() == null) {
            this.setAscendent(true);
        }
        if (this.getFieldOrderBy() == null) {
            this.setFieldOrderBy("id");
        }
    }
}
