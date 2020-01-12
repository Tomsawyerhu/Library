package com.example.demo.model;

public class OtherInfo {
    private String favBook;
    private String favWriter;
    private String kind;
    private String reason;

    public OtherInfo(String favBook, String favWriter, String kind, String reason) {
        this.favBook = favBook;
        this.favWriter = favWriter;
        this.kind = kind;
        this.reason = reason;
    }
    public OtherInfo(){
        this.favBook=this.favWriter=this.kind=this.reason="Left to be finish";
    }

    public String getFavBook() {
        return favBook;
    }

    public void setFavBook(String favBook) {
        this.favBook = favBook;
    }

    public String getFavWriter() {
        return favWriter;
    }

    public void setFavWriter(String favWriter) {
        this.favWriter = favWriter;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "OtherInfo{" +
                "favBook='" + favBook + '\'' +
                ", favWriter='" + favWriter + '\'' +
                ", kind='" + kind + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
