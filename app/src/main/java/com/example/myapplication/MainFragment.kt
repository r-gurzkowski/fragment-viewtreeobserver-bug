package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.TextView
import androidx.fragment.app.Fragment

class MainFragment : Fragment() {

    private val onLayoutObserver = ViewTreeObserver.OnGlobalLayoutListener {
        if (view == null) {
            throw IllegalStateException("OnGlobalLayoutListener shouldn't be called in fragment " +
                    "with index ${arguments?.getInt(ARG_INDEX)}")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = TextView(requireContext()).apply {
        text = arguments?.getInt(ARG_INDEX)?.toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.viewTreeObserver.addOnGlobalLayoutListener(onLayoutObserver)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // there are no listeners in this viewTreeObserver
        requireView().viewTreeObserver.removeOnGlobalLayoutListener(onLayoutObserver)
        // possible fix - listener is present in viewTreeObserver obtained from decorView
        // requireActivity().window.decorView.viewTreeObserver.removeOnGlobalLayoutListener(onLayoutObserver)
    }

    companion object {
        private const val ARG_INDEX = "ARG_INDEX"

        fun newInstance(index: Int): MainFragment = MainFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_INDEX, index)
            }
        }
    }
}