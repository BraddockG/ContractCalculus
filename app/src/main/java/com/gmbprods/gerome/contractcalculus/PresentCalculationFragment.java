package com.gmbprods.gerome.contractcalculus;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
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
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * Created by Gerome on 10/29/2016.
 */

public class PresentCalculationFragment extends Fragment{
    private static final String EXTRA_PRICE_DIFFERENCE = "com.gmbprods.gerome.contractcalculus.value";
    private double mPriceDifference;
    private String mPriceDifferenceText;
    private Button mStartAgainButton;
    NumberFormat format = NumberFormat.getCurrencyInstance();

    public static Intent newIntent(Context packageContext, double mPriceDifference){
        Intent i = new Intent(packageContext, PresentCalculationActivity.class);
        i.putExtra(EXTRA_PRICE_DIFFERENCE, mPriceDifference);
        return i;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPriceDifference = getActivity().getIntent().getDoubleExtra(EXTRA_PRICE_DIFFERENCE, 0.0);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle onSavedInstance) {
        View v = inflater.inflate(R.layout.fragment_present_calculation, container, false);
        Resources res = getResources();


        TextView valueTextBox = (TextView)v.findViewById(R.id.value_textview);
        TextView valueTextBox2 = (TextView)v.findViewById(R.id.value_textview_2);
        mPriceDifference = (mPriceDifference < 0) ? - mPriceDifference : mPriceDifference;
        mPriceDifferenceText = format.format(mPriceDifference);

        if(mPriceDifference > 0){
            valueTextBox.setText( String.format(res.getString(R.string.present_line_two_sim_only), mPriceDifferenceText));
            valueTextBox2.setText( String.format(res.getString(R.string.present_line_three_sim_only), format.format(mPriceDifference / 24), format.format(mPriceDifference/104)));
        } else {
            valueTextBox.setText( String.format(res.getString(R.string.present_line_two_mobile_contract), mPriceDifferenceText));
            valueTextBox2.setText( String.format(res.getString(R.string.present_line_three_mobile_contract), format.format(mPriceDifference / 24), format.format(mPriceDifference/104)));
        }

        mStartAgainButton = (Button)v.findViewById(R.id.start_again);
        mStartAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getActivity().finish();
            }
        });

        return v;
    }




}
