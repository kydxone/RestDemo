package com.example.restdemotwo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Author: wjianjun on 2017-03-12.
 * Email: wangjianjun202@qq.com
 */

public interface Githubapi {

    @GET("repos/{owner}/{repo}/contributors")
    Call<ResponseBody> getcall(@Path("owner") String owner, @Path("repo") String repo);

}
