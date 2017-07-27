package com.gmbprods.gerome.contractcalculus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.media.TransportPerformer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.Double.parseDouble;

/**
 * Created by Gerome on 10/29/2016.
 */

public class CalculateFragment extends Fragment{

    private EditText mSimOnlyCostField;
    private EditText mCostOfHandsetField;
    private EditText mContractMonthlyCostField;
    private EditText mContractUpfrontCost;
    private Button mCalculateButton;

    private String mSimOnlyCostFieldText;
    private String mCostOfHandsetFieldText;
    private String mContractMonthlyCostFieldText;
    private String mContractUpfrontCostText;


    private int contractLength = 24;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
        mSimOnlyCostField.setText("");
        mCostOfHandsetField.setText("");
        mContractMonthlyCostField.setText("");
        mContractUpfrontCost.setText("");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle onSavedInstance) {
        View v = inflater.inflate(R.layout.fragment_calculate, container, false);

        mSimOnlyCostField = (EditText)v.findViewById(R.id.sim_only_cost_box);
        
        mCostOfHandsetField = (EditText)v.findViewById(R.id.cost_of_handset_box);

        mContractMonthlyCostField = (EditText)v.findViewById(R.id.contract_monthly_cost_box);

        mContractUpfrontCost = (EditText)v.findViewById(R.id.contract_upfront_cost_box);

        mCalculateButton = (Button)v.findViewById(R.id.calculate_button);
        mCalculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mSimOnlyCostFieldText = mSimOnlyCostField.getText().toString();
                mCostOfHandsetFieldText = mCostOfHandsetField.getText().toString();
                mContractMonthlyCostFieldText = mContractMonthlyCostField.getText().toString();
                mContractUpfrontCostText = mContractUpfrontCost.getText().toString();


                if(mSimOnlyCostFieldText.isEmpty() ||
                        mCostOfHandsetFieldText.isEmpty() ||
                        mContractMonthlyCostFieldText.isEmpty()||
                        mContractUpfrontCostText.isEmpty()){

                    Toast.makeText(getContext(),
                            R.string.empty_edit_text,
                            Toast.LENGTH_SHORT).show();
                } else {
                    double value = calculateValue();

                    Intent intent = PresentCalculationFragment.newIntent(getContext(), value);
                    startActivity(intent);
                }
            }
        });


        return v;
    }

    public double calculateValue(){

        double simOnlyTotal = parseDouble(mSimOnlyCostFieldText) * contractLength;
        double handsetCost = parseDouble(mCostOfHandsetFieldText);

        double nonContractAmount = simOnlyTotal + handsetCost;

        double traditionalContractCost = parseDouble(mContractMonthlyCostFieldText) * contractLength + parseDouble(mContractUpfrontCostText);

        double difference = nonContractAmount - traditionalContractCost;

        return difference;
    }
}
