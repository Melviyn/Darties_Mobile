package com.maximeattoumani.darties_mobile.model;

/**
 * Created by melvi on 01/12/2016.
 */

public class RowAccueil {

    private String lib;
    private String info1;
    private String info2;

    public RowAccueil(String lib,String info1,String info2){
        this.lib = lib;
        this.info1 = info1;
        this.info2 = info2;
    }

    public String getLib() {
        return lib;
    }

    public void setLib(String lib) {
        this.lib = lib;
    }

    public String getInfo1() {
        return info1;
    }

    public void setInfo1(String info1) {
        this.info1 = info1;
    }

    public String getInfo2() {
        return info2;
    }

    public void setInfo2(String info2) {
        this.info2 = info2;
    }
}
