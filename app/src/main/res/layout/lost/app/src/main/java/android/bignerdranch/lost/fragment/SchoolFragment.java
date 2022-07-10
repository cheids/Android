package android.bignerdranch.lost.fragment;

import android.bignerdranch.lost.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class SchoolFragment extends Fragment {

    private ImageButton mAddSchool;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_school,container,false);
        return rootView;
    }
}
