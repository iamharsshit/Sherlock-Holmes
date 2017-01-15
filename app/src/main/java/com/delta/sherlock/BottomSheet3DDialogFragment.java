package com.delta.sherlock;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;

/**
 * Created by Harshit Bansal on 12/30/2016.
 */

public class BottomSheet3DDialogFragment extends BottomSheetDialogFragment{

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView=View.inflate(getContext(),R.layout.fragment_bottomsheet,null);
        dialog.setContentView(contentView);

    }
}
