package com.android7.Sem7;

import android.util.Log;
import android.widget.TextView;

import com.apptakk.http_request.HttpRequest;
import com.apptakk.http_request.HttpRequestTask;
import com.apptakk.http_request.HttpResponse;

public class CurrencyService {

    private final String EUR = "EUR,";
    private final String PLN = "PLN,";
    private final String USD = "USD,";
    private final String JPY = "JPY,";

    private final String BTC = "BTC";
    private final String ETH = "ETH";

    private final String API_BASE = "https://min-api.cryptocompare.com/data/price";
    private final String API_KEY = "{c4eb134f01e08f229e9b544f1521945267cd2c1b880d17c9a65cdc309a49e210}";

    private TextView EUR_BTC;
    private TextView EUR_ETH;

    public Boolean responseStatus = false;

//    Response body:
//    {"USD":4198.61}
//    {"EUR":3618.82,"PLN":15533.83,"USD":4107.54,"JPY":462616.35}

//    Full url:
//    https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=EUR,PLN,USD,JPY,?api_key={c4eb134f01e08f229e9b544f1521945267cd2c1b880d17c9a65cdc309a49e210}


    public boolean getAllCurrencyValues() {

//         HTTP dla BTC
        new HttpRequestTask(
                new HttpRequest(API_BASE + "?fsym=" + BTC + "&tsyms=" + EUR + PLN + USD + JPY + "?api_key=" + API_KEY, HttpRequest.GET),
                new HttpRequest.Handler() {
                    @Override
                    public void response(HttpResponse response) {

                        if (response.code == 200) {
                            String[] values = response.body.split(",");

                            CurrencyData.EUR_BTC = Double.valueOf(values[0].replace("}", "").split(":") [1]);
                            CurrencyData.PLN_BTC = Double.valueOf(values[1].replace("}", "").split(":") [1]);
                            CurrencyData.USD_BTC = Double.valueOf(values[2].replace("}", "").split(":") [1]);
                            CurrencyData.JPY_BTC = Double.valueOf(values[3].replace("}", "").split(":") [1]);

                            responseStatus = true;
                        }

                        else {
                            Log.e(this.getClass().toString(), "Błąd zapytania HTTP: " + response);

                            responseStatus = false;
                        }
                    }
                }).execute();

//        Http dla ETH
        new HttpRequestTask(
                new HttpRequest(API_BASE + "?fsym=" + ETH + "&tsyms=" + EUR + PLN + USD + JPY + "?api_key=" + API_KEY, HttpRequest.GET),
                new HttpRequest.Handler() {
                    @Override
                    public void response(HttpResponse response) {

                        if (response.code == 200) {
                            String[] values = response.body.split(",");

                            CurrencyData.EUR_ETH = Double.valueOf(values[0].replace("}", "").split(":") [1]);
                            CurrencyData.PLN_ETH = Double.valueOf(values[1].replace("}", "").split(":") [1]);
                            CurrencyData.USD_ETH = Double.valueOf(values[2].replace("}", "").split(":") [1]);
                            CurrencyData.JPY_ETH = Double.valueOf(values[3].replace("}", "").split(":") [1]);

                            responseStatus = true;
                        }

                        else {
                            Log.e(this.getClass().toString(), "Błąd zapytania HTTP: " + response);

                            responseStatus = false;
                        }
                    }
                }).execute();

        return responseStatus;
    }
}
