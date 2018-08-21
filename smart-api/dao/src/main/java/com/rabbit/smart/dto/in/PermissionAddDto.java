package com.rabbit.smart.dto.in;

public class PermissionAddDto {
    public PermissionAddDto() {
    }

    private String code;
    private String name;
    private String parentCode;
    private String url;
    private Integer type;
    private Boolean isMenuOpen;
    private Integer menuSort;
    private String menuIcon;
    private Boolean isLog;
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getMenuOpen() {
        return isMenuOpen;
    }

    public void setMenuOpen(Boolean menuOpen) {
        isMenuOpen = menuOpen;
    }

    public Integer getMenuSort() {
        return menuSort;
    }

    public void setMenuSort(Integer menuSort) {
        this.menuSort = menuSort;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public Boolean getLog() {
        return isLog;
    }

    public void setLog(Boolean log) {
        isLog = log;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
