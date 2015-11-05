package me.company.rtspviewer.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import rtspviewer.company.me.rtspviewer.R;

/**
 * Created by giovanni on 11/4/15.
 */
public class ScanQRFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View rootView = inflater.inflate(R.layout.fragment_scanqrcode, container, false);

        // Configure buttons listeners
        this.configureButtonsListeners(rootView);

        return rootView;
    }


    /**********************************************************************************************/

    public void configureButtonsListeners(View rootView) {

        // Configure scanQR Button
        Button scanQRBtn = (Button) rootView.findViewById(R.id.scanQRBtn);
        scanQRBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // @TODO, Validate QR Code before go to stream viewer

                // Go to Streem viewer
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainContainer, new StreamViewerFragment(), "streamviewer")
                        .commit();
            }
        });
    }
}
