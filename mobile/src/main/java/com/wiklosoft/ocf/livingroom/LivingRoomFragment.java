package com.wiklosoft.ocf.livingroom;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.larswerkman.holocolorpicker.ColorPicker;
import com.wiklosoft.ocf.OcfControlPoint;
import com.wiklosoft.ocf.OcfDevice;


public class LivingRoomFragment extends Fragment{
    private static final String LIVINGROOM_ID = "00000000-0000-0000-0001-000000000001";

    TextView mConnectionStatus = null;
    TextView mServerConnectionStatus = null;
    OcfDevice mDevice = null;
    OcfControlPoint mController = null;

    ToggleButton mMasterButton = null;
    SeekBar mFrontSeekBar = null;
    SeekBar mBackSeekBar = null;
    SeekBar mTableSeekBar = null;
    SeekBar mAmbient = null;

    ColorPicker mPicker = null;

    void setScrollListeners()
    {
        mFrontSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


            }
        });
        mBackSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }
        });
        mTableSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               // mDevice.setValue("table", seekBar.getProgress());
            }
        });

        mAmbient.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        mPicker.setOnColorChangedListener(new ColorPicker.OnColorChangedListener() {
            @Override
            public void onColorChanged(int i) {
            }
        });
    }

    OcfControlPoint.OcfOnDeviceFound onFound = new OcfControlPoint.OcfOnDeviceFound() {
        @Override
        public void deviceFound(OcfDevice dev) {
            if (dev.getDi().equals(LIVINGROOM_ID) && mDevice == null){
                Log.d("", "device Found");
                mDevice = dev;
            }
        }
    };
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.living_room_fragment, container, false);

        mController = ((OcfApplicationContext)getActivity().getApplicationContext()).getOcfControlPoint();

        mController.addOnDeviceFoundCallback(onFound);
        
        mServerConnectionStatus = (TextView) rootView.findViewById(R.id.section_label);
        mConnectionStatus = (TextView) rootView.findViewById(R.id.device_connected);
        mMasterButton = (ToggleButton) rootView.findViewById(R.id.master_button);

        mFrontSeekBar = (SeekBar) rootView.findViewById(R.id.output_front);
        mBackSeekBar =(SeekBar) rootView.findViewById(R.id.output_back);
        mTableSeekBar = (SeekBar) rootView.findViewById(R.id.output_table);

        mAmbient = (SeekBar) rootView.findViewById(R.id.output_color);

        mPicker = (ColorPicker) rootView.findViewById(R.id.picker);

        ViewTreeObserver vto = mPicker.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mPicker.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int a = (int) (mPicker.getHeight() *0.45);
                mMasterButton.getLayoutParams().width = a;
                mMasterButton.getLayoutParams().height = a;
            }
        });

        setScrollListeners();
        
        mMasterButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            }
        });
        
        return rootView;
    }

}