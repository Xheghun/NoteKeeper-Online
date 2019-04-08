package com.xheghun.justanotherpractice.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("save.php")
    Call<Note> saveNote(
            @Field("title") String title,
            @Field("note") String note,
            @Field("cat_color") int color
    );
}