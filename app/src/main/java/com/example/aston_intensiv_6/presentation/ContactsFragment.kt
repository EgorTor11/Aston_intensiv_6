package com.example.aston_intensiv_6.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aston_intensiv_6.R
import com.example.aston_intensiv_6.data.Contact
import com.example.aston_intensiv_6.data.Repository
import com.example.aston_intensiv_6.databinding.FragmentContaktsBinding

class ContactsFragment : Fragment() {
    lateinit var binding: FragmentContaktsBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var listLiveData = MutableLiveData<MutableList<Contact>>()
        var contactList = Repository.getInstans().getContactList()
        listLiveData.postValue(contactList)
        val adapter = ContactsAdapter(object : ContactsAdapter.Listener {
            override fun onImDeleteClick(contact: Contact) {
                contactList.removeAt(contact.id)
                listLiveData.postValue(contactList)
            }

            override fun onRootLongClick(contact: Contact) {
                contact.isImDeleteVisible = true
                val list= mutableListOf<Contact>()
                contactList.forEach{
                    list.add(it)
                }
               list[contact.id]=contact
                listLiveData.postValue(list)
            }

            override fun onRootClick(contact: Contact) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, DetailFragment.getInstance(contact))
                    .addToBackStack(null)
                    .commit()
            }

        })
        (binding.rcView.itemAnimator as? DefaultItemAnimator)?.supportsChangeAnimations = false
        binding.rcView.layoutManager = LinearLayoutManager(requireContext())
        binding.rcView.adapter = adapter
        listLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            Log.d("myLog", it[0].firstName)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContaktsBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        fun getInstance(): ContactsFragment {
            return ContactsFragment()
        }

    }
}