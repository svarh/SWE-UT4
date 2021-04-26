package com.example.care;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GuestProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GuestProfileFragment extends Fragment {

    private TextView guestName;
    private TextView guestEmail;
    private TextView guestPhoneNumb;
    private Button accountHome;

    private GuestAccount guest;
    private UserModel userModel;

    private Activity guestHomeActivity;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GuestProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GuestProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GuestProfileFragment newInstance(String param1, String param2) {
        GuestProfileFragment fragment = new GuestProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        guest = GuestHome.getGuest();
        userModel = new UserModel(getActivity());
        userModel.hideStatusBar();


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initialize();

        accountHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_guest_profile, container, false);

    }

    // This method initializes all the needed things
    public void initialize(){
        guestName = (TextView) getView().findViewById(R.id.tvGuestName);
        guestEmail = (TextView) getView().findViewById(R.id.tvGuestEmail);
        guestPhoneNumb = (TextView) getView().findViewById(R.id.tvPhoneNumber);
        accountHome = (Button) getView().findViewById(R.id.btnAccountHome);


        guestName.setText(guest.getName());
        guestEmail.setText(guest.getEmail());
        guestPhoneNumb.setText(guest.getPhone());
    }
}