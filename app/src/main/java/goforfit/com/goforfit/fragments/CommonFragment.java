package goforfit.com.goforfit.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import goforfit.com.goforfit.R;
import goforfit.com.goforfit.helper.AppConfig;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommonFragment extends Fragment {


    private WebView webView;
    private ProgressBar progressBar;
    public CommonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_layout, container, false);
        webView = view.findViewById(R.id.webview);
        progressBar = view.findViewById(R.id.progress_bar);
        //noInternetImage = view.findViewById(R.id.no_internet_image);
        webView.getSettings().setJavaScriptEnabled(true);
        loadUrl();

        return view;
    }

    private void loadUrl(){
        webView.setWebViewClient(new Callback());

        Bundle bundle = getArguments();
        if (bundle!=null) {
            webView.loadUrl(bundle.getString(AppConfig.INTENT_URL_EXTRA));
        }
        else
            Toast.makeText(getActivity(), "Something went wrong! Please load again!", Toast.LENGTH_SHORT).show();

    }


    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            webView.loadUrl(url);
//            return true;
            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);

        }
    }

}
