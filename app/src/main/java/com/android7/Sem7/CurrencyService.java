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

    public void getAllHistoricalValues() {

        new HttpRequestTask(
            new HttpRequest("https://min-api.cryptocompare.com/data/histoday?fsym=BTC&tsym=EUR&limit=10" + "&api_key=" + API_KEY, HttpRequest.GET),
                new HttpRequest.Handler() {
                @Override
                        public void response(HttpResponse response) {
                    String rawData = response.body.substring(60);
                    rawData = rawData.split("\\]") [0];

                    String[] values = rawData.split(",");

                        for (int i = 0; i < values.length; i += 7) {
                            values[i] = values[i].replace('"', ' ');
                            values[i] = values[i].replace("{ time :", "");

                            CurrencyData.EUR_BTC_HST.add(Integer.valueOf(values[i]));

                            values[i + 1] = values[i + 1].replace('"', ' ');
                            values[i + 1] = values[i + 1].replace(" close :", "");

                            CurrencyData.EUR_BTC_HST.add(Double.valueOf(values[i + 1]).intValue());
                        }
                }
            }).execute();

        new HttpRequestTask(
                new HttpRequest("https://min-api.cryptocompare.com/data/histoday?fsym=ETH&tsym=EUR&limit=10" + "&api_key=" + API_KEY, HttpRequest.GET),
                new HttpRequest.Handler() {
                    @Override
                    public void response(HttpResponse response) {
                        String rawData = response.body.substring(60);
                        rawData = rawData.split("\\]") [0];

                        String[] values = rawData.split(",");

                        for (int i = 0; i < values.length; i += 7) {
                            values[i] = values[i].replace('"', ' ');
                            values[i] = values[i].replace("{ time :", "");

                            CurrencyData.EUR_ETH_HST.add(Integer.valueOf(values[i]));

                            values[i + 1] = values[i + 1].replace('"', ' ');
                            values[i + 1] = values[i + 1].replace(" close :", "");

                            CurrencyData.EUR_ETH_HST.add(Double.valueOf(values[i + 1]).intValue());
                        }
                    }
                }).execute();

        new HttpRequestTask(
                new HttpRequest("https://min-api.cryptocompare.com/data/histoday?fsym=BTC&tsym=PLN&limit=10" + "&api_key=" + API_KEY, HttpRequest.GET),
                new HttpRequest.Handler() {
                    @Override
                    public void response(HttpResponse response) {
                        String rawData = response.body.substring(60);
                        rawData = rawData.split("\\]") [0];

                        String[] values = rawData.split(",");

                        for (int i = 0; i < values.length; i += 7) {
                            values[i] = values[i].replace('"', ' ');
                            values[i] = values[i].replace("{ time :", "");

                            CurrencyData.PLN_BTC_HST.add(Integer.valueOf(values[i]));

                            values[i + 1] = values[i + 1].replace('"', ' ');
                            values[i + 1] = values[i + 1].replace(" close :", "");

                            CurrencyData.PLN_BTC_HST.add(Double.valueOf(values[i + 1]).intValue());
                        }
                    }
                }).execute();

        new HttpRequestTask(
                new HttpRequest("https://min-api.cryptocompare.com/data/histoday?fsym=ETH&tsym=PLN&limit=10" + "&api_key=" + API_KEY, HttpRequest.GET),
                new HttpRequest.Handler() {
                    @Override
                    public void response(HttpResponse response) {
                        String rawData = response.body.substring(60);
                        rawData = rawData.split("\\]") [0];

                        String[] values = rawData.split(",");

                        for (int i = 0; i < values.length; i += 7) {
                            values[i] = values[i].replace('"', ' ');
                            values[i] = values[i].replace("{ time :", "");

                            CurrencyData.PLN_ETH_HST.add(Integer.valueOf(values[i]));

                            values[i + 1] = values[i + 1].replace('"', ' ');
                            values[i + 1] = values[i + 1].replace(" high :", "");

                            CurrencyData.PLN_ETH_HST.add(Double.valueOf(values[i + 1]).intValue());
                        }
                    }
                }).execute();

        new HttpRequestTask(
                new HttpRequest("https://min-api.cryptocompare.com/data/histoday?fsym=BTC&tsym=USD&limit=10" + "&api_key=" + API_KEY, HttpRequest.GET),
                new HttpRequest.Handler() {
                    @Override
                    public void response(HttpResponse response) {
                        String rawData = response.body.substring(60);
                        rawData = rawData.split("\\]") [0];

                        String[] values = rawData.split(",");

                        for (int i = 0; i < values.length; i += 7) {
                            values[i] = values[i].replace('"', ' ');
                            values[i] = values[i].replace("{ time :", "");

                            CurrencyData.USD_BTC_HST.add(Integer.valueOf(values[i]));

                            values[i + 1] = values[i + 1].replace('"', ' ');
                            values[i + 1] = values[i + 1].replace(" close :", "");

                            CurrencyData.USD_BTC_HST.add(Double.valueOf(values[i + 1]).intValue());
                        }
                    }
                }).execute();

        new HttpRequestTask(
                new HttpRequest("https://min-api.cryptocompare.com/data/histoday?fsym=ETH&tsym=USD&limit=10" + "&api_key=" + API_KEY, HttpRequest.GET),
                new HttpRequest.Handler() {
                    @Override
                    public void response(HttpResponse response) {
                        String rawData = response.body.substring(60);
                        rawData = rawData.split("\\]") [0];

                        String[] values = rawData.split(",");

                        for (int i = 0; i < values.length; i += 7) {
                            values[i] = values[i].replace('"', ' ');
                            values[i] = values[i].replace("{ time :", "");

                            CurrencyData.USD_ETH_HST.add(Integer.valueOf(values[i]));

                            values[i + 1] = values[i + 1].replace('"', ' ');
                            values[i + 1] = values[i + 1].replace(" close :", "");

                            CurrencyData.USD_ETH_HST.add(Double.valueOf(values[i + 1]).intValue());
                        }
                    }
                }).execute();
        }

}
