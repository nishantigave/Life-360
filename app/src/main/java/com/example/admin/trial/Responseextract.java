package com.example.admin.trial;

/**
 * Created by Admin on 25/03/2018.
 */

public class Responseextract {
    String name,response,responser;

    public Responseextract(String name, String response, String responser) {
        this.name = name;
        this.response = response;
        this.responser = responser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponser() {
        return responser;
    }

    public void setResponser(String responser) {
        this.responser = responser;
    }
}
