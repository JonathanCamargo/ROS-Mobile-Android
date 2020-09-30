package com.schneewittchen.rosandroid.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.anychart.charts.Cartesian;
import com.schneewittchen.rosandroid.R;
import com.schneewittchen.rosandroid.ui.custum_views.WidgetGroup;
import com.schneewittchen.rosandroid.viewmodel.VizViewModel;
import com.schneewittchen.rosandroid.widgets.base.DataListener;
import com.schneewittchen.rosandroid.widgets.base.BaseData;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.charts.Pie;
import java.util.ArrayList;
import java.util.List;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;

/**
 * TODO: Description
 *
 * @author Nico Studt
 * @version 1.0.2
 * @created on 10.01.20
 * @updated on 21.04.20
 * @modified by Nils Rottmann
 */
public class VizFragment extends Fragment implements DataListener {

    public static final String TAG = VizFragment.class.getSimpleName();

    private VizViewModel mViewModel;
    private WidgetGroup widgetGroupview;


    public static VizFragment newInstance() {
        return new VizFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_viz, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        widgetGroupview = view.findViewById(R.id.widget_groupview);
        widgetGroupview.setDataListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(VizViewModel.class);

        mViewModel.getCurrentWidgets().observe(getViewLifecycleOwner(), widgetEntities -> {
            widgetGroupview.setWidgets(widgetEntities);
        });

        mViewModel.getData().observe(getViewLifecycleOwner(), data -> {
            widgetGroupview.setData(data);
        });
    }

    @Override
    public void onNewData(BaseData data) {
        mViewModel.informWidgetDataChange(data);
    }

}
