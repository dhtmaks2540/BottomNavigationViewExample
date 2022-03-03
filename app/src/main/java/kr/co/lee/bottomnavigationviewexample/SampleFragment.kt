package kr.co.lee.bottomnavigationviewexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import kr.co.lee.bottomnavigationviewexample.databinding.FragmentSampleBinding

class SampleFragment: Fragment() {
    private var _binding: FragmentSampleBinding? = null
    private val binding: FragmentSampleBinding
        get() = _binding!!
    private var count = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sample, container, false)

        binding.apply {
            tvName.text = requireArguments().getString("fragment_title")
            lifecycleOwner = this@SampleFragment
            tvCount.text = count.toString()
            btnAddCount.setOnClickListener {
                count++
                tvCount.text = count.toString()
            }
        }

        return binding.root
    }

    companion object {
        fun newInstance(title: String) = SampleFragment().apply {
            arguments = Bundle().apply {
                putString("fragment_title", title)
            }
        }
    }
}