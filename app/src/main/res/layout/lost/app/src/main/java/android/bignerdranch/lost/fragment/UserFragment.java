package android.bignerdranch.lost.fragment;

import android.bignerdranch.lost.R;
import android.bignerdranch.lost.activity.UserAboutOurActivity;
import android.bignerdranch.lost.activity.UserInformationActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class UserFragment extends Fragment {

    private ImageButton mBtn_user;
    private ImageButton mBtn_what;
    private ImageButton mBtn_lost;
    private ImageButton mBtn_found;
    private ImageButton mBtn_us;
    private ImageButton mBtn_soft;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_user,container,false);

        mBtn_user = (ImageButton) rootView.findViewById(R.id.btn_user);
        mBtn_what = (ImageButton) rootView.findViewById(R.id.btn_what);
        mBtn_lost = (ImageButton) rootView.findViewById(R.id.btn_lost);
        mBtn_found = (ImageButton) rootView.findViewById(R.id.btn_found);
        mBtn_us = (ImageButton) rootView.findViewById(R.id.btn_us);
        mBtn_soft = (ImageButton) rootView.findViewById(R.id.btn_soft);

        mBtn_user.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), UserInformationActivity.class);
                startActivity(intent);
            }
        });
        mBtn_what.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), UserInformationActivity.class);
                startActivity(intent);
            }
        });
        mBtn_lost.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), UserInformationActivity.class);
                startActivity(intent);
            }
        });
        mBtn_found.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), UserInformationActivity.class);
                startActivity(intent);
            }
        });
        mBtn_us.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), UserAboutOurActivity.class);
                startActivity(intent);
            }
        });
        mBtn_soft.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), UserInformationActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }




}
