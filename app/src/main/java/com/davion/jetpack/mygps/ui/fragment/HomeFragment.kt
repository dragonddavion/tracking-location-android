package com.davion.jetpack.mygps.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.davion.jetpack.mygps.R
import com.davion.jetpack.mygps.util.IActivityConnector
import com.davion.jetpack.mygps.util.getCurrentDate
import com.davion.jetpack.mygps.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var activityConnector: IActivityConnector

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is IActivityConnector) {
            activityConnector = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadLayout()
    }

    private fun loadLayout() {
        setTime.setOnClickListener {
            val time = timeDelay.text.toString()
            Log.d("Davion", "time delay: $time")
            viewModel.setTime(time.toLong())
            textView.text = getCurrentDate()
        }

        getLocation.setOnClickListener {
            runWorker()
        }

        cancelButton.setOnClickListener {
            viewModel.cancelWorker()
        }
    }

    private fun runWorker() {
        if (activityConnector.checkPermission()) {
            viewModel.runWorker()
        }
    }
}