package com.example.technoligest.lastdraft;

import com.squareup.okhttp.ResponseBody;

import java.util.List;


import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;


public interface IjsonQueries {
    //http://ooper.space/VoltaHackathon2016/api.php?type=refresh&pass=wordpass
    @GET("api.php")
    Call<List<Product>> api(@Query("type")String type,@Query("pass")String pass );
    //api.php?type=insert&pass=wordpass&name=anything&back=##&location=ssss
    @GET("api.php")
    Call<ResponseBody>  add(@Query("type")String type,@Query("pass")String pass
            ,@Query("name")String name,@Query("back")String back,@Query("location")String location );
    //api.php?type=insert&pass=wordpass&name=anything&back=##&location=ssss
    @GET("api.php")
    Call<ResponseBody> addQ(@Query("type")String type,@Query("pass")String pass,@Query("id")String id, @Query("amount")String amount );

}
