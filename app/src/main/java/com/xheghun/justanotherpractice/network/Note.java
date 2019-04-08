package com.xheghun.justanotherpractice.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Note {
    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("title")
    private String title;

    @Expose
    @SerializedName("note")
    private String note;

    @Expose
    @SerializedName("cat_color")
    private int color;

    @Expose
    @SerializedName("date")
    private String date;

    @Expose
    @SerializedName("success")
    private Boolean success;

    @Expose
    @SerializedName("sql_error_msg")
    private String sql_error;

    public String getSql_error() {
        return sql_error;
    }

    public void setSql_error(String sql_error) {
        this.sql_error = sql_error;
    }

    @Expose
    @SerializedName("message")
    private String message;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
