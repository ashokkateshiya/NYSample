package com.nytimesexample.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Ashok on 04-08-2018.
 */

public class NYResponse<T> implements Serializable{

    private String status;
    private String copyright;
    private int num_results;
    private ArrayList<T> results;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public int getNum_results() {
        return num_results;
    }

    public void setNum_results(int num_results) {
        this.num_results = num_results;
    }

    public ArrayList<T> getResults() {
        return results;
    }

    public void setResults(ArrayList<T> results) {
        this.results = results;
    }
}
