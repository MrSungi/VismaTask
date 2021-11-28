package com.Visma.demo.entity;


public class LibraryRegistry {

    private String client;
    private int days;

    public LibraryRegistry() {
    }

    public LibraryRegistry(String client, int days) {
        this.client = client;
        this.days = days;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

}
