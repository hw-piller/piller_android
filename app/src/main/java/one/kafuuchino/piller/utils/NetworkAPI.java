package one.kafuuchino.piller.utils;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import one.kafuuchino.piller.models.Allergy;
import one.kafuuchino.piller.models.Eat;
import one.kafuuchino.piller.models.Medicine;
import one.kafuuchino.piller.models.User;
import one.kafuuchino.piller.models.UserMedicineList;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Junseok Oh on 2017-07-19.
 */

public interface NetworkAPI {

    @GET("/list")
    Call<ArrayList<UserMedicineList>> getMedicList(@Query("token") String token);

    @GET("/parse/getData")
    Call<Medicine> getMedicineInfo(@Query("token") String token, @Query("medicNum") String medicNum);

    @GET("/pharmacy/check")
    Call<ArrayList<Object>> getPharmacyList(@Query("w") String latitude, @Query("h") String longitude);

    @GET("/push/shangus")
    Call<ResponseBody> pushFirebaseToken(@Query("token") String token, @Query("fcm") String fcmToken);

    @GET("/list/eat/getData")
    Call<Eat> getEat(@Query("token") String token);

    @GET("/list/allergy/getData")
    Call<Allergy> getAllergy(@Query("token") String token);

    @POST("/auth/login")
    @FormUrlEncoded
    Call<User> login(@Field("id") String id, @Field("ps") String password);

    @POST("/auth/signup")
    @FormUrlEncoded
    Call<ResponseBody> register(@Field("id") String id, @Field("ps") String password,
                                @Field("name") String name, @Field("age") String age, @Field("sex") String sex);

    @POST("/auth/authenticate")
    @FormUrlEncoded
    Call<User> authenticateLocal(@Field("token") String token);

    @POST("/list/add")
    @FormUrlEncoded
    Call<ResponseBody> addMyMedicine(@Field("token") String token, @Field("time") String time, @Field("name") String medicineName);

    @POST("/auth/setAge")
    @FormUrlEncoded
    Call<ResponseBody> setAge(@Field("token") String token, @Field("age") String age);

//    @POST("/list/eat/update")
//    @FormUrlEncoded
//    Call<ResponseBody> updateEat(@Field("token") String token,  @Field("age") String age);

    @POST("/list/allergy/update")
    @FormUrlEncoded
    Call<ResponseBody> setAllergy(@Field("token") String token, @Field("allergy") String allergy);

    @GET("/parse/userData")
    Call<ArrayList<Medicine>> getMedicMyList(@Query("token") String token);

}
