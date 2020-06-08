package com.example.gamecalculator.satisfactory.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.gamecalculator.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SatisfactoryItem#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SatisfactoryItem extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Variablen
    private DatabaseReference mDatabase;

    private EditText category;
    private EditText title;
    private CheckBox isOre;
    private Button submit;

    public SatisfactoryItem() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ecoItem.
     */
    // TODO: Rename and change types and number of parameters
    public static SatisfactoryItem newInstance(String param1, String param2) {
        SatisfactoryItem fragment = new SatisfactoryItem();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_satisfactory_item, container, false);

        FirebaseApp.initializeApp(this.getContext());
        mDatabase = FirebaseDatabase.getInstance().getReference();

        submit = (Button) v.findViewById(R.id.successForm);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = v.findViewById(R.id.categorie);
                title = v.findViewById(R.id.itemName);
                isOre = v.findViewById(R.id.isOre);
                writeItem();
            }
        });

        return v;
    }

    private void writeItem() {
        String categoryS = category.getText().toString();
        String titleS = title.getText().toString();
        String isOreS;
        if (isOre.isChecked()) {
            isOreS = "true";
        } else {
            isOreS = "false";
        }

        mDatabase.child("satisfactory").child(categoryS).setValue(titleS);
        mDatabase.child("satisfactory").child(categoryS).setValue(isOreS);
    }
}