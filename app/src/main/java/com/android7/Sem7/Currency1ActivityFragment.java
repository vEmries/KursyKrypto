package com.android7.Sem7;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android7.constrainlayoutexample.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class Currency1ActivityFragment extends Fragment {

    private TextView valuePLNToBTC;
    private TextView valuePLNToETH;

    public Currency1ActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currency1, container, false);

        valuePLNToBTC = (TextView) view.findViewById(R.id.valuePLNToBTC);
        valuePLNToBTC.setText(CurrencyData.PLN_BTC.toString());
        valuePLNToETH = (TextView) view.findViewById(R.id.valuePLNToETH);
        valuePLNToETH.setText(CurrencyData.PLN_ETH.toString());

        return view;
    }
}
