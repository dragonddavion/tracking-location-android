package com.davion.jetpack.mygps.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.davion.jetpack.mygps.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrackingListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tracking_list, container, false)
    }
}