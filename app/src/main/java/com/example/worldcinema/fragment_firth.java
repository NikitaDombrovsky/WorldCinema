package com.example.worldcinema;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;


public class fragment_firth extends Fragment {
    TextView Profile_Text_Name, Profile_Text_Email;
    Button Profile_Button_Edit,  Profile_Button_History, Profile_Button_Setting, Profile_Button_Exit;
    RelativeLayout Profile_Button_Discussion;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_third, container,false);


        Profile_Button_Exit = v.findViewById(R.id.Profile_Button_Exit);

        Profile_Button_Exit.setOnClickListener(this);

        Profile_Button_Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //requireActivity().finish();
                getActivity().finish();
            }
        });
        @Override
        public void onClick(View view) {
            getActivity().finish();
        }

        return inflater.inflate(R.layout.fragment_firth, container, false);

    }

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