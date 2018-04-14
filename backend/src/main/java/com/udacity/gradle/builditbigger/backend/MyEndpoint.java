package com.udacity.gradle.builditbigger.backend;

import com.example.javajokelibrary.MyJokes;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

/** An endpoint class we are exposing */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    MyJokes myJoke = new MyJokes();
  //  String joke = myJoke.tellAHandCraftedJoke();

//    /** A simple endpoint method that takes a name and says Hi back */
//    @ApiMethod(name = "sayHi")
//    public MyBean sayHi(@Named("name") String joke) {
//        MyBean response = new MyBean();
//        response.setData("Hi, " + joke);
//
//        return response;
//    }

    @ApiMethod(name = "sayHi")
    public MyBean sayHi() {

        MyBean response = new MyBean();
      response.setData("Hi, " + myJoke.tellAHandCraftedJoke());
        return response;
    }

}
