package pl.put.poznan.sk.irc.model;

import java.util.ArrayList;

public class Data {
    private boolean ready = true;
    private int mode;
    private int nodeMode;
    private int functionMode;
    private int nodeQuantity;
    private ArrayList<Double> nodeList;
    private double sInterval;
    private double eInterval;
    private ArrayList<Double> params;

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public int getFunctionMode() {
        return functionMode;
    }

    public void setFunctionMode(int functionMode) {
        this.functionMode = functionMode;
    }

    public int getNodeQuantity() {
        return nodeQuantity;
    }

    public void setNodeQuantity(int nodeQuantity) {
        this.nodeQuantity = nodeQuantity;
    }

    public int getNodeMode() {
        return nodeMode;
    }

    public void setNodeMode(int nodeMode) {
        this.nodeMode = nodeMode;
    }

    public ArrayList<Double> getParams() {
        return params;
    }

    public void setParams(ArrayList<Double> params) {
        this.params = params;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public ArrayList<Double> getNodeList() {
        return nodeList;
    }

    public void setNodeList(ArrayList<Double> nodeList) {
        this.nodeList = nodeList;
    }

    public double getsInterval() {
        return sInterval;
    }

    public void setsInterval(double sInterval) {
        this.sInterval = sInterval;
    }

    public double geteInterval() {
        return eInterval;
    }

    public void seteInterval(double eInterval) {
        this.eInterval = eInterval;
    }
}
