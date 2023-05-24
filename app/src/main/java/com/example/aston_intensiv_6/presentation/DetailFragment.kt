package com.example.aston_intensiv_6.presentation

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.aston_intensiv_6.data.Contact
import com.example.aston_intensiv_6.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {
    lateinit var binding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val contact = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getParcelable("key", Contact::class.java)
        } else {
            requireArguments().getParcelable("key")
        }
        binding.tvNameD.text = "${contact?.firstName} ${contact?.lastName}"
        binding.tvNumberD.text = contact?.phoneNumber
    }

    companion object {
        fun getInstance(value: Contact): DetailFragment {
            return DetailFragment().apply {
                arguments = bundleOf("key" to value)
            }

        }
    }
}