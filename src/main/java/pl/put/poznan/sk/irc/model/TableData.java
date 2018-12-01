package pl.put.poznan.sk.irc.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TableData {
    private StringProperty point = new SimpleStringProperty(this,"Point");
    private StringProperty wf = new SimpleStringProperty(this,"WF");
    private StringProperty wl = new SimpleStringProperty(this,"WL");
    private StringProperty wp = new SimpleStringProperty(this,"WP");
    private StringProperty ws = new SimpleStringProperty(this,"WS");

    public  TableData(){}

    public TableData(String point, String wf, String wl, String wp, String ws){
        this.point.set(point);
        this.wf.set(wf);
        this.wl.set(wl);
        this.wp.set(wp);
        this.ws.set(ws);
    }

    public StringProperty getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point.set(point);
    }

    public StringProperty getWf() {
        return wf;
    }

    public void setWf(String wf) {
        this.wf.set(wf);
    }

    public StringProperty getWl() {
        return wl;
    }

    public void setWl(String wl) {
        this.wl.set(wl);
    }

    public StringProperty getWp() {
        return wp;
    }

    public void setWp(String wp) {
        this.wp.set(wp);
    }

    public StringProperty getWs() {
        return ws;
    }

    public void setWs(String ws) {
        this.ws.set(ws);
    }
}
