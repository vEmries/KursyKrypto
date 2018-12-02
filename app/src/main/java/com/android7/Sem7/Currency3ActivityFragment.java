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
public class Currency3ActivityFragment extends Fragment {

    private TextView valueJPYToBTC;
    private TextView valueJPYToETH;

    public Currency3ActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currency3, container, false);

        valueJPYToBTC = (TextView) view.findViewById(R.id.valueJPYToBTC);
        valueJPYToBTC.setText(CurrencyData.JPY_BTC.toString());
        valueJPYToETH = (TextView) view.findViewById(R.id.valueJPYToETH);
        valueJPYToETH.setText(CurrencyData.JPY_ETH.toString());

        return view;
    }
}
