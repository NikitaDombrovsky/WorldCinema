package com.example.worldcinema;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.worldcinema.network.ApiHandler;
import com.example.worldcinema.network.models.LoginBody;
import com.example.worldcinema.network.models.LoginResponse;
import com.example.worldcinema.network.models.Profile.ProfileResponse;
import com.example.worldcinema.network.models.User.UserBody;
import com.example.worldcinema.network.models.User.UserResponse;
import com.example.worldcinema.network.service.ApiService;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class fragment_firth extends Fragment {

    private SharedPreferences sharedPreferences;
    private static final String TAG = "ProfileFragment";
    private ApiService service = ApiHandler.getInstance().getService();
    private String token = "603141";
    private TextView Profile_Text_Name, Profile_Text_Email;
    RelativeLayout Profile_Button_Discussion, Profile_Button_History, Profile_Button_Setting;
    Button Profile_Button_Edit, Profile_Button_Exit;
    ImageView Profile_Img;
    View v;
    public fragment_firth() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getContext().getSharedPreferences("token", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_firth, container,false);


        Profile_Text_Name = v.findViewById(R.id.Profile_Text_Name);
        Profile_Text_Email = v.findViewById(R.id.Profile_Text_Email);
        Intent intent = new Intent(getContext(), AuthorizationScreen.class);
        Intent intent1 = new Intent(getContext(), DiscussionActivity.class);
        Profile_Button_Exit = v.findViewById(R.id.Profile_Button_Exit);
       // MaterialButton Button = v.findViewById(R.id.Profile_Button_Exit);
        Profile_Button_Discussion = v.findViewById(R.id.Profile_Button_Discussion);
        Profile_Button_History = v.findViewById(R.id.Profile_Button_History);
        Profile_Button_Setting = v.findViewById(R.id.Profile_Button_Setting);
        Button Button = v.findViewById(R.id.Profile_Button_Exit);
        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences.edit().remove("token").commit();
                startActivity(intent);
            }
        });
        Profile_Button_Discussion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "!", Toast.LENGTH_SHORT).show();
                startActivity(intent1);
            }
        });
        Profile_Button_History.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "?", Toast.LENGTH_SHORT).show();
            }
        });
        Profile_Button_Setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "-", Toast.LENGTH_SHORT).show();
            }
        });
        getUserInfo();
        return v;
        //return inflater.inflate(R.layout.fragment_firth, container, false);

    }

    private void getUserInfo(){
        AsyncTask.execute(() ->{
            service.getData(token).enqueue(new Callback<List<ProfileResponse>>() {
                @Override
                public void onResponse(Call<List<ProfileResponse>> call, Response<List<ProfileResponse>> response) {

                    Profile_Text_Email.setText(response.body().get(0).getEmail());

                    Profile_Text_Name.setText(response.body().get(0).getFirstName() + " " + response.body().get(0).getLastName());
                    //Picasso.with(getContext()).load("http://cinema.areas.su/up/images/" + response.body().get(0).getAvatar()).into(Profile_Img);
                }

                @Override
                public void onFailure(Call<List<ProfileResponse>> call, Throwable t) {
                    Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        });
    }
//        ApiHandler.getInstance().getService().getUser().enqueue(new Callback<List<UserBody>>() {
//            @Override
//            public void onResponse(Call<List<UserBody>> call, Response<List<UserBody>> response) {
//                UserBody user = (UserBody) response.body();
//                //Profile_Text_Name.append(user.getFirstName());
//                String str = response.body().getToken();
//                Profile_Text_Name.append(str);
//            }
//
//            @Override
//            public void onFailure(Call<List<UserBody>> call, Throwable t) {
//                Profile_Text_Name.append("Хуйню спарсил");
//                t.printStackTrace();
//            }
//        });
        //Profile_Button_Exit.setOnClickListener(this);

//        Profile_Button_Exit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                //requireActivity().finish();
//                getActivity().finish();
//            }
//        });
//        @Override
//        public void onClick(View view) {
//            getActivity().finish();
//        }






//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public fragment_firth() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment fragment_firth.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static fragment_firth newInstance(String param1, String param2) {
//        fragment_firth fragment = new fragment_firth();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_firth, container, false);
//    }
}